package mvc.model;


import mvc.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Calendario {
    private LocalDate dataAtual;

    // Construtor
    public Calendario(LocalDate dataInicial) {
        this.dataAtual = dataInicial;
    }

    // Atualiza o calendário (pode ser feito manualmente pelo administrador)
    public void atualizarData(LocalDate novaData) {
        this.dataAtual = novaData;
    }

    public LocalDateTime avancarDia(LocalDateTime dataRecebida) {
        // Avança a data atual em um dia
        return dataRecebida.plusDays(1);
    }

    // Pegar dia inicial do sistema e avançar para a data atual
    public void avancarDiasEconferirPagamentos(ArrayList<Pagamento> pagamentos, ArrayList<Fornecedor> fornecedores) {
        LocalDateTime diaInicialDoSistema = Util.getDiaInicioDoSistema().atStartOfDay();
        LocalDateTime diaAtual = Util.getDia();

        do {
            diaInicialDoSistema = avancarDia(diaInicialDoSistema);
            boolean pagamentoEfetuado = verificarPagamentosAgendados(diaInicialDoSistema, pagamentos, fornecedores);
            if (pagamentoEfetuado) {
                System.out.println("\nPagamento agendado para '" + diaInicialDoSistema.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "' foi efetuado com sucesso em '" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "'.");
                System.out.println("\n--------------------------------------------------");
            }
        } while (diaInicialDoSistema.isBefore(diaAtual)) ;

        if (!verificarPagamentosAgendados(diaInicialDoSistema, pagamentos, fornecedores)) {
            System.out.println("\nNão há mais pagamentos agendados para serem efetuados.");
        }

        // Notificar pagamentos próximos
        notificarPagamentosProximos(pagamentos, 3);
    }

    // Verifica pagamentos agendados para a data atual
    public boolean verificarPagamentosAgendados(LocalDateTime data, ArrayList<Pagamento> pagamentos, ArrayList<Fornecedor> fornecedores) {
        boolean pagamentoEfetuado = false;

        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado() && pagamento.getDataPagamento().equals(data.toLocalDate())
                    && pagamento.getFornecedor().getValorAPagar() > 0) {
                Fornecedor fornecedor = pagamento.getFornecedor();

                if (fornecedor.getValorAPagar() == 0) {
                    System.out.println("\nO fornecedor " + fornecedor.getNome() + " já foi totalmente pago.");
                } else {
                    double valorParcela = fornecedor.getValorParcela();
                    double valorPago = valorParcela * pagamento.getParcela();
                    int parcelasRestantes = fornecedor.getParcelas() - pagamento.getParcela();
                    int totalParcelasPagas = fornecedor.getTotalParcelasPagas() + pagamento.getParcela();

                    String SQL = "UPDATE fornecedor SET valorAPagar = ?, parcelas = ?, totalParcelasPagas = ? WHERE id = ?";
                    try (Connection connection = new ConnectionFactory().getConnection();
                         PreparedStatement stmt = connection.prepareStatement(SQL)) {

                        stmt.setDouble(1, fornecedor.getValorAPagar() - valorPago);
                        stmt.setInt(2, parcelasRestantes);
                        stmt.setInt(3, totalParcelasPagas);
                        stmt.setLong(4, fornecedor.getId());
                        stmt.execute();
                        System.out.println("\nValor a pagar e parcelas do fornecedor atualizados com sucesso.");

                    } catch (Exception e) {
                        System.out.println("\nErro ao atualizar valor a pagar e parcelas do fornecedor: " + e.getMessage());
                        e.printStackTrace();
                    }

                    String SQL2 = "UPDATE pagamento SET parcela = ?, agendado = ?, dataModificacao = ? WHERE id = ?";
                    try (Connection connection = new ConnectionFactory().getConnection();
                         PreparedStatement stmt = connection.prepareStatement(SQL2)) {
                        stmt.setInt(1, pagamento.getParcela());
                        stmt.setBoolean(2, false);
                        stmt.setTimestamp(3, Util.localDateTimeToTimestamp(LocalDateTime.now()));
                        stmt.setLong(4, pagamento.getId());
                        stmt.execute();
                        System.out.println("\nPagamento atualizado com sucesso.");

                    } catch (Exception e) {
                        System.out.println("\nErro ao atualizar pagamento: " + e.getMessage());
                        e.printStackTrace();
                    }

                    String SQL3 = "INSERT INTO historicoPagamento (fornecedorId, valorPago, dataPagamento) VALUES (?, ?, ?)";
                    try (Connection connection = new ConnectionFactory().getConnection();
                         PreparedStatement stmt = connection.prepareStatement(SQL3)) {

                        stmt.setLong(1, fornecedor.getId());
                        stmt.setDouble(2, valorPago);
                        stmt.setTimestamp(3, Util.localDateTimeToTimestamp(LocalDateTime.now()));
                        stmt.execute();
                        System.out.println("\nPagamento inserido no histórico com sucesso.");

                    } catch (Exception e) {
                        System.out.println("\nErro ao inserir pagamento no histórico: " + e.getMessage());
                        e.printStackTrace();
                    }

                    String SQL4 = "UPDATE fornecedor SET estado = ? WHERE id = ?";
                    try (Connection connection = new ConnectionFactory().getConnection();
                         PreparedStatement stmt = connection.prepareStatement(SQL4)) {

                        if (fornecedor.getValorAPagar() <= 0) {
                            fornecedor.setEstado("Pago");
                            stmt.setString(1, "Pago");
                        } else {
                            stmt.setString(1, "Parcial");
                        }
                        stmt.setLong(2, fornecedor.getId());
                        stmt.execute();
                        System.out.println("\nEstado do fornecedor atualizado com sucesso.");

                    } catch (Exception e) {
                        System.out.println("\nErro ao atualizar estado do fornecedor: " + e.getMessage());
                        e.printStackTrace();
                    }

                    pagamentoEfetuado = true;
                }
            }
        }
        return pagamentoEfetuado;
    }

    public void notificarPagamentosProximos(ArrayList<Pagamento> pagamentos, int diasAntecedencia) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado()) {
                LocalDateTime dataPagamento = pagamento.getDataPagamento().atStartOfDay();

                // Verifica se o pagamento está dentro do intervalo de dias antes do vencimento
                if (dataPagamento.minusDays(diasAntecedencia).equals(this.dataAtual)) {
                    System.out.println("\nATENÇÃO: O pagamento de " + pagamento.getFornecedor().getNome() +
                            " está agendado para daqui a " + diasAntecedencia + " dias.");
                }
            }
        }
    }

    public void notificarPagamentosAtrasados(ArrayList <Pagamento> pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado()) {
                LocalDate dataPagamento = pagamento.getDataPagamento();

                // Verifica se o pagamento está atrasado (data do pagamento já passou)
                if (dataPagamento.isBefore(this.dataAtual)) {
                    System.out.println("\nAVISO: O pagamento de " + pagamento.getFornecedor().getNome() +
                            " está atrasado! Vencimento: " + Util.formataDataHora(dataPagamento.atStartOfDay()));
                }
            }
        }
    }

    // Exibe a data atual do calendário
    public void exibirDataAtual() {
        System.out.println("\nData atual do calendário: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    // Getters e Setters
    public LocalDate getDataAtual() {
        return dataAtual;
    }

    // Exibe os pagamentos agendados para a data atual
    public void exibirPagamentosAgendados(LocalDateTime dataAtual, ArrayList<Pagamento> pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.getDataPagamento().equals(dataAtual.toLocalDate())) {
                System.out.println("\n" + pagamento);
            }
        }
    }
}
