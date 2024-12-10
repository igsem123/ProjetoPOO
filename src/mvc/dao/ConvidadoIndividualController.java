package mvc.dao;

import mvc.model.ConvidadoIndividual;
import mvc.model.Evento;
import mvc.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConvidadoIndividualController implements ConvidadoIndividualDAO {
    public ArrayList<ConvidadoIndividual> convidados;
    private int totalConvidados;
    private EventoDAO eventoDAO;
    private PessoaDAO pessoaDAO;
    private ConvidadoFamiliaDAO convidadoFamiliaDAO;
    private ConvidadoIndividual convidadoObj;

    public ConvidadoIndividualController() {
        this.convidados = new ArrayList<>();
        this.totalConvidados = 0;
        this.convidadoFamiliaDAO = new ConvidadoFamiliaController();
        this.convidadoObj = new ConvidadoIndividual();
        this.eventoDAO = new EventoController();
        this.pessoaDAO = new PessoaController();
    }

    public ArrayList<ConvidadoIndividual> getConvidados() {
        String sql = "SELECT * FROM convidadoindividual";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                convidados.add(resultSetToConvidadoIndividual(rs));
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar convites individuais: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return convidados;
    }

    private ConvidadoIndividual resultSetToConvidadoIndividual(ResultSet rs) {
        ConvidadoIndividual convidado = new ConvidadoIndividual();
        try {
            convidado.setId(rs.getLong("id"));
            convidado.setPessoa(pessoaDAO.buscaPorId(rs.getLong("pessoaId")));
            convidado.setFamilia(convidadoFamiliaDAO.buscarPorId(rs.getLong("familiaId")));
            convidado.setEvento(eventoDAO.buscarPorId(rs.getLong("eventoId")));
            convidado.setParentesco(rs.getString("parentesco"));
            convidado.setConfirmacao(rs.getBoolean("confirmacao"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convidado;
    }

    // Criar
    public void criarConvidado(ConvidadoIndividual convidado) {
        // Verifica se o familiaId existe na tabela convidadofamilia
        String checkFamiliaSql = "SELECT id FROM convidadofamilia WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkFamiliaSql)) {
            checkStmt.setLong(1, convidado.getFamilia().getId());
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Erro: familiaId " + convidado.getFamilia().getId() + " não existe na tabela convidadofamilia.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO convidadoindividual (pessoaId, familiaId, eventoId, parentesco, confirmacao) VALUES (?, ?, ?, ?, ?)";

        // Inserção no banco de dados
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, convidado.getPessoa().getId());
            stmt.setLong(2, convidado.getFamilia().getId());
            stmt.setLong(3, convidado.getEvento().getId());
            stmt.setString(4, convidado.getParentesco());
            stmt.setBoolean(5, convidado.getConfirmacaoPrimitivo());
            stmt.execute();

            // Recupera o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    convidado.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do convidado individual.");
                }
            }

            System.out.println("\nConvite individual criado com sucesso: \n\n" + convidado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Confirmar presença
    public void confirmarConvidado(ConvidadoIndividual convidado) {
        String sql = "UPDATE convidadoindividual SET confirmacao = ? WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setLong(2, convidado.getId());
            stmt.executeUpdate();

            // Atualiza o estado do objeto
            convidado = buscarPorId(convidado.getId());

            System.out.println("\nPresença confirmada para o convidado: \n\n" + convidado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Confirmar ou desconfirmar presença pela pessoa que possui o convite
    public void confirmarPresencaPelaPessoa(ConvidadoIndividual convidado) {
        String sql = "UPDATE convidadoindividual SET confirmacao = ? WHERE pessoaId = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setLong(2, convidado.getPessoa().getId());
            stmt.executeUpdate();

            // Atualiza o estado do objeto
            convidado = buscarPorPessoaId(convidado.getPessoa().getId());

            // Exibe mensagem conforme o novo estado
            System.out.println("\nPresença confirmada, seu convite: \n\n" + convidado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconfirmarPresencaPelaPessoa(ConvidadoIndividual convidadoDesconfirmado) {
        String sql = "UPDATE convidadoindividual SET confirmacao = ? WHERE pessoaId = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, false);
            stmt.setLong(2, convidadoDesconfirmado.getPessoa().getId());
            stmt.execute();

            // Atualiza o estado do objeto
            convidadoDesconfirmado = buscarPorPessoaId(convidadoDesconfirmado.getPessoa().getId());

            // Exibe mensagem conforme o novo estado
            System.out.println("\nPresença desconfirmada, seu convite: \n\n" + convidadoDesconfirmado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Buscar por ID
    public ConvidadoIndividual buscarPorId(long id) {
        String sql = "SELECT * FROM convidadoindividual WHERE id = ?";
        ConvidadoIndividual convidado = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                convidado = resultSetToConvidadoIndividual(rs);
            }

            return convidado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Buscar convite por pessoa 'ID'
    public ConvidadoIndividual buscarPorPessoaId(long pessoaId) {
        String sql = "SELECT * FROM convidadoindividual WHERE pessoaId = ?";
        ConvidadoIndividual convidado = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pessoaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                convidado = resultSetToConvidadoIndividual(rs);
            }

            return convidado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Buscar Todos
    public ArrayList<ConvidadoIndividual> buscarTodos(Evento eventoDoRecadoConv) {
        // ArrayList do tamanho exato dos convidados válidos conforme o evento fornecido
        ArrayList<ConvidadoIndividual> convidadosValidos = new ArrayList<>();
        String sql = "SELECT * FROM convidadoindividual WHERE eventoId = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, eventoDoRecadoConv.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                convidadosValidos.add(resultSetToConvidadoIndividual(rs));
            }

            if (convidadosValidos.isEmpty()) {
                System.out.println("\nNão há convidados para o evento informado.");
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar convidados.");
            throw new RuntimeException(e);
        }

        return convidadosValidos;
    }

    //Buscar Todos Confirmados
    public ArrayList<ConvidadoIndividual> buscarTodosConfirmados(Evento eventoDoRecadoConv) {
        // ArrayList do tamanho exato dos convidados válidos conforme o evento fornecido
        ArrayList<ConvidadoIndividual> convidadosValidos = new ArrayList<>();
        String sql = "SELECT * FROM convidadoindividual WHERE eventoId = ? AND confirmacao = true";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, eventoDoRecadoConv.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                convidadosValidos.add(resultSetToConvidadoIndividual(rs));
            }

            if (convidadosValidos.isEmpty()) {
                System.out.println("\nNão há convidados confirmados para o evento informado.");
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar convidados.");
            throw new RuntimeException(e);
        }

        return convidadosValidos;
    }

    // Atualizar
    public void atualizarConvidado(long id, ConvidadoIndividual convidadoAtualizado) {
        String sql = "UPDATE convidadoindividual SET pessoaId = ?, familiaId = ?, eventoId = ?, parentesco = ?, confirmacao = ? WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, convidadoAtualizado.getPessoa().getId());
            stmt.setLong(2, convidadoAtualizado.getFamilia().getId());
            stmt.setLong(3, convidadoAtualizado.getEvento().getId());
            stmt.setString(4, convidadoAtualizado.getParentesco());
            stmt.setBoolean(5, convidadoAtualizado.getConfirmacaoPrimitivo());
            stmt.setLong(6, id);
            stmt.execute();

            // Atualize o estado do objeto
            convidadoAtualizado = buscarPorId(id);

            System.out.println("\nConvidado atualizado com sucesso: \n\n" + convidadoAtualizado.toString());
        } catch (SQLException e) {
            System.out.println("\nErro ao atualizar convidado.");
            throw new RuntimeException(e);
        }
    }

    // Remover
    public void removerConvidado(long id) {
        String sql = "DELETE FROM convidadoindividual WHERE id = ?";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();

            System.out.println("\nConvidado removido com sucesso.");
        } catch (SQLException e) {
            System.out.println("\nErro ao remover convidado.");
            throw new RuntimeException(e);
        }
    }

    // Listar todos
    public void listarConvidados() {
        String sql = "SELECT * FROM convidadoindividual";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nID: [" + rs.getLong("id") + "]" +
                        "\nNome: " + pessoaDAO.buscaPorId(rs.getLong("pessoaId")).getNome() +
                        "\nFamília: " + convidadoFamiliaDAO.buscarPorId(rs.getLong("familiaId")).getNomeFamilia() +
                        "\nEvento: " + eventoDAO.buscarPorId(rs.getLong("eventoId")).getNomeDoEvento() +
                        "\nParentesco: " + rs.getString("parentesco") +
                        "\nConfirmado: " + convidadoObj.isConfirmacao(rs.getBoolean("confirmacao")));
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao listar convidados.");
            throw new RuntimeException(e);
        }
    }

    // Exibir lista simples
    public void exibirConvidadosSimples() {
        String sql = "SELECT * FROM convidadoindividual";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nLista de convidados: ");
            while (rs.next()) {
                System.out.println("ID: [" + rs.getLong("id") + "] - Nome: " + pessoaDAO.buscaPorId(rs.getLong("pessoaId")).getNome());
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao exibir convidados.");
            throw new RuntimeException(e);
        }
    }

    // Exibir lista por evento
    public void exibirConvidadosPorEvento(Long idEvento) {
        String sql = "SELECT * FROM convidadoindividual WHERE eventoId = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idEvento);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nLista de convidados para o evento: ");
            while (rs.next()) {
                System.out.println("\nID: [" + rs.getLong("id") + "] - Nome: " + pessoaDAO.buscaPorId(rs.getLong("pessoaId")).getNome() + " - Parentesco: " + rs.getString("parentesco")
                        + " - Confirmado: " + rs.getBoolean("confirmacao") + " - Evento: " + eventoDAO.buscarPorId(rs.getLong("eventoId")).getNomeDoEvento());
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao exibir convidados.");
            throw new RuntimeException(e);
        }
    }
}
