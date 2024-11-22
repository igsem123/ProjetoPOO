package mvc.dao;

import mvc.model.Pessoa;
import mvc.model.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PessoaController implements PessoaDAO {
    private ArrayList<Pessoa> listaPessoas;

    public PessoaController() {
        this.listaPessoas = new ArrayList<>();
    }

    @Override
    public boolean criarPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, sexo, dataNascimento, telefone, login, senha, tipoUsuario, cpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, LocalDate.parse(pessoa.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getEmail());
            stmt.setString(6, pessoa.getSenha());
            stmt.setInt(7, pessoa.getTipoUsuario());
            stmt.setString(8, pessoa.getCpf());
            stmt.execute();

            System.out.println("\nPessoa cadastrada com sucesso:\n\n" + pessoa.toString());
            return true;
        } catch (SQLException e) {
            System.out.println("\nErro ao cadastrar pessoa.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizarPessoa(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, sexo = ?, dataNascimento = ?, telefone = ?, login = ?, senha = ?, tipoUsuario = ?, cpf = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, LocalDate.parse(pessoa.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getEmail());
            stmt.setString(6, pessoa.getSenha());
            stmt.setInt(7, pessoa.getTipoUsuario());
            stmt.setString(8, pessoa.getCpf());
            stmt.setLong(9, pessoa.getId());
            stmt.execute();

            System.out.println("\nPessoa atualizada com sucesso:\n\n" + pessoa.toString());
        } catch (SQLException e) {
            System.out.println("\nErro ao atualizar pessoa.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletarPessoa(String cpf) {
        String sql = "DELETE FROM pessoa WHERE cpf = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.execute();

            System.out.println("\nPessoa deletada com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("\nErro ao deletar pessoa.");
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pessoa> listarPessoas() {
        String sql = "SELECT * FROM pessoa";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.execute();
            return listaPessoas;
        } catch (SQLException e) {
            System.out.println("\nErro ao listar pessoas.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pessoa buscaPessoaLogin(String email, String senha) {
        String sql = "SELECT * FROM pessoa WHERE email = ? AND senha = ?";
        Pessoa pessoa = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.execute();

            System.out.println("\nPessoa encontrada com sucesso.");
            return pessoa;
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar pessoa.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pessoa buscaPessoa(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        Pessoa pessoa = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.execute();

            System.out.println("\nPessoa encontrada com sucesso.");
            return pessoa;
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar pessoa.");
            throw new RuntimeException(e);
        }
    }

    public void buscaCerimonialistas() {
        String sql = "SELECT * FROM pessoa WHERE tipo_usuario = 2";
        ArrayList<Pessoa> cerimonialistas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.execute();
            System.out.println("\nCerimonialistas encontrados com sucesso. Lista de cerimonialistas:");
            System.out.println(cerimonialistas);
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar cerimonialistas.");
            throw new RuntimeException(e);
        }
    }

    public void buscaNoivos() {
        /*if (totalPessoas == 0) {
            System.out.println("\nNenhum noivo(a) cadastrado(a).");
        } else {
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa != null && pessoa.getTipoUsuario() == 1) {
                    System.out.println("Noivo(a): " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
                }
            }
        }*/
    }

    public void buscaConvidados() {
        /*if (totalPessoas == 0) {
            System.out.println("\nNenhum convidado cadastrado.");
        } else {
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa != null && pessoa.getTipoUsuario() == 4) {
                    System.out.println("Convidado(a): " + pessoa.getNome() + " cadastrado sob o ID: [" + pessoa.getId() + "]");
                }
            }
        }*/
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
