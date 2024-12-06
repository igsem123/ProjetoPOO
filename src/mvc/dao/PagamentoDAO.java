package mvc.dao;

import mvc.model.Pagamento;

import java.util.ArrayList;

public interface PagamentoDAO {
    boolean criarPagamento(Pagamento pagamento);
    Pagamento buscarPagamentoPorId(long id);
    ArrayList<Pagamento> buscarTodos();
    boolean atualizarPagamento(Pagamento pagamento);
    boolean removerPagamento(long id);
    void listarPagamentos();
    void exibirListaSimplesPagamentos();
    ArrayList<Pagamento> getPagamentos();
    String getAgendamento(long id);
    void inserirHistoricoPagamento(long id, double valorPago);
    void atualizarPagamentoCalendario(Pagamento pagamento);
}
