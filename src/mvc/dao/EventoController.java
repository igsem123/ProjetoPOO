package mvc.dao;

import mvc.model.Evento;
import mvc.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;

public class EventoController implements EventoDAO {
    private final ArrayList<Evento> eventos;
    private final PessoaDAO pessoaDAO = new PessoaController();
    private final FornecedorDAO fornecedorDAO = new FornecedorController();
    public static int totalEventos = 0;

    public EventoController() {
        this.eventos = new ArrayList<>();
        this.contarTotalDeEventos();
    }

    // Método auxiliar para converter ResultSet em Evento
    private Evento resultSetToEvento(ResultSet rs) throws SQLException {
        Evento evento = new Evento();
        evento.setId(rs.getLong("id"));
        evento.setDataEvento(rs.getDate("dataEvento").toLocalDate());
        evento.setCerimonial(new PessoaController().buscaPorId(rs.getLong("cerimonial")));
        evento.setIgreja(rs.getString("igreja"));
        evento.setCartorio(rs.getString("cartorio"));
        evento.setPessoaNoivo1(new PessoaController().buscaPorId(rs.getLong("pessoaNoivo1")));
        evento.setPessoaNoivo2(new PessoaController().buscaPorId(rs.getLong("pessoaNoivo2")));
        evento.setNomeDoEvento(rs.getString("nomeDoEvento"));
        return evento;
    }

    public int contarTotalDeEventos() {
        String sql = "SELECT COUNT(*) AS total FROM Evento";
        totalEventos = 0;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalEventos = rs.getInt("total");
            }

            return totalEventos;

        } catch (SQLException e) {
            System.out.println("\nErro ao contar total de eventos.");
            e.printStackTrace();
        }

        return totalEventos;
    }
    
    @Override
    public void criarEvento(Evento evento) {
        String sqlEvento = "INSERT INTO Evento (dataEvento, cerimonial, igreja, cartorio, pessoaNoivo1, pessoaNoivo2, nomeDoEvento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlEvento, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(LocalDate.parse(evento.getDataEvento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()));
            stmt.setObject(2, evento.getCerimonial() != null ? evento.getCerimonial().getId() : null);
            stmt.setString(3, evento.getIgreja());
            stmt.setString(4, evento.getCartorio());
            stmt.setLong(5, evento.getPessoaNoivo1().getId());
            stmt.setLong(6, evento.getPessoaNoivo2().getId());
            stmt.setString(7, evento.getNomeDoEvento());

            stmt.executeUpdate();

            // Recupera o ID gerado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                evento.setId(generatedKeys.getLong(1));
            }

            // Adiciona os fornecedores associados
            if (evento.getFornecedores() != null) {
                for (Fornecedor fornecedor : evento.getFornecedores()) {
                    adicionarFornecedorAoEvento(evento.getId(), fornecedor.getId());
                }
            }

            System.out.println("\nEvento cadastrado com sucesso:\n\n" + evento);

        } catch (SQLException e) {
            System.out.println("\nErro ao cadastrar evento.");
            e.printStackTrace();
        }
    }
    
    @Override
    public Evento buscarPorId(long id) {
        String sql = "SELECT * FROM Evento WHERE id = ?";
        Evento evento = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                evento = resultSetToEvento(rs);

                // Busca os fornecedores associados
                ArrayList<Fornecedor> fornecedores = buscarFornecedoresDoEvento(id);
                for (Fornecedor fornecedor : fornecedores) {
                    evento.addFornecedor(fornecedor);
                }
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao buscar evento por ID.");
            e.printStackTrace();
        }

        return evento;
    }
    
    @Override
    public void atualizarEvento(long id, Evento eventoAtualizado) {
        String sql = "UPDATE Evento SET dataEvento = ?, cerimonial = ?, igreja = ?, cartorio = ?, pessoaNoivo1 = ?, pessoaNoivo2 = ?, nomeDoEvento = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.parse(eventoAtualizado.getDataEvento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()));
            stmt.setObject(2, eventoAtualizado.getCerimonial() != null ? eventoAtualizado.getCerimonial().getId() : null);
            stmt.setString(3, eventoAtualizado.getIgreja());
            stmt.setString(4, eventoAtualizado.getCartorio());
            stmt.setLong(5, eventoAtualizado.getPessoaNoivo1().getId());
            stmt.setLong(6, eventoAtualizado.getPessoaNoivo2().getId());
            stmt.setString(7, eventoAtualizado.getNomeDoEvento());
            stmt.setLong(8, id);

            stmt.executeUpdate();

            // Atualiza fornecedores associados
            removerFornecedoresDoEvento(id); // Remove todos os fornecedores associados
            if (eventoAtualizado.getFornecedores() != null) {
                for (Fornecedor fornecedor : eventoAtualizado.getFornecedores()) {
                    adicionarFornecedorAoEvento(id, fornecedor.getId());
                }
            }

            System.out.println("\nEvento atualizado com sucesso:\n\n" + eventoAtualizado);

        } catch (SQLException e) {
            System.out.println("\nErro ao atualizar evento.");
            e.printStackTrace();
        }
    }
    
    @Override
    public void removerEvento(long id) {
        String sql = "DELETE FROM Evento WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Remove fornecedores associados
            removerFornecedoresDoEvento(id);

            // Remove o evento
            stmt.setLong(1, id);
            stmt.executeUpdate();

            System.out.println("\nEvento removido com sucesso.");

        } catch (SQLException e) {
            System.out.println("\nErro ao remover evento.");
            e.printStackTrace();
        }
    }
    
    public void listarEventos() {
        String sql = "SELECT * FROM Evento";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Evento evento = resultSetToEvento(rs);
                System.out.println(evento);
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao listar eventos.");
            e.printStackTrace();
        }
    }
    
    private void adicionarFornecedorAoEvento(long eventoId, long fornecedorId) throws SQLException {
        String sql = "INSERT INTO Evento_Fornecedor (eventoId, fornecedorId) VALUES (?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, eventoId);
            stmt.setLong(2, fornecedorId);
            stmt.executeUpdate();
        }
    }

    private void removerFornecedoresDoEvento(long eventoId) throws SQLException {
        String sql = "DELETE FROM Evento_Fornecedor WHERE eventoId = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, eventoId);
            stmt.executeUpdate();
        }
    }
    
    private ArrayList<Fornecedor> buscarFornecedoresDoEvento(long eventoId) throws SQLException {
        String sql = "SELECT f.* FROM Fornecedor f JOIN Evento_Fornecedor ef ON f.id = ef.fornecedorId WHERE ef.eventoId = ?";
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, eventoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new FornecedorController().resultSetToFornecedor(rs);
                fornecedores.add(fornecedor);
            }
        }

        return fornecedores;
    }

    public void exibirListaEventosSimples() {
        String sql = "SELECT e.id, e.dataEvento, e.igreja, "
                   + "n1.nome AS noivo1, n2.nome AS noivo2 "
                   + "FROM Evento e "
                   + "JOIN Pessoa n1 ON e.pessoaNoivo1 = n1.id "
                   + "JOIN Pessoa n2 ON e.pessoaNoivo2 = n2.id";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum evento cadastrado no sistema!");
            } else {
                while (rs.next()) {
                    System.out.println(
                        rs.getLong("id") + " - " +
                        rs.getDate("dataEvento").toLocalDate() + " - " +
                        rs.getString("igreja") + " - " +
                        rs.getString("noivo1") + " - " +
                        rs.getString("noivo2")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao exibir lista de eventos.");
            e.printStackTrace();
        }
    }

}