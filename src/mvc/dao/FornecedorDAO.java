package mvc.dao;

import mvc.model.Fornecedor;

public interface FornecedorDAO {
    void criarFornecedor(Fornecedor fornecedor);
    void listarFornecedores();
    void exibeFornecedoresSimples();
    void atualizarFornecedor(Fornecedor fornecedor);
    Fornecedor buscaFornecedor(String CNPJ);
    Fornecedor buscaPorId(Long id);
    void deletarFornecedor(Long id);
    int getTotalFornecedores();
}
