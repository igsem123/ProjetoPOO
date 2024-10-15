package mvc.dao;

import mvc.model.Pagamento;

public class PagamentoDAOMemoria implements PagamentoDAO{
    private final Pagamento[] pagamentos;
    private int totalEventos;

    public PagamentoDAOMemoria(int capacidade) {
        this.pagamentos = new Pagamento[capacidade];
        this.totalEventos = 0;
    }

    public boolean criarPagamento(Pagamento pagamento) {
        if (totalEventos < pagamentos.length) {
            pagamentos[totalEventos] = pagamento;
            totalEventos++;
            return true;
        } else {
            System.out.println("\nCapacidade máxima atingida.");
            return false;
        }
    }

    public Pagamento buscarPagamentoPorId(long id) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.getId() == id) {
                return pagamento;
            }
        }
        System.out.println("\nPagamento com ID [" + id + "] não encontrado.");
        return null;
    }

    public boolean atualizarPagamento(Pagamento pagamentoAtualizado) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] != null && pagamentos[i].getId() == pagamentoAtualizado.getId()) {
                pagamentos[i] = pagamentoAtualizado;
                return true;
            }
        }
        System.out.println("\nPagamento não encontrado para atualização.");
        return false;
    }

    public boolean removerPagamento(long id) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] != null && pagamentos[i].getId() == id) {
                pagamentos[i] = null;
                reorganizarArray(i);
                return true;
            }
        }
        System.out.println("\nPagamento com ID [" + id + "] não encontrado para remoção.");
        return false;
    }

    private void reorganizarArray(int posicaoRemovida) {
        for (int i = posicaoRemovida; i < pagamentos.length - 1; i++) {
            pagamentos[i] = pagamentos[i + 1];
        }
        pagamentos[pagamentos.length - 1] = null;
        totalEventos--;
    }

    public void listarPagamentos() {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null) {
                System.out.println(pagamento);
            }
        }
    }

}
