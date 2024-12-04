package mvc.dao;

import mvc.model.Fornecedor;
import mvc.model.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorController implements FornecedorDAO {
    private final ArrayList<Fornecedor> listaFornecedores;
    private Util util = new Util();

    public FornecedorController() {
        this.listaFornecedores = new ArrayList<>();
        this.getTotalFornecedores();
    }

    // Metodo auxiliar para converter ResultSet em Fornecedor
    Fornecedor resultSetToFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = null;
        try {
            fornecedor = new Fornecedor();
            fornecedor.setId(rs.getLong("id"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setCNPJ(rs.getString("CNPJ"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEmail(rs.getString("email"));
            fornecedor.setValorAPagar(rs.getDouble("valorAPagar"));
            fornecedor.setValorParcela(rs.getDouble("valorParcela"));
            fornecedor.setParcelas(rs.getInt("parcelas"));
            fornecedor.setEstado(rs.getString("estado"));
            fornecedor.setValorInicial(rs.getDouble("valorInicial"));
            fornecedor.setParcelaInicial(rs.getInt("parcelaInicial"));
            fornecedor.setTotalParcelasPagas(rs.getInt("totalParcelasPagas"));
        } catch (SQLException e) {
            System.out.println("\nErro ao converter dados do banco em fornecedor.");
            throw new RuntimeException(e);
        }
        return fornecedor;
    }

    public int getTotalFornecedores() {
        String sql = "SELECT COUNT(*) FROM Fornecedor";
        int totalFornecedores = 0;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);){
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalFornecedores = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar total de fornecedores.");
            e.printStackTrace();
        }
        return totalFornecedores;
    }

    public ArrayList<Fornecedor> getFornecedores() {
        String sql = "SELECT * FROM Fornecedor";
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fornecedores.add(resultSetToFornecedor(rs));
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar fornecedores.");
            e.printStackTrace();
        }
        return fornecedores;
    }
    
    @Override
    public void criarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO Fornecedor (nome, CNPJ, telefone, email, valorAPagar, valorParcela, parcelas, estado, valorInicial, parcelaInicial, totalParcelasPagas) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCNPJ());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setDouble(5, fornecedor.getValorAPagar());
            stmt.setDouble(6, fornecedor.getValorParcela());
            stmt.setInt(7, fornecedor.getParcelas());
            stmt.setString(8, fornecedor.getEstado());
            stmt.setDouble(9, fornecedor.getValorInicial());
            stmt.setInt(10, fornecedor.getParcelaInicial());
            stmt.setInt(11, fornecedor.getTotalParcelasPagas());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fornecedor.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o [ID] do fornecedor.");
                }
            }

            System.out.println("\nFornecedor cadastrado com sucesso:\n" + fornecedor);
        } catch (SQLException e) {
            System.out.println("\nErro ao cadastrar fornecedor.");
            e.printStackTrace();
        }
    }
    
    @Override
    public Fornecedor buscaPorId(Long id) {
        String sql = "SELECT * FROM Fornecedor WHERE id = ?";
        Fornecedor fornecedor = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fornecedor = resultSetToFornecedor(rs);
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar fornecedor por [ID].");
            e.printStackTrace();
        }
        return fornecedor;
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedorEditado) {
        String sql = "UPDATE Fornecedor SET nome = ?, CNPJ = ?, telefone = ?, email = ?, valorAPagar = ?, valorParcela = ?, parcelas = ?, estado = ?, valorInicial = ?, parcelaInicial = ?, totalParcelasPagas = ? WHERE id = ?";

        if (fornecedorEditado != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, fornecedorEditado.getNome());
                stmt.setString(2, fornecedorEditado.getCNPJ());
                stmt.setString(3, fornecedorEditado.getTelefone());
                stmt.setString(4, fornecedorEditado.getEmail());
                stmt.setDouble(5, fornecedorEditado.getValorAPagar());
                stmt.setDouble(6, fornecedorEditado.getValorParcela());
                stmt.setInt(7, fornecedorEditado.getParcelas());
                stmt.setString(8, fornecedorEditado.getEstado());
                stmt.setDouble(9, fornecedorEditado.getValorInicial());
                stmt.setInt(10, fornecedorEditado.getParcelaInicial());
                stmt.setInt(11, fornecedorEditado.getTotalParcelasPagas());
                stmt.setLong(12, fornecedorEditado.getId());

                stmt.executeUpdate();
                System.out.println("Fornecedor atualizado com sucesso: " + fornecedorEditado.getNome());
            } catch (SQLException e) {
                System.out.println("\nErro ao atualizar fornecedor.");
                e.printStackTrace();
            }
        } else {
            System.out.println("\nFornecedor não encontrado para o CNPJ fornecido.");
        }
    }

    @Override
    public void deletarFornecedor(Long id) {
        String sql = "DELETE FROM Fornecedor WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.execute();
            System.out.println("\nFornecedor deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println("\nErro ao deletar fornecedor.");
            e.printStackTrace();
        }
    }

    public void listarFornecedores() {
        String sql = "SELECT * FROM Fornecedor";
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fornecedores.add(resultSetToFornecedor(rs));
            }

            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor);
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao listar fornecedores.");
            e.printStackTrace();
        }
    }

    public void exibeFornecedoresSimples() {
        String sql = "SELECT id, CNPJ, nome FROM Fornecedor";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            System.out.println("Fornecedores cadastrados:");
            while (rs.next()) {
                System.out.println(
                    rs.getLong("id") + " - " +
                    util.formataCnpj(rs.getString("CNPJ")) + " - " +
                    rs.getString("nome")
                );
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao exibir fornecedores.");
            e.printStackTrace();
        }
    }

    @Override
    public Fornecedor buscaFornecedor(String CNPJ) {
        String sql = "SELECT * FROM Fornecedor WHERE CNPJ = ?";
        Fornecedor fornecedor = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, CNPJ);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fornecedor = resultSetToFornecedor(rs);
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar fornecedor pelo CNPJ.");
            e.printStackTrace();
        }

        return fornecedor;
    }

}
