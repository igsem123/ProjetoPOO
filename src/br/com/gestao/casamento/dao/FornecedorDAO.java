package br.com.gestao.casamento.dao;

import br.com.gestao.casamento.model.Fornecedor;

public interface FornecedorDAO {
    void inicializarFornecedoresDeExemplo();
    void criarFornecedor(Fornecedor fornecedor);
    void listarFornecedores();
    void atualizarFornecedor(String CNPJ);
    Fornecedor buscaFornecedor(String CNPJ);
    Fornecedor buscaPorId(Long id);
    void deletarFornecedor(Long id);
}
