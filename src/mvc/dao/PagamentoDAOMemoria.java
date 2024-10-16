package mvc.dao;

import mvc.model.Pagamento;
import mvc.model.Util;

public class PagamentoDAOMemoria implements PagamentoDAO{
    private final Pagamento[] pagamentos;
    private int totalPagamentos;

    public PagamentoDAOMemoria(PessoaDAO pessoaDAO, FornecedorDAO fornecedorDAO, int capacidade) {
        this.pagamentos = new Pagamento[capacidade];
        this.totalPagamentos = 0;

        Pagamento p1 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa.", 2500.0,2, true, Util.formataData("21/10/2024"));
        this.criarPagamento(p1);
    }

    public Pagamento[] getPagamentos() {
        return pagamentos;
    }

    public boolean criarPagamento(Pagamento pagamento) {
        if (totalPagamentos < pagamentos.length) {
            pagamentos[totalPagamentos] = pagamento;
            totalPagamentos++;
            System.out.println("\nDados do pagamento: \n\n" + pagamento.toString());
            return true;
        } else {
            System.out.println("\nCapacidade máxima atingida.");
            return false;
        }
    }

    public Pagamento buscarPagamentoPorId(long id) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null && pagamento.getId() == id) {
                System.out.println(pagamento.toString());
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
        totalPagamentos--;
    }

    public void listarPagamentos() {
        if (totalPagamentos == 0) {
            System.out.println("\nNenhum pagamento registrado!");
        } else {
            for (Pagamento pagamento : pagamentos) {
                if (pagamento != null) {
                    System.out.println(pagamento);
                }
            }
        }
    }

    public void exibirListaSimplesPagamentos() {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null) {
                System.out.println("ID do Pagamento [" + pagamento.getId() + "] - Pagador: " + pagamento.getPessoa().getNome() + " - Fornecedor: " + pagamento.getFornecedor().getNome());
            }
        }
    }
}
