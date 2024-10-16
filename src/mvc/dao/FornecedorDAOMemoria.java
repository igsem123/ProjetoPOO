package mvc.dao;

import mvc.model.Fornecedor;

import java.time.LocalDateTime;

public class FornecedorDAOMemoria implements FornecedorDAO {
    private static final int TAMANHO_MAXIMO = 50;
    private final Fornecedor[] listaFornecedores;
    private int totalFornecedores;

    public FornecedorDAOMemoria() {
        this.listaFornecedores = new Fornecedor[TAMANHO_MAXIMO];
        this.totalFornecedores = 0;

        // Criando fornecedores de exemplo
        Fornecedor f1 = new Fornecedor("Empresa XYZ", "12.345.678/0001-90", "(11) 99999-8888",
                10000.0, 8, "Não Pago", "contato@empresaxyz.com");
        this.criarFornecedor(f1);

        Fornecedor f2 = new Fornecedor("Fotografia ABC", "98.765.432/0001-10", "(21) 88888-7777",
                20000.0, 12, "Pago", "contato@fotografiabc.com");
        this.criarFornecedor(f2);

        Fornecedor f3 = new Fornecedor("Decorações Elegantes", "33.222.111/0001-33", "(31) 77777-6666",
                15000.0, 10, "Parcialmente Pago", "contato@decoracoes.com");
        this.criarFornecedor(f3);

        Fornecedor f4 = new Fornecedor("Buffet Gourmet", "44.333.222/0001-44", "(41) 66666-5555",
                25000.0, 15, "Não Pago", "contato@buffetgourmet.com");
        this.criarFornecedor(f4);
    }

    @Override
    public boolean criarFornecedor(Fornecedor fornecedor) {
        if (totalFornecedores < TAMANHO_MAXIMO) {
            listaFornecedores[totalFornecedores] = fornecedor;
            totalFornecedores++;
            System.out.println("Fornecedor cadastrado com sucesso:\n\n" + fornecedor);
            return true;
        } else {
            System.out.println("\nErro: Não há espaço para mais fornecedores.");
            return false;
        }
    }

    @Override
    public Fornecedor buscaPorId(Long id) {
        for (int i = 0; i < totalFornecedores; i++) {
            if (listaFornecedores[i].getId() == id) {
                return listaFornecedores[i];
            }
        }
        System.out.println("\nFornecedor não encontrada.");
        return null;
    }

    @Override
    public void atualizarFornecedor(String CNPJ) {
        Fornecedor fornecedor = buscaFornecedor(CNPJ);
        for (int i = 0; i < totalFornecedores; i++) {
            if (listaFornecedores[i].getId() == fornecedor.getId()) {
                listaFornecedores[i] = fornecedor;
                fornecedor.setDataModificacao(LocalDateTime.now());
                return;
            }
        }
        System.out.println("\nFornecedor não encontrado.");
    }

    @Override
    public void deletarFornecedor(Long id) {
        for (int i = 0; i < totalFornecedores; i++) {
            if (listaFornecedores[i].getId() == id) {
                for (int j = i; j < totalFornecedores - 1; j++) {
                    listaFornecedores[j] = listaFornecedores[j + 1];  // Move os itens para trás no array reorganizando
                }
                totalFornecedores--;
                System.out.println("\nFornecedor deletado com sucesso.");
                return;
            }
        }
        System.out.println("\nFornecedor não encontrado.");
    }

    public void listarFornecedores() {
        if (totalFornecedores == 0) {
            System.out.println("\nNenhum fornecedor cadastrado.");
        } else {
            for (int i = 0; i < totalFornecedores; i++) {
                System.out.println(listaFornecedores[i].toString());
            }
        }
    }

    public void exibeFornecedoresSimples() {
        if (totalFornecedores == 0) {
            System.out.println("\nNenhum fornecedor cadastrado.");
        } else {
            for (int i = 0; i < totalFornecedores; i++) {
                System.out.println(listaFornecedores[i].getId() + " - " + listaFornecedores[i].getCNPJ() + " - " + listaFornecedores[i].getNome());
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
        System.out.println("\nFornecedor não encontrado.");
        return null; // Se não encontrar, retorna null
    }
}
