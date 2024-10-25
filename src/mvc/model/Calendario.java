package mvc.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void avancarDiasEconferirPagamentos(Pagamento[] pagamentos) {
        LocalDateTime diaInicialDoSistema = Util.getDiaInicioDoSistema().atStartOfDay();
        LocalDateTime diaAtual = Util.getDia();

        do {
            diaInicialDoSistema = avancarDia(diaInicialDoSistema);
            if (verificarPagamentosAgendados(diaInicialDoSistema, pagamentos)) {
                System.out.println("\nPagamento agendado para '" + diaInicialDoSistema.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "' foi efetuado com sucesso em '" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "'.");
                System.out.println("\n--------------------------------------------------");
            }
        } while (diaInicialDoSistema.isBefore(diaAtual)) ;

        if (!verificarPagamentosAgendados(diaInicialDoSistema, pagamentos)) {
            System.out.println("\nNão há mais pagamentos agendados para serem efetuados.");
        }

        // Notificar pagamentos próximos
        notificarPagamentosProximos(pagamentos, 3);
    }

    // Verifica pagamentos agendados para a data atual
    public boolean verificarPagamentosAgendados(LocalDateTime data, Pagamento[] pagamentos) {
        // Processa os pagamentos agendados para a data atual
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado() && pagamento.getDataPagamento().equals(data.toLocalDate())
                    && pagamento.getFornecedor().getValorAPagar() > 0) {
                // Obter o fornecedor relacionado ao pagamento
                Fornecedor fornecedor = pagamento.getFornecedor();

                if (fornecedor.getValorAPagar() == 0) {
                    System.out.println("\nO fornecedor " + fornecedor.getNome() + " já foi totalmente pago.");
                } else {
                    // Calcular valor das parcelas a serem pagas
                    double valorAPagar = pagamento.getValor(); // Valor total do pagamento
                    int parcelasRestantes = fornecedor.getParcelas(); // Parcelas restantes do fornecedor

                    // Calcular o valor de cada parcela
                    double valorParcela = fornecedor.getValorParcela();
                    double valorPago = valorParcela * pagamento.getParcela();

                    // Atualiza o valor do fornecedor após o pagamento
                    fornecedor.setValorAPagar(fornecedor.getValorAPagar() - valorPago);
                    fornecedor.setParcelas(parcelasRestantes - pagamento.getParcela());

                    // Atualiza o pagamento como concluído
                    pagamento.setParcela(1);
                    pagamento.setAgendado(false);
                    pagamento.setDataModificacao(LocalDateTime.now());

                    // Se todas as parcelas foram pagas, atualiza o estado do fornecedor
                    if (fornecedor.getValorAPagar() <= 0) {
                        fornecedor.setEstado("Pago Completo");
                        System.out.println("\nO fornecedor " + fornecedor.getNome() + " foi totalmente pago.");
                    } else {
                        System.out.println("\nTransação efetuada com sucesso! O fornecedor " + fornecedor.getNome() + " ainda possui saldo em aberto de R$ " + fornecedor.getValorAPagar() + ".");
                    }

                    return true;
                }
            }
        }
        return false;
    }

    public void notificarPagamentosProximos(Pagamento[] pagamentos, int diasAntecedencia) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado()) {
                LocalDate dataPagamento = pagamento.getDataPagamento();

                // Verifica se o pagamento está dentro do intervalo de dias antes do vencimento
                if (dataPagamento.minusDays(diasAntecedencia).equals(this.dataAtual)) {
                    System.out.println("\nATENÇÃO: O pagamento de " + pagamento.getFornecedor().getNome() +
                            " está agendado para daqui a " + diasAntecedencia + " dias.");
                }
            }
        }
    }

    public void notificarPagamentosAtrasados(Pagamento[] pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado()) {
                LocalDate dataPagamento = pagamento.getDataPagamento();

                // Verifica se o pagamento está atrasado (data do pagamento já passou)
                if (dataPagamento.isBefore(this.dataAtual)) {
                    System.out.println("\nAVISO: O pagamento de " + pagamento.getFornecedor().getNome() +
                            " está atrasado! Vencimento: " + dataPagamento);
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
    public void exibirPagamentosAgendados(LocalDateTime dataAtual, Pagamento[] pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.getDataPagamento().equals(dataAtual.toLocalDate())) {
                System.out.println("\n" + pagamento);
            }
        }
    }
}
