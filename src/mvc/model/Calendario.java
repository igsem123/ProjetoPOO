package mvc.model;


import mvc.dao.FornecedorController;
import mvc.dao.FornecedorDAO;
import mvc.dao.PagamentoController;
import mvc.dao.PagamentoDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Calendario {
    private LocalDate dataAtual;
    private FornecedorDAO fornecedorDAO = new FornecedorController();
    private PagamentoDAO pagamentoDAO = new PagamentoController();

    // Construtor
    public Calendario(LocalDate dataInicial) {
        this.dataAtual = dataInicial;
    }

    // Atualize o calendário (possa ser feito manualmente pelo administrador)
    public void atualizarData(LocalDate novaData) {
        this.dataAtual = novaData;
    }

    public LocalDateTime avancarDia(LocalDateTime dataRecebida) {
        // Avança a data atual em um dia
        return dataRecebida.plusDays(1);
    }

    // Pegar dia inicial do sistema e avançar para a data atual
    public void avancarDiasEconferirPagamentos(ArrayList<Pagamento> pagamentos) {
        LocalDateTime diaInicialDoSistema = Util.getDiaInicioDoSistema().atStartOfDay();
        LocalDateTime diaAtual = Util.getDia();

        do {
            diaInicialDoSistema = avancarDia(diaInicialDoSistema);
            boolean pagamentoEfetuado = verificarPagamentosAgendados(diaInicialDoSistema, pagamentos);
            if (pagamentoEfetuado) {
                System.out.println("\nPagamento agendado para '" + diaInicialDoSistema.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "' foi efetuado com sucesso em '" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "'.");
                System.out.println("\n--------------------------------------------------");
            }
        } while (diaInicialDoSistema.isBefore(diaAtual));

        if (!verificarPagamentosAgendados(diaInicialDoSistema, pagamentos)) {
            System.out.println("\nNão há mais pagamentos agendados para serem efetuados.");
        }

        // Notificar pagamentos próximos
        notificarPagamentosProximos(pagamentos, 3);
    }

    // Verifique pagamentos agendados para a data atual.
    public boolean verificarPagamentosAgendados(LocalDateTime data, ArrayList<Pagamento> pagamentos) {
        boolean pagamentoEfetuado = false;

        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado() && pagamento.getDataPagamento().equals(data.toLocalDate())
                    && pagamento.getFornecedor().getValorAPagar() > 0) {
                Fornecedor fornecedor = fornecedorDAO.buscaPorId(pagamento.getFornecedor().getId());

                if (fornecedor.getValorAPagar() == 0) {
                    System.out.println("\nO fornecedor " + fornecedor.getNome() + " já foi totalmente pago.");
                } else {
                    System.out.println("\nRelatório do pagamento agendado para '" + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "':");

                    double valorParcela = fornecedor.getValorParcela();
                    double valorPago = valorParcela * pagamento.getParcela();
                    int parcelasRestantes = fornecedor.getParcelas() - pagamento.getParcela();
                    int totalParcelasPagas = fornecedor.getTotalParcelasPagas() + pagamento.getParcela();

                    fornecedorDAO.atualizarFornecedorCalendario(fornecedor, valorPago, parcelasRestantes, totalParcelasPagas);
                    System.out.println("\nValor a pagar e parcelas do fornecedor atualizados com sucesso.");
                    System.out.println("-> Valor em débito com o fornecedor atualmente: " + (fornecedor.getValorAPagar() - valorPago));
                    System.out.println("-> Parcelas restantes: " + parcelasRestantes);
                    System.out.println("-> Total de parcelas pagas: " + totalParcelasPagas);

                    pagamentoDAO.atualizarPagamentoCalendario(pagamento);
                    System.out.println("\nPagamento atualizado com sucesso.");

                    pagamentoDAO.inserirHistoricoPagamento(fornecedor.getId(), valorPago);
                    System.out.println("\nPagamento inserido no histórico de pagamentos com sucesso.");

                    fornecedorDAO.atualizarEstadoFornecedor(fornecedor);
                    System.out.println("\nEstado do fornecedor atualizado com sucesso para: " + fornecedorDAO.buscaPorId(fornecedor.getId()).getEstado() + ".");

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

                // Verifique se o pagamento está dentro do intervalo de dias antes do vencimento
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

                // Verifique se o pagamento está atrasado (date do pagamento já passou)
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
