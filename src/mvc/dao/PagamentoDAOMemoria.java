package mvc.dao;

import mvc.model.Pagamento;
import mvc.model.Util;

public class PagamentoDAOMemoria implements PagamentoDAO{
    private final Pagamento[] pagamentos;
    private int totalPagamentos;

    public PagamentoDAOMemoria(PessoaDAO pessoaDAO, FornecedorDAO fornecedorDAO, int capacidade) {
        this.pagamentos = new Pagamento[capacidade];
        this.totalPagamentos = 0;

        Pagamento p1 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, primeira parcela.", 1250.0,1, true, Util.formataData("21/06/2024"));
        this.criarPagamento(p1);
        Pagamento p2 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, segunda parcela.", 1250.0,1, true, Util.formataData("01/07/2024"));
        this.criarPagamento(p2);
        Pagamento p3 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, terceira parcela.", 1250.0,1, true, Util.formataData("15/07/2024"));
        this.criarPagamento(p3);
        Pagamento p4 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, quarta parcela.", 1250.0,1, true, Util.formataData("01/08/2024"));
        this.criarPagamento(p4);
        Pagamento p5 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, quinta parcela.", 1250.0,1, true, Util.formataData("15/08/2024"));
        this.criarPagamento(p5);
        Pagamento p6 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, sexta parcela.", 1250.0,1, true, Util.formataData("01/09/2024"));
        this.criarPagamento(p6);
        Pagamento p7 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, sétima parcela.", 1250.0,1, true, Util.formataData("15/09/2024"));
        this.criarPagamento(p7);
        Pagamento p8 = new Pagamento(pessoaDAO.buscaPorId(0L), fornecedorDAO.buscaPorId(0L), "Pagamento a empresa XZY das lonas da festa, oitava e última parcela.", 1250.0,1, true, Util.formataData("01/10/2024"));
        this.criarPagamento(p8);
        Pagamento p9 = new Pagamento(pessoaDAO.buscaPorId(2L), fornecedorDAO.buscaPorId(1L), "Pagamento a empresa ABC das bebidas da festa, primeira parcela.", 1500.0, 1, true, Util.formataData("22/10/2024"));
        this.criarPagamento(p9);
        Pagamento p10 = new Pagamento(pessoaDAO.buscaPorId(2L), fornecedorDAO.buscaPorId(1L), "Pagamento a empresa ABC das bebidas da festa, segunda e terceira parcela.", 3000.0, 2, true, Util.formataData("02/11/2024"));
        this.criarPagamento(p10);
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
    
    public Pagamento[] buscarTodos() {
    	// Conta número de pagamentos válidos
        int count = 0;
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null) {
                count++;
            }
        }

        // Array do tamanho exato dos pagamentos válidos
        Pagamento[] pagamentosValidos = new Pagamento[count];
        int index = 0;

        // Adiciona apenas os pagamentos válidos no array
        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null) {
            	pagamentosValidos[index++] = pagamento;
            }
        }
        
        return pagamentosValidos;
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
