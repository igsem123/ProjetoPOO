package mvc.model;

import java.util.Calendar;
import java.util.TimeZone;

public class Calendario extends Calendar{

    private Calendar dataAtual;

    // Construtor
    public Calendario(Calendar dataInicial) {
        this.dataAtual = dataInicial;
    }

    // Atualiza o calendário manualmente (administrador pode alterar a data)
    public void atualizarData(Calendar novaData) {
        this.dataAtual = novaData;
    }

    // Verifica os pagamentos agendados para a data atual
    public void verificarPagamentosAgendados(Pagamento[] pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            // Comparação de datas com Calendar
            if (pagamento.isAgendado() && pagamento.getDataPagamento().getTime().equals(this.dataAtual.getTime())) {
                pagamento.setParcela(0); // Marca o pagamento como completo
                pagamento.setDataModificacao(Calendar.getInstance()); // Atualiza a data de modificação

                // Se todas as parcelas forem pagas, altera o estado do fornecedor
                if (pagamento.isPagoCompleto()) {
                    pagamento.getFornecedor().setEstado("Pago Completo");
                }
            }
        }
    }

    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int field, int amount) {

    }

    @Override
    public void roll(int field, boolean up) {

    }

    @Override
    public int getMinimum(int field) {
        return 0;
    }

    @Override
    public int getMaximum(int field) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int field) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int field) {
        return 0;
    }

}
