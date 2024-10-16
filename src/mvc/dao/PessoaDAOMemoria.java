package mvc.dao;

import mvc.model.ConvidadoIndividual;
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

        // Noivos
        Pessoa noivo1 = new Pessoa("Raphael Nathan Moreira", "Masculino", Util.formataData("04/05/1999"),
                "+55 34 9 9222-8686", "rnathan@gmail.com", "12345678", 1, "000.786.666-02");
        this.criarPessoa(noivo1);

        Pessoa noivo2 = new Pessoa("Lucas Tobias de Sousa Machado", "Masculino", Util.formataData("28/03/1994"),
                "+55 34 9 9227-8858", "lucastobias@gmail.com", "lucas123", 1, "785.147.542-01");
        this.criarPessoa(noivo2);

        Pessoa noivo3 = new Pessoa("Ana Paula Ferreira", "Feminino", Util.formataData("15/12/1993"),
                "+55 34 9 9988-7766", "ana.paula@gmail.com", "ana123", 1, "324.567.789-11");
        this.criarPessoa(noivo3);

        Pessoa noivo4 = new Pessoa("Carlos Alberto Pereira", "Masculino", Util.formataData("23/09/1990"),
                "+55 34 9 9876-5432", "carlos.pereira@gmail.com", "carlos456", 1, "213.456.789-12");
        this.criarPessoa(noivo4);

        // Cerimonialistas
        Pessoa cerimonialista1 = new Pessoa("Fernanda Silva", "Feminino", Util.formataData("18/07/1985"),
                "+55 34 9 7788-6655", "fernanda.silva@gmail.com", "cerimonial123", 2, "555.666.777-88");
        this.criarPessoa(cerimonialista1);

        Pessoa cerimonialista2 = new Pessoa("Gabriel Antunes", "Masculino", Util.formataData("05/05/1989"),
                "+55 34 9 2233-4455", "gabriel.antunes@gmail.com", "gabriel456", 2, "222.333.444-55");
        this.criarPessoa(cerimonialista2);

        // Administradores
        Pessoa administrador2 = new Pessoa("José Roberto", "Masculino", Util.formataData("02/02/1980"),
                "+55 34 9 5555-9999", "jose.admin@gmail.com", "admin456", 3, "444.555.666-77");
        this.criarPessoa(administrador2);

        Pessoa p3 = new Pessoa("Admin", "Sem Gênero", Util.formataData("14/08/2024"),
                "+55 34 9 2222-5555", "admin", "admin", 3, "123.456.789-10");
        this.criarPessoa(p3);

        // Convidados
        Pessoa convidado1 = new Pessoa("Sofia Mendes", "Feminino", Util.formataData("27/03/2000"),
                "+55 34 9 2234-5678", "sofia.mendes@gmail.com", "sofia123", 4, "987.123.456-78");
        this.criarPessoa(convidado1);

        Pessoa convidado2 = new Pessoa("Ricardo Barbosa", "Masculino", Util.formataData("14/04/1987"),
                "+55 34 9 3344-5566", "ricardo.barbosa@gmail.com", "ricardo123", 4, "654.321.987-00");
        this.criarPessoa(convidado2);

        Pessoa convidado3 = new Pessoa("Camila Rocha", "Feminino", Util.formataData("10/10/1995"),
                "+55 34 9 4455-6677", "camila.rocha@gmail.com", "camila456", 4, "123.321.456-98");
        this.criarPessoa(convidado3);

        Pessoa p4 = new Pessoa("Adivâina dos Reis", "Feminino", Util.formataData("17/03/1972"),
                "+55 34 9 3333-5555", "adivaina@gmail.com", "adivaina123", 4, "855.777.666-32");
        this.criarPessoa(p4);

        Pessoa p5 = new Pessoa("Jessica Moreira", "Feminino", Util.formataData("30/06/1992"),
                "+55 34 9 4444-7777", "jessica@gmail.com", "jessica123", 4, "789.456.123-00");
        this.criarPessoa(p5);

        Pessoa p6 = new Pessoa("Anderson Alvarenga", "Masculino", Util.formataData("01/05/1998"),
                "+55 34 9 1111-8888", "anderson@gmail.com", "anderson123", 4, "111.222.333-44");
        this.criarPessoa(p6);

        Pessoa p7 = new Pessoa("Adriana Tobias Machado", "Feminino", Util.formataData("10/11/1976"),
                "+55 34 9 7777-9999", "adriana@gmail.com", "adriana123", 4, "147.852.012-98");
        this.criarPessoa(p7);

        Pessoa p8 = new Pessoa("João das Neves", "Masculino", LocalDate.of(1990, 1, 1),
                "+55 34 9978-8852","joao@email.com" , "joao123", 1, "123.456.789-00");
        this.criarPessoa(p8);

        Pessoa p9 = new Pessoa("Maria Oliveira", "Feminino", LocalDate.of(1995, 2, 12),
                "+55 34 9125-4550","maria@email.com" , "maria123", 1, "987.654.321-00");
        this.criarPessoa(p9);
    }

    @Override
    public boolean criarPessoa(Pessoa pessoa) {
        if (totalPessoas < TAMANHO_MAXIMO) {
            listaPessoas[totalPessoas] = pessoa;
            totalPessoas++;
            System.out.println("Pessoa cadastrada com sucesso:\n\n" + pessoa.toString());
            return true;
        } else {
            System.out.println("\nErro: Não há espaço para mais pessoas.");
            return false;
        }
    }

    @Override
    public void atualizarPessoa(Pessoa pessoa) {
        for (int i = 0; i < totalPessoas; i++) {
            if (listaPessoas[i].getId() == pessoa.getId()) {
                listaPessoas[i] = pessoa;
                pessoa.setDataModificacao(LocalDateTime.now());
                return;
            }
        }
    }

    @Override
    public boolean deletarPessoa(String cpf) {
        for (int i = 0; i < totalPessoas; i++) {
            if (listaPessoas[i].getCpf().equals(cpf)) {
                for (int j = i; j < totalPessoas - 1; j++) {
                    listaPessoas[j] = listaPessoas[j + 1];  // Move os itens para trás no array reorganizando
                }
                totalPessoas--;
                return true;
            }
        }
        return false;
    }

    public void listarPessoas() {
        if (totalPessoas == 0) {
            System.out.println("\nNenhuma pessoa cadastrada.");
        } else {
            for (int i = 0; i < totalPessoas; i++) {
                System.out.println(listaPessoas[i].toString());
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
        if (totalPessoas == 0) {
            System.out.println("\nNenhum cerimonialista cadastrado(a).");
        } else {
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa != null && pessoa.getTipoUsuario() == 2) {
                    System.out.println("Cerimonialista: " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
                }
            }
        }
    }

    public void buscaNoivos() {
        if (totalPessoas == 0) {
            System.out.println("\nNenhum noivo(a) cadastrado(a).");
        } else {
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa != null && pessoa.getTipoUsuario() == 1) {
                    System.out.println("Noivo(a): " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
                }
            }
        }
    }

    public void buscaConvidados() {
        if (totalPessoas == 0) {
            System.out.println("\nNenhum convidado cadastrado.");
        } else {
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa != null && pessoa.getTipoUsuario() == 4) {
                    System.out.println("Convidado(a): " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
                }
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
