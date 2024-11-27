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
    }

    // Método auxiliar para converter ResultSet em Fornecedor
    Fornecedor resultSetToFornecedor(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
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
        return fornecedor;
    }
    
    @Override
    public boolean criarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO Fornecedor (nome, CNPJ, telefone, email, valorAPagar, valorParcela, parcelas, estado, valorInicial, parcelaInicial, totalParcelasPagas) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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

            stmt.execute();
            System.out.println("Fornecedor cadastrado com sucesso:\n" + fornecedor);
            return true;
        } catch (SQLException e) {
            System.out.println("\nErro ao cadastrar fornecedor.");
            e.printStackTrace();
            return false;
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
            System.out.println("\nErro ao buscar fornecedor por ID.");
            e.printStackTrace();
        }
        return fornecedor;
    }

    @Override
    public void atualizarFornecedor(String CNPJ) {
        String sql = "UPDATE Fornecedor SET nome = ?, telefone = ?, email = ?, valorAPagar = ?, valorParcela = ?, parcelas = ?, estado = ?, valorInicial = ?, parcelaInicial = ?, totalParcelasPagas = ? WHERE CNPJ = ?";

        Fornecedor fornecedor = buscaFornecedor(CNPJ); // Busca pelo CNPJ para verificar se existe
        if (fornecedor != null) {
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, fornecedor.getNome());
                stmt.setString(2, fornecedor.getTelefone());
                stmt.setString(3, fornecedor.getEmail());
                stmt.setDouble(4, fornecedor.getValorAPagar());
                stmt.setDouble(5, fornecedor.getValorParcela());
                stmt.setInt(6, fornecedor.getParcelas());
                stmt.setString(7, fornecedor.getEstado());
                stmt.setDouble(8, fornecedor.getValorInicial());
                stmt.setInt(9, fornecedor.getParcelaInicial());
                stmt.setInt(10, fornecedor.getTotalParcelasPagas());
                stmt.setString(11, CNPJ);

                stmt.executeUpdate();
                System.out.println("Fornecedor atualizado com sucesso: " + fornecedor.getNome());
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
            System.out.println("Fornecedor deletado com sucesso.");
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
            System.out.println("\nFornecedores cadastrados:");
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

        if (fornecedor == null) {
            System.out.println("\nFornecedor não encontrado para o CNPJ: " + CNPJ);
        }
        return fornecedor;
    }

}
