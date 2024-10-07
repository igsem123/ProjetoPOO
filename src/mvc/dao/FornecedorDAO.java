package mvc.dao;

import mvc.model.Fornecedor;

public interface FornecedorDAO {
    boolean criarFornecedor(Fornecedor fornecedor);
    void listarFornecedores();
    void exibeFornecedoresSimples();
    void atualizarFornecedor(String CNPJ);
    Fornecedor buscaFornecedor(String CNPJ);
    Fornecedor buscaPorId(Long id);
    void deletarFornecedor(Long id);
}
