package mvc.dao;

import mvc.model.Pagamento;
import mvc.model.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PagamentoController implements PagamentoDAO{
    private final ArrayList<Pagamento> listaPagamentos;
    private PessoaDAO pessoaDAO;
    private FornecedorDAO fornecedorDAO;

    public PagamentoController(PessoaDAO pessoaDAO, FornecedorDAO fornecedorDAO) {
        this.listaPagamentos = new ArrayList<>();
        this.pessoaDAO = pessoaDAO;
        this.fornecedorDAO = fornecedorDAO;
    }

    public ArrayList<Pagamento> getPagamentos() {
        String SQL = "SELECT * FROM pagamento";
        ArrayList<Pagamento> listaPagamentos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listaPagamentos.add(resultSetToPagamento(rs));
            }

        } catch (Exception e) {
            System.out.println("\nErro ao buscar pagamentos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaPagamentos;
    }

    public String getAgendamento(long id) {
        boolean agendado = false;
        LocalDate dataPagamento = null;

        String agendamento = null;
        String SQL = "SELECT * FROM pagamento WHERE id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                agendado = rs.getBoolean("agendado");
                dataPagamento = rs.getDate("dataPagamento").toLocalDate();
            } else {
                System.out.println("Nenhum pagamento encontrado com o ID: " + id);
                return "Pagamento não encontrado!";
            }

        } catch (Exception e) {
            System.out.println("\nErro ao buscar agendamento: " + e.getMessage());
            e.printStackTrace();
        }

        if (agendado) {
            agendamento = "Agendado!";
            return agendamento;
        } else {
            assert dataPagamento != null;
            if (dataPagamento.isBefore(LocalDate.now())) {
                agendamento = "Pago com atraso!";
                return agendamento;
            } else if (dataPagamento.isEqual(LocalDate.now())) {
                agendamento = "Pago!";
                return agendamento;
            } else if (dataPagamento.isAfter(LocalDate.now())) {
                agendamento = "Pagamento próximo!";
                return agendamento;
            } else {
                agendamento = "Não agendado!";
                return agendamento;
            }
        }
    }

    public Pagamento resultSetToPagamento(ResultSet rs) {
        Pagamento pagamento = null;
        try {
            pagamento = new Pagamento();
            pagamento.setId(rs.getLong("id"));
            pagamento.setPessoa(pessoaDAO.buscaPorId(rs.getLong("pessoaId")));
            pagamento.setFornecedor(fornecedorDAO.buscaPorId(rs.getLong("fornecedorId")));
            pagamento.setDescricao(rs.getString("descricao"));
            pagamento.setValor(rs.getDouble("valor"));
            pagamento.setParcela(rs.getInt("parcela"));
            pagamento.setAgendado(rs.getBoolean("agendado"));
            pagamento.setDataPagamento(Util.timestampToLocalDate(rs.getTimestamp("dataPagamento")));
            pagamento.setDataCriacao(Util.timestampToLocalDateTime(rs.getTimestamp("dataCriacao")));
            pagamento.setDataModificacao(Util.timestampToLocalDateTime(rs.getTimestamp("dataModificacao")));
        } catch (Exception e) {
            System.out.println("\nErro ao converter ResultSet para Pagamento: " + e.getMessage());
            e.printStackTrace();
        }
        return pagamento;
    }

    public boolean criarPagamento(Pagamento pagamento) {
        String SQL = "INSERT INTO pagamento (pessoaId, fornecedorId, descricao, valor, parcela, agendado, dataPagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, pagamento.getPessoa().getId());
            preparedStatement.setLong(2, pagamento.getFornecedor().getId());
            preparedStatement.setString(3, pagamento.getDescricao());
            preparedStatement.setDouble(4, pagamento.getValor());
            preparedStatement.setInt(5, pagamento.getParcela());
            preparedStatement.setBoolean(6, pagamento.isAgendado());
            preparedStatement.setTimestamp(7, Util.localDateToTimestamp(pagamento.getDataPagamento()));
            preparedStatement.execute();
            return true;

        } catch (Exception e) {
            System.out.println("\nErro ao criar pagamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Pagamento buscarPagamentoPorId(long id) {
        String SQL = "SELECT * FROM pagamento WHERE id = ?";
        Pagamento pagamento = null;

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pagamento = resultSetToPagamento(rs);
            }

            return pagamento;

        } catch (Exception e) {
            System.out.println("\nErro ao buscar pagamento por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Pagamento> buscarTodos() {
        String SQL = "SELECT * FROM pagamento";
        listaPagamentos.clear();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listaPagamentos.add(resultSetToPagamento(rs));
            }

        } catch (Exception e) {
            System.out.println("\nErro ao buscar todos os pagamentos: " + e.getMessage());
            e.printStackTrace();
        }

        return listaPagamentos;
    }

    public boolean atualizarPagamento(Pagamento pagamentoAtualizado) {
        String SQL = "UPDATE pagamento SET pessoaId = ?, fornecedorId = ?, descricao = ?, valor = ?, parcela = ?, agendado = ?, dataPagamento = ? WHERE id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, pagamentoAtualizado.getPessoa().getId());
            preparedStatement.setLong(2, pagamentoAtualizado.getFornecedor().getId());
            preparedStatement.setString(3, pagamentoAtualizado.getDescricao());
            preparedStatement.setDouble(4, pagamentoAtualizado.getValor());
            preparedStatement.setInt(5, pagamentoAtualizado.getParcela());
            preparedStatement.setBoolean(6, pagamentoAtualizado.isAgendado());
            preparedStatement.setTimestamp(7, Util.localDateToTimestamp(pagamentoAtualizado.getDataPagamento()));
            preparedStatement.setLong(8, pagamentoAtualizado.getId());

            preparedStatement.execute();
            System.out.println("\nPagamento atualizado com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("\nErro ao atualizar pagamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerPagamento(long id) {
        String SQL = "DELETE FROM pagamento WHERE id = ?";

        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("\nPagamento removido com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("\nErro ao remover pagamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void listarPagamentos() {
        ArrayList<Pagamento> pagamentos = buscarTodos();
        int totalPagamentos = pagamentos.size();

        if (totalPagamentos == 0) {
            System.out.println("\nNenhum pagamento registrado!");
        } else {
            for (Pagamento pagamento : pagamentos) {
                if (pagamento != null) {
                    System.out.println(pagamento);
                }
            }
        }
    }

    public void exibirListaSimplesPagamentos() {
        ArrayList<Pagamento> pagamentos = buscarTodos();

        for (Pagamento pagamento : pagamentos) {
            if (pagamento != null) {
                System.out.println("ID do Pagamento [" + pagamento.getId() + "] - Pagador: " + pagamento.getPessoa().getNome() + " - Fornecedor: " + pagamento.getFornecedor().getNome());
            }
        }
    }
}
