package mvc.dao;

import mvc.model.Pessoa;
import mvc.model.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PessoaDAOMemoria implements PessoaDAO {
    private static final int TAMANHO_MAXIMO = 100;
    private final Pessoa[] listaPessoas;  // Simulação de armazenamento
    private int totalPessoas;

    public PessoaDAOMemoria() {
        this.listaPessoas = new Pessoa[TAMANHO_MAXIMO];
        this.totalPessoas = 0;

        Pessoa noivo1 = new Pessoa("Raphael Nathan Moreira", "Masculino", Util.formataData("04/05/1999"),
                "+55 34 9 9222-8686", "rnathan@gmail.com", "12345678", 1, "142.786.626-02");
        this.criarPessoa(noivo1);

        Pessoa noivo2 = new Pessoa("Lucas Tobias de Sousa Machado", "Masculino", Util.formataData("28/03/1994"),
                "+55 9 9227-8858", "lucastobias@gmail.com", "lucas123", 1, "785.147.542-01");
        this.criarPessoa(noivo2);

        Pessoa p3 = new Pessoa("Admin", "Sem Gênero", Util.formataData("14/08/2024"),
                "", "admin", "admin", 3, "123.456.789-10");
        this.criarPessoa(p3);

        Pessoa p4 = new Pessoa("Adivâina dos Reis", "Feminino", Util.formataData("17/03/1972"),
                "", "adivaina@gmail.com", "adivaina", 4, "855.777.666-32");
        this.criarPessoa(p4);

        Pessoa p5 = new Pessoa("Jessica Moreira", "Feminino", Util.formataData("30/06/1992"),
                "", "jessica@gmail.com", "jessica123", 4, "789.456.123-00");
        this.criarPessoa(p5);

        Pessoa p6 = new Pessoa("Anderson Alvarenga", "Masculino", Util.formataData("01/05/1998"),
                "", "anderson@gmail.com", "anderson123", 2, "111.222.333-44");
        this.criarPessoa(p6);

        Pessoa p7 = new Pessoa("Adriana Tobias Machado", "Feminino", Util.formataData("10/11/1976"),
                "", "adriana@gmail.com", "adriana", 2, "147.852.012-98");
        this.criarPessoa(p7);

        Pessoa p8 = new Pessoa("João das Neves", "Masculino", LocalDate.of(1990, 1, 1),
                "+55 34 9978-8852","joao@email.com" , "joao123", 1, "123.456.789-00");
        this.criarPessoa(p8);

        Pessoa p9 = new Pessoa("Maria das Neves", "Feminino", LocalDate.of(1995, 2, 12),
                "+55 34 9125-4550","maria@email.com" , "maria123", 1, "987.654.321-00");
        this.criarPessoa(p9);
    }

    @Override
    public boolean criarPessoa(Pessoa pessoa) {
        if (totalPessoas < TAMANHO_MAXIMO) {
            listaPessoas[totalPessoas] = pessoa;
            totalPessoas++;
            System.out.println("Pessoa cadastrada com sucesso:\n\n" + pessoa);
            return true;
        } else {
            System.out.println("Erro: Não há espaço para mais pessoas.");
            return false;
        }
    }

    @Override
    public void atualizarPessoa(Pessoa pessoa) {
        for (int i = 0; i < totalPessoas; i++) {
            if (listaPessoas[i].getId() == pessoa.getId()) {
                listaPessoas[i] = pessoa;
                pessoa.setDataModificacao(LocalDateTime.now());
                System.out.println("Pessoa atualizada: " + pessoa);
                return;
            }
        }
        System.out.println("Pessoa não encontrada.");
    }

    @Override
    public boolean deletarPessoa(String cpf) {
        for (int i = 0; i < totalPessoas; i++) {
            if (listaPessoas[i].getCpf().equals(cpf)) {
                for (int j = i; j < totalPessoas - 1; j++) {
                    listaPessoas[j] = listaPessoas[j + 1];  // Move os itens para trás no array
                }
                totalPessoas--;
                return true;
            }
        }
        return false;
    }

    public void listarPessoas() {
        if (totalPessoas == 0) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            for (int i = 0; i < totalPessoas; i++) {
                System.out.println(listaPessoas[i].toString());
                System.out.println("--------------------------------------");
            }
        }
    }

    @Override
    public Pessoa buscaPessoaLogin(String email, String senha) {
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa != null && pessoa.getEmail().equals(email) && pessoa.getSenha().equals(senha)) {
                return pessoa;
            }
        }
        return null;
    }

    @Override
    public Pessoa buscaPessoa(String cpf) {
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa != null && pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null; // Se não encontrar, retorna null
    }

    public void buscaCerimonialistas() {
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa != null && pessoa.getTipoUsuario() == 2){
                System.out.println("Cerimonialista: " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
            }
        }
    }

    public void buscaNoivos() {
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa != null && pessoa.getTipoUsuario() == 1){
                System.out.println("Noivo(a): " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
            }
        }
    }

    @Override
    public Pessoa buscaPorId(Long id) {
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa != null && pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null; // Se não encontrar, retorna null
    }

    @Override
    public boolean alterarNome(String cpf, String novoNome) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setNome(novoNome);
            return true; // Nome atualizado com sucesso
        }
        return false; // Pessoa não encontrada
    }

    @Override
    public boolean alterarSexo(String cpf, String novoSexo) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setSexo(novoSexo);
            return true; // Sexo atualizado com sucesso
        }
        return false; // Pessoa não encontrada
    }

    @Override
    public boolean alterarNascimento(String cpf, String novoNascimento) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setDataNascimento(novoNascimento);
            return true; // Data de nascimento atualizada com sucesso
        }
        return false; // Pessoa não encontrada
    }

    @Override
    public boolean alterarEmail(String cpf, String novoEmail) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setEmail(novoEmail);
            return true; // Email atualizado com sucesso
        }
        return false; // Pessoa não encontrada
    }

    @Override
    public boolean alterarSenha(String cpf, String novaSenha) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setSenha(novaSenha);
            return true; // Senha atualizada com sucesso
        }
        return false; // Pessoa não encontrada
    }

    @Override
    public boolean alterarTipoUsuario(String cpf, int novoTipo) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setTipoUsuario(novoTipo);
            return true; // Tipo de usuário atualizado com sucesso
        }
        return false; // Pessoa não encontrada
    }

    @Override
    public boolean alterarCpf(String cpf, String novaCpf) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa != null) {
            pessoa.setCpf(novaCpf);
            return true; // CPF atualizado com sucesso
        }
        return false; // Pessoa não encontrada
    }
}
