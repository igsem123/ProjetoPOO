package mvc.dao;

import mvc.model.Pessoa;
import mvc.model.Presentes;

import java.sql.*;
import java.util.ArrayList;

public class PresentesController implements PresentesDAO{
    public ArrayList<Presentes> presentes;

    public PresentesController() {
        this.presentes = popularListaPresentes();
    }

    public ArrayList<Presentes> popularListaPresentes() {
        String SQL = "SELECT * FROM presentes";
        ArrayList<Presentes> presentes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Presentes presente = resultSetsToPresentes(rs);
                presentes.add(presente);
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar lista de presentes: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return presentes;
    }

    // Metodo para auxiliar na conversão de ResultSet em Presentes
    public Presentes resultSetsToPresentes(ResultSet rs) throws SQLException {
        Presentes presente = null;
        try {
            presente = new Presentes();
            presente.setId(rs.getLong("id"));
            presente.setNome(rs.getString("nome"));
            presente.setTipo(rs.getInt("tipo"));
            presente.setValor(rs.getDouble("valor"));
            
            Timestamp dataCriacaoTimestamp = rs.getTimestamp("dataCriacao");
            Timestamp dataModificacaoTimestamp = rs.getTimestamp("dataModificacao");
            if (dataCriacaoTimestamp != null) {
                presente.setDataCriacao(dataCriacaoTimestamp.toLocalDateTime());
            }
            if (dataModificacaoTimestamp != null) {
                presente.setDataModificacao(dataModificacaoTimestamp.toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao converter dados do banco em presente.");
            throw new RuntimeException(e);
        }

        return presente;
    }

    @Override
    public void adicionarPresente(Presentes presente) {
        String SQL = "INSERT INTO presentes (nome, tipo, valor) VALUES (?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, presente.getNome());
            stmt.setInt(2, presente.getTipo());
            stmt.setDouble(3, presente.getValor());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                presente.setId(rs.getLong(1));
            }

            System.out.println("\nPresente adicionado com sucesso: \n\n" + presente.toString());

        } catch (SQLException e) {
            System.out.println("\nErro ao adicionar presente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Presentes buscarPorId(long id) {
        String SQL = "SELECT * FROM presentes WHERE id = ?";
        Presentes presente = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                presente = resultSetsToPresentes(rs);
            }

        } catch (SQLException e) {
            System.out.println("\nErro ao buscar presente por ID: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return presente;
    }

    @Override
    public void darPresente(long id, Pessoa pessoa) {
        String selectSQL = "SELECT * FROM presentes WHERE id = ?";
        String SQL = "UPDATE presentes SET pessoaId = ? WHERE id = ?";

        // Verifica se o presente já foi presenteado
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getLong("pessoaId") != 0 || rs.getString("nomePessoa") != null) {
                    System.out.println("\nEste presente já foi presenteado aos noivos por: " + rs.getString("nomePessoa"));
                    System.out.println("Escolha outro presente para dar aos noivos :)!");
                    return;
                }
            } else {
                System.out.println("\nPresente não encontrado com o ID: " + id);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Atualiza o presente com o ‘ID’ da pessoa que deu o presente
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setLong(1, pessoa.getId());
            stmt.setLong(2, id);
            stmt.execute();
            System.out.println("\nPresente registrado aos noivos com sucesso!!");
        } catch (SQLException e) {
            System.out.println("\nErro ao dar presente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void darPresente(long id, String nomePessoa) {
        String selectSQL = "SELECT * FROM presentes WHERE id = ?";
        String SQL = "UPDATE presentes SET nomePessoa = ? WHERE id = ?";

        // Verifica se o presente já foi presenteado
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("nomePessoa") != null) {
                    System.out.println("\nEste presente já foi presenteado aos noivos por: " + rs.getString("nomePessoa"));
                    System.out.println("Escolha outro presente para dar aos noivos :)!");
                    return;
                }
            } else {
                System.out.println("\nPresente não encontrado com o ID: " + id);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Atualiza o presente com o nome da pessoa que deu o presente
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, nomePessoa);
            stmt.setLong(2, id);
            stmt.execute();
            System.out.println("\nPresente registrado aos noivos com sucesso!!");
        } catch (SQLException e) {
            System.out.println("\nErro ao dar presente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exibeListaPresentesSimples() {
        String SQL = "SELECT id, nome, valor, pessoaId, nomePessoa FROM presentes";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nID: [" + rs.getLong("id") + "] Presente: " + rs.getString("nome") + "\nValor: " + rs.getDouble("valor"));
                if (rs.getLong("pessoaId") != 0) {
                    System.out.println("-> Este presente já foi presenteado aos noivos por: " + rs.getLong("pessoaId"));
                } else if(rs.getString("nomePessoa") != null) {
                	System.out.println("-> Este presente já foi presenteado aos noivos por: " + rs.getString("nomePessoa"));
                }
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao exibir lista de presentes: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizarPresente(long id, Presentes presenteAtualizado) {
        String SQL = "UPDATE presentes SET nome = ?, tipo = ?, valor = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(1, presenteAtualizado.getNome());
            stmt.setInt(2, presenteAtualizado.getTipo());
            stmt.setDouble(3, presenteAtualizado.getValor());
            stmt.setLong(4, id);
            stmt.execute();
            System.out.println("\nPresente atualizado com sucesso: \n\n" + presenteAtualizado.toString());
        } catch (SQLException e) {
            System.out.println("\nErro ao atualizar presente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removerPresente(long id) {
        String SQL = "DELETE FROM presentes WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            stmt.execute();
            System.out.println("\nPresente removido com sucesso.");
        } catch (SQLException e) {
            System.out.println("\nErro ao remover presente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
