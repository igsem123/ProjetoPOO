package br.com.gestao.casamento.dao;

import br.com.gestao.casamento.model.Fornecedor;
import br.com.gestao.casamento.model.Pessoa;
import br.com.gestao.casamento.model.Util;

import java.time.LocalDateTime;

import static br.com.gestao.casamento.model.Fornecedor.totalFornecedores;

public class FornecedorDAOMemoria implements FornecedorDAO {
    private static final int TAMANHO_MAXIMO = 50;
    private final Fornecedor[] listaFornecedores;  // Simulação de armazenamento
    private Util util;

    public FornecedorDAOMemoria() {
        this.listaFornecedores = new Fornecedor[TAMANHO_MAXIMO];
        totalFornecedores = 0;
    }

    public void inicializarFornecedoresDeExemplo() {
        Fornecedor f1 = new Fornecedor("Empresa XYZ", "12.345.678/0001-90", "(11) 99999-8888",
                10000, 8, "Não Pago", "contato@empresaxyz.com");
        this.criarFornecedor(f1);
    }

    @Override
    public void criarFornecedor(Fornecedor fornecedor) {
        if (totalFornecedores < TAMANHO_MAXIMO) {
            listaFornecedores[totalFornecedores] = fornecedor;
            totalFornecedores++;
            System.out.println("Fornecedor cadastrado com sucesso:\n\n" + fornecedor);
        } else {
            System.out.println("Erro: Não há espaço para mais fornecedores.");
        }
    }

    @Override
    public Fornecedor buscaPorId(Long id) {
        for (int i = 0; i < totalFornecedores; i++) {
            if (listaFornecedores[i].getId() == id) {
                return listaFornecedores[i];
            }
        }
        System.out.println("Fornecedor não encontrada.");
        return null;
    }

    @Override
    public void atualizarFornecedor(String CNPJ) {
        Fornecedor fornecedor = buscaFornecedor(CNPJ);
        for (int i = 0; i < totalFornecedores; i++) {
            if (listaFornecedores[i].getId() == fornecedor.getId()) {
                listaFornecedores[i] = fornecedor;
                fornecedor.setDataModificacao(LocalDateTime.now());
                System.out.println("Fornecedor atualizada: " + fornecedor);
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    @Override
    public void deletarFornecedor(Long id) {
        listarFornecedores();
        for (int i = 0; i < totalFornecedores; i++) {
            if (listaFornecedores[i].getId() == id) {
                for (int j = i; j < totalFornecedores - 1; j++) {
                    listaFornecedores[j] = listaFornecedores[j + 1];  // Move os itens para trás no array
                }
                totalFornecedores--;
                System.out.println("Fornecedor deletada com sucesso.");
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    public void listarFornecedores() {
        if (totalFornecedores == 0) {
            System.out.println("Nenhum fornecedor cadastrada.");
        } else {
            for (int i = 0; i < totalFornecedores; i++) {
                System.out.println(listaFornecedores[i].toString());
                System.out.println("--------------------------------------");
            }
        }
    }

    @Override
    public Fornecedor buscaFornecedor(String CNPJ) {
        for (Fornecedor fornecedor : listaFornecedores) {
            if (fornecedor != null && fornecedor.getCNPJ().equals(CNPJ)) {
                return fornecedor;
            }
        }
        System.out.println("Fornecedor não encontrado.");
        return null; // Se não encontrar, retorna null
    }
}
