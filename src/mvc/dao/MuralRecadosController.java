package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import mvc.model.*;

public class MuralRecadosController implements MuralRecadosDAO{
    private final ArrayList<MuralRecados> recadosCriados;
    public static int totalRecados = 0;

    public MuralRecadosController() {
        this.recadosCriados = new ArrayList<MuralRecados>();
    }
    
    private MuralRecados resultSetToMuralRecados(ResultSet rs) throws SQLException {
        MuralRecados recado = new MuralRecados();
        recado.setId(rs.getLong("id"));

        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getLong("pessoaId"));
        pessoa.setNome(rs.getString("nomePessoa"));
        recado.setPessoa(pessoa);

        long eventoId = rs.getLong("eventoId");
        EventoController eventoController = new EventoController();
        Evento evento = eventoController.buscarPorId(eventoId);
        recado.setEvento(evento);
				
        recado.setComentario(rs.getString("comentario"));
        
        return recado;
    }
    
    @Override
    public void criarRecado(MuralRecados recado) {
        String sql = "INSERT INTO muralrecados (pessoaId, nomePessoa, eventoId, comentario) VALUES (?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Define `pessoaId` como NULL se não for fornecido
            if (recado.getPessoa() != null) {
                stmt.setLong(1, recado.getPessoa().getId());
            } else {
                stmt.setNull(1, Types.BIGINT);
            }

            // Define o nome da pessoa
            if (recado.getPessoa() != null) {
                stmt.setString(2, recado.getPessoa().getNome());
            } else {
                stmt.setString(2, recado.getNomePessoa());
            }

            // Define o evento associado
            stmt.setLong(3, recado.getEvento().getId());

            // Define o comentário
            stmt.setString(4, recado.getComentario());

            // Executa a query
            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    recado.setId(rs.getLong(1));
                }
            }

            System.out.println("\nRecado criado com sucesso: \n\n" + recado);

        } catch (SQLException e) {
            System.err.println("Erro ao criar recado.");
            e.printStackTrace();
        }
    }


    @Override
    public MuralRecados buscarPorId(long id) {
        String sql = "SELECT * FROM muralrecados WHERE id = ?";
        MuralRecados recado = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    recado = resultSetToMuralRecados(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar recado por ID.");
            e.printStackTrace();
        }

        return recado;
    }

    @Override
    public ArrayList<MuralRecados> buscarTodosPorEvento(Evento evento) {
        String sql = "SELECT * FROM muralrecados WHERE eventoId = ?";
        ArrayList<MuralRecados> recadosList = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, evento.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    recadosList.add(resultSetToMuralRecados(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar recados por evento.");
            e.printStackTrace();
        }

        return recadosList;
    }
    
    @Override
    public MuralRecados buscaRecadoPorPessoa(Pessoa quemEscreveu) {
        String sql = "SELECT * FROM muralrecados WHERE pessoaId = ?";
        MuralRecados recado = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, quemEscreveu.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    if (quemEscreveu.getTipoUsuario() == 4) { // Tipo "Convidado"
                        recado = resultSetToMuralRecados(rs);
                        System.out.println("Convidado(a) que escreveu este recado: " + quemEscreveu.getNome());
                        System.out.println(recado);
                    } else {
                        System.out.println("Pessoa informada não é um convidado(a). Nome: " + quemEscreveu.getNome());
                    }
                } else {
                    System.out.println("Não foi encontrado recado desta pessoa: " + quemEscreveu.getNome());
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar recado por pessoa.");
            e.printStackTrace();
        }

        return recado;
    }

    @Override
    public void exibeListaDeRecados() {
        String sql = "SELECT * FROM muralrecados";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("Nenhum recado cadastrado.");
                    return;
                }

                while (rs.next()) {
                    System.out.println(resultSetToMuralRecados(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao exibir lista de recados.");
            e.printStackTrace();
        }
    }
    
    @Override
    public void exibeListaDeRecadosPorEvento(Evento evento) {
        String sql = "SELECT * FROM muralrecados WHERE eventoId = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, evento.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("\nNenhum recado encontrado para o evento: " + evento.getNomeDoEvento());
                    return;
                }

                System.out.println("\nRecados para o evento: " + evento.getNomeDoEvento() + "\n");
                while (rs.next()) {
                    MuralRecados recado = resultSetToMuralRecados(rs);
                    System.out.println(recado);
                }
            }

        } catch (SQLException e) {
            System.err.println("\nErro ao exibir lista de recados por evento.");
            e.printStackTrace();
        }
    }

    @Override
    public void editaRecado(MuralRecados recadoAtualizado) {
        String sql = "UPDATE muralrecados SET pessoaId = ?, nomePessoa = ?, comentario = ?, dataModificacao = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Atualiza o ID e nome da pessoa que comentou
            stmt.setLong(1, recadoAtualizado.getPessoa().getId());
            stmt.setString(2, recadoAtualizado.getPessoa().getNome());

            // Atualiza o comentário
            stmt.setString(3, recadoAtualizado.getComentario());

            // Identifica o recado pelo ID
            stmt.setLong(4, recadoAtualizado.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                recadoAtualizado = this.buscarPorId(recadoAtualizado.getId());
                System.out.println("\nRecado editado com sucesso: \n\n" + recadoAtualizado);
            } else {
                System.out.println("\nRecado não encontrado para edição.");
            }

        } catch (SQLException e) {
            System.err.println("\nErro ao editar recado.");
            e.printStackTrace();
        }
    }

    @Override
    public void removeRecado(MuralRecados recadoRemover) {
        String sql = "DELETE FROM muralrecados WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, recadoRemover.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nRecado removido com sucesso.");
            } else {
                System.out.println("\nRecado não encontrado para remoção.");
            }

        } catch (SQLException e) {
            System.err.println("\nErro ao remover recado.");
            e.printStackTrace();
        }
    }
    
}
