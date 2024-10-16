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

    public void avancarDia(Pagamento[] pagamentos) {
        // Avança a data atual em um dia
        dataAtual = dataAtual.plusDays(1);

        // Verifica pagamentos agendados para a data atual
        verificarPagamentosAgendados(pagamentos);
    }

    public void avancarDias(int dias, Pagamento[] pagamentos) {
        for (int i = 0; i < dias; i++) {
            avancarDia(pagamentos);
        }
    }

    // Verifica pagamentos agendados para a data atual
    public void verificarPagamentosAgendados(Pagamento[] pagamentos) {
        // Verifica pagamentos agendados para a data atual
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.isAgendado() && pagamento.getDataPagamento().equals(this.dataAtual)) {
                // Obter o fornecedor relacionado ao pagamento
                Fornecedor fornecedor = pagamento.getFornecedor();

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
                pagamento.setParcela(0); // Marca o pagamento como concluído para esse ciclo
                pagamento.setDataModificacao(LocalDateTime.now());

                // Se todas as parcelas foram pagas, atualiza o estado do fornecedor
                if (fornecedor.getValorAPagar() <= 0) {
                    fornecedor.setEstado("Pago Completo");
                    System.out.println("\nO fornecedor " + fornecedor.getNome() + " foi totalmente pago.");
                } else {
                    System.out.println("\nO fornecedor " + fornecedor.getNome() + " ainda possui saldo em aberto: " + fornecedor.getValorAPagar());
                }
            }
        }
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
}
