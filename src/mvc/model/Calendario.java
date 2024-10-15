package mvc.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
        dataAtual = dataAtual.plusDays(1);

        // Verifica pagamentos agendados para a data atual
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.isAgendado() && pagamento.getDataPagamento().equals(this.dataAtual)) {
                pagamento.setParcela(0); // Marca pagamento como completo
                pagamento.setDataModificacao(LocalDateTime.now());

                // Se todas as parcelas foram pagas, atualiza o estado do fornecedor
                if (pagamento.isPagoCompleto()) {
                    pagamento.getFornecedor().setEstado("Pago Completo");
                }
            }
        }
    }

    public void avancarDias(int dias, Pagamento[] pagamentos) {
        for (int i = 0; i < dias; i++) {
            avancarDia(pagamentos);
        }
    }

    // Exibe a data atual do calendário
    public void exibirDataAtual() {
        System.out.println("Data atual do calendário: " + dataAtual);
    }

    // Getters e Setters
    public LocalDate getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(LocalDate dataAtual) {
        this.dataAtual = dataAtual;
    }
}
