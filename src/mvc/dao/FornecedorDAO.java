package mvc.dao;

import mvc.model.Fornecedor;

import java.util.ArrayList;

public interface FornecedorDAO {
    void criarFornecedor(Fornecedor fornecedor);
    void listarFornecedores();
    void exibeFornecedoresSimples();
    void atualizarFornecedor(Fornecedor fornecedor);
    Fornecedor buscaFornecedor(String CNPJ);
    Fornecedor buscaPorId(Long id);
    void deletarFornecedor(Long id);
    int getTotalFornecedores();
    ArrayList<Fornecedor> getFornecedores();
}
