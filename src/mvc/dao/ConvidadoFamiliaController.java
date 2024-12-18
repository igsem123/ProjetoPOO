package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.view.GUI;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvidadoFamiliaController implements ConvidadoFamiliaDAO {
    private ArrayList<ConvidadoFamilia> listaFamilias;
    private ConvidadoFamiliaDAO convidadoFamiliaDAO;
    private ConvidadoIndividualDAO convidadoIndividualDAO;
    private EventoDAO eventoDAO;
    private Scanner scanner;

    public ConvidadoFamiliaController() {
        this.listaFamilias = new ArrayList<>();
        this.convidadoFamiliaDAO = this;
        this.scanner = new Scanner(System.in);
        this.eventoDAO = new EventoController();
    }

    public ArrayList<ConvidadoFamilia> getFamilias() {
        String sql = "SELECT * FROM convidadoFamilia";
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaFamilias.add(resultSetToConvidadoFamilia(rs));
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar convites família: " + e.getMessage());
            e.printStackTrace();
        }
        return listaFamilias;
    }

    private ConvidadoFamilia resultSetToConvidadoFamilia(ResultSet rs) throws SQLException {
        ConvidadoFamilia convidadoFamilia = new ConvidadoFamilia();
        try {
            convidadoFamilia.setId(rs.getLong("id"));
            convidadoFamilia.setNomeFamilia(rs.getString("nomeFamilia"));
            convidadoFamilia.setAcesso(rs.getString("acesso"));
            convidadoFamilia.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
            convidadoFamilia.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
            convidadoFamilia.setEvento(eventoDAO.buscarPorId(rs.getLong("eventoId")));
        } catch (SQLException e) {
            System.out.println("\nErro ao converter ResultSet para ConvidadoFamilia: " + e.getMessage());
            e.printStackTrace();
        }
        return convidadoFamilia;
    }

    public void criarFamilia(ConvidadoFamilia familia) {
        String sql = "INSERT INTO convidadofamilia (nomeFamilia, acesso, eventoId) VALUES (?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, familia.getNomeFamilia());
            stmt.setString(2, familia.getAcesso());
            stmt.setLong(3, familia.getEvento().getId());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                familia.setId(rs.getLong(1));
            }

            System.out.println("\nConvite família criado com sucesso: \n\n" + familia.toString());
        } catch (SQLException e) {
            System.out.println("\nErro ao criar convite família: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ConvidadoFamilia buscarPorId(long id) {
        String sql = "SELECT * FROM convidadoFamilia WHERE id = ?";
        ConvidadoFamilia familia = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                familia = resultSetToConvidadoFamilia(rs);
            }

            if (familia == null) {
                System.out.println("\nConvite família não encontrado.");
            }

            return familia;
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar convite família por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Buscar convites individuais para confirmar pela familia
    public void confirmarPresenca(String acessoFamilia, ArrayList<ConvidadoIndividual> convidadosIndividuais, ArrayList<ConvidadoFamilia> convidadosFamilia) {
        // Primeiro vou buscar se o acesso fornecido existe
        ConvidadoFamilia familia = null;
        int totalDeConvidadosInd = 0;

        for (ConvidadoFamilia convidadoFamilia : convidadosFamilia) {
            if (convidadoFamilia != null && convidadoFamilia.getAcesso().equals(acessoFamilia)) {
                familia = convidadoFamilia;
                break;
            }
        }

        if (familia == null) {
            System.out.println("\nAcesso inválido ou família não encontrada!");
            return;
        }

        String sql = "UPDATE convidadoIndividual SET confirmacao = ?, dataModificacao = ? WHERE familiaId = ?";

        // Agora que tenho a familia, preciso percorrer o vetor de convidados individuais e confirmar aqueles que estão atrelados à familia do acesso digitado
        for (ConvidadoIndividual convidado : convidadosIndividuais) {
            if (convidado != null && convidado.getFamilia() != null && convidado.getFamilia().getId() == familia.getId()) {

                try (Connection conn = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setBoolean(1, true);
                    stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                    stmt.setLong(3, familia.getId());
                    stmt.execute();
                    totalDeConvidadosInd++;
                } catch (SQLException e) {
                    System.out.println("\nErro ao confirmar presença de convidados individuais: " + e.getMessage());
                    e.printStackTrace();
                }

                System.out.println("\nConvidado individual " + convidado.getPessoa().getNome() + " confirmado com sucesso!");
            }
        }

        if (totalDeConvidadosInd == 0) {
            System.out.println("\nNão há nenhum convidado individual cadastrado nessa família para confirmar.");
        }
    }

    public void atualizarFamilia(long id, ConvidadoFamilia familiaAtualizada) {
        String sql = "UPDATE convidadoFamilia SET nomeFamilia = ?, acesso = ?, eventoId = ? WHERE id = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, familiaAtualizada.getNomeFamilia());
            stmt.setString(2, familiaAtualizada.getAcesso());
            stmt.setLong(3, familiaAtualizada.getEvento().getId());
            stmt.setLong(4, id);
            stmt.execute();
            System.out.println("\nConvite família atualizado com sucesso: \n\n" + familiaAtualizada.toString());
        } catch (SQLException e) {
            System.out.println("\nErro ao atualizar convite família: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removerFamilia(long id) {
        String sql = "DELETE FROM convidadoFamilia WHERE id = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
            System.out.println("\nConvite família removido com sucesso.");
        } catch (SQLException e) {
            System.out.println("\nErro ao remover convite família: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Mostrar todos os convites família por evento
    public void exibirFamiliasPorEvento(Long idEvento) {
        String sql = "SELECT * FROM convidadoFamilia WHERE eventoId = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, idEvento);
            stmt.execute();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Convite Família de ID: " + rs.getLong("id") + " - Nome da família: " + rs.getString("nomeFamilia") + " - Acesso: " + rs.getString("acesso"));
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao exibir convites família por evento: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Listar todos os convites família
    public void listarFamilias() {
        String sql = "SELECT * FROM convidadoFamilia";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.execute();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Convite Família de ID: " + rs.getLong("id") + " - Nome da família: " + rs.getString("nomeFamilia") + " - Acesso: " + rs.getString("acesso"));
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao listar convites família: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
