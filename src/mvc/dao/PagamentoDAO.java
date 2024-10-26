package mvc.dao;

import mvc.model.Pagamento;

public interface PagamentoDAO {
    boolean criarPagamento(Pagamento pagamento);
    Pagamento buscarPagamentoPorId(long id);
    Pagamento[] buscarTodos();
    boolean atualizarPagamento(Pagamento pagamento);
    boolean removerPagamento(long id);
    void listarPagamentos();
    void exibirListaSimplesPagamentos();
    Pagamento[] getPagamentos();
}
