package mvc.dao;

import mvc.controller.Main;
import mvc.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class PessoaController implements PessoaDAO {
    private final ArrayList<Pessoa> listaPessoas;

    public PessoaController() {
        this.listaPessoas = new ArrayList<>();
    }

    // Metodo do ResultSet para evitar repeticao de codigo
    private Pessoa resultSetToPessoa(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getLong("id"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setSexo(rs.getString("sexo"));
        pessoa.setDataNascimentoDB(rs.getString("dataNascimento"));
        pessoa.setTelefone(rs.getString("telefone"));
        pessoa.setEmail(rs.getString("email"));
        pessoa.setSenha(rs.getString("senha"));
        pessoa.setTipoUsuario(rs.getInt("tipoUsuario"));
        pessoa.setCpf(rs.getString("cpf"));
        return pessoa;
    }

    @Override
    public void criarPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, sexo, dataNascimento, telefone, email, senha, tipoUsuario, cpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

            pessoa.setId(buscaPessoa(pessoa.getCpf()).getId()); // Pega o ‘ID’ gerado no banco e seta no objeto

            System.out.println("\nPessoa cadastrada com sucesso:\n\n" + pessoa.toString());
        } catch (SQLException e) {
            System.out.println("\nErro ao cadastrar pessoa.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizarPessoa(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, sexo = ?, dataNascimento = ?, telefone = ?, email = ?, senha = ?, tipoUsuario = ?, cpf = ? WHERE id = ?";

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
    public void deletarPessoa(String cpf) {
        String sql = "DELETE FROM pessoa WHERE cpf = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.execute();

            System.out.println("\n" + buscaPessoa(cpf).getNome());
            System.out.println("\nPessoa deletada com sucesso.");
        } catch (SQLException e) {
            System.out.println("\nErro ao deletar pessoa.");
            throw new RuntimeException(e);
        }
    }

    public void listarPessoas() {
        String sql = "SELECT * FROM pessoa";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.execute();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaPessoas.add(resultSetToPessoa(rs));
            }

            for (Pessoa pessoa : listaPessoas) {
                System.out.println("\nUsuário de [ID]: " + pessoa.getId() + " - Nome: " + pessoa.getNome() + " - CPF: " + pessoa.getCpf() + " - Tipo de usuário: " + pessoa.getTipoUsuario());
            }

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

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pessoa = resultSetToPessoa(rs);
            }

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

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pessoa = resultSetToPessoa(rs);
            }

            if (pessoa == null) {
                System.out.println("\nPessoa não encontrada.");
                return null;
            } else {
                return pessoa;
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar pessoa.");
            throw new RuntimeException(e);
        }
    }

    public void buscaCerimonialistas() {
        String sql = "SELECT * FROM pessoa WHERE tipoUsuario = 2";
        ArrayList<Pessoa> cerimonialistas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.execute();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cerimonialistas.add(resultSetToPessoa(rs));
            }

            for (Pessoa cerimonialista : cerimonialistas) {
                System.out.println("Cerimonialista de [ID]: " + cerimonialista.getId() + " - Nome: " + cerimonialista.getNome());
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar cerimonialistas.");
            throw new RuntimeException(e);
        }
    }

    public void buscaNoivos() {
        String sql = "SELECT * FROM pessoa WHERE tipoUsuario = 1";
        ArrayList<Pessoa> noivos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            stmt.execute();

            while (rs.next()) {
                noivos.add(resultSetToPessoa(rs));
            }

            for (Pessoa noivo : noivos) {
                System.out.println("Noivo(a) de [ID]: " + noivo.getId() + " - Nome: " + noivo.getNome());
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar noivo(a).");
            throw new RuntimeException(e);
        }
    }

    public void buscaConvidados() {
        String sql = "SELECT * FROM pessoa WHERE tipoUsuario = 4";
        ArrayList<Pessoa> convidados = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            stmt.execute();

            while (rs.next()) {
                convidados.add(resultSetToPessoa(rs));
            }

            for (Pessoa convidado : convidados) {
                System.out.println("Convidado(a) de [ID]: " + convidado.getId() + " - Nome: " + convidado.getNome());
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar convidados.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pessoa buscaPorId(Long id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa pessoa = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pessoa = resultSetToPessoa(rs);
            }

            rs.close();
            stmt.close();
            return pessoa;
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar pessoa.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterarNome(String cpf, String novoNome) {
        String sql = "UPDATE pessoa SET nome = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, novoNome);
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nNome atualizado com sucesso.");
                System.out.println("Nome atual: " + novoNome);
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar nome.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void alterarSexo(String cpf, String novoSexo) {
        String sql = "UPDATE pessoa SET sexo = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, novoSexo);
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nSexo atualizado com sucesso.");
                System.out.println("Sexo atual: " + novoSexo);
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar sexo.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void alterarNascimento(String cpf, String novoNascimento) {
        String sql = "UPDATE pessoa SET dataNascimento = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, LocalDate.parse(novoNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nData de nascimento atualizada com sucesso.");
                System.out.println("Data de nascimento atual: " + novoNascimento);
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar data de nascimento.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void alterarEmail(String cpf, String novoEmail) {
        String sql = "UPDATE pessoa SET email = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, novoEmail);
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nEmail atualizado com sucesso.");
                System.out.println("Email atual: " + novoEmail);
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar email.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void alterarSenha(String cpf, String novaSenha) {
        String sql = "UPDATE pessoa SET senha = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, novaSenha);
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nSenha atualizada com sucesso.");
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar senha.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void alterarTipoUsuario(String cpf, int novoTipo) {
        // Caso o tipo de usuário seja 3, é necessário informar a senha master
        if (novoTipo == 3) {
            System.out.println("\nTipo de usuário escolhido: 3 - Administrador.");
            System.out.println("Para criar um novo administrador, é necessário informada a senha master do sistema.");
            System.out.println("Digite a senha master:");
            String senhaMaster = new java.util.Scanner(System.in).nextLine();

            if (!Objects.equals(senhaMaster, Main.SENHA_ADMIN)) {
                System.out.println("\nSenha master incorreta.");
                return;
            } else {
                System.out.println("\nSenha master correta.");
            }
        }

        // Senão for administrador, atualiza o tipo de usuário normalmente
        String sql = "UPDATE pessoa SET tipoUsuario = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setInt(1, novoTipo);
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nTipo de usuário atualizado com sucesso.");
                System.out.println("Tipo de usuário atual: " + novoTipo);
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar tipo de usuário.");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void alterarCpf(String cpf, String novoCpf) {
        String sql = "UPDATE pessoa SET cpf = ? WHERE cpf = ?";
        Pessoa pessoa = buscaPessoa(cpf);

        if (pessoa != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, novoCpf);
                stmt.setString(2, cpf);
                stmt.execute();

                System.out.println("\nCPF atualizado com sucesso.");
                System.out.println("CPF atual: " + novoCpf);
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar CPF.");
                throw new RuntimeException(e);
            }
        }
    }
}
