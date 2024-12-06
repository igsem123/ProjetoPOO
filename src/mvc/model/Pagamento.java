package mvc.model;
import mvc.dao.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pagamento {

    private long id;
    public static int serial = 0;
    private Pessoa pessoa;
    private Fornecedor fornecedor;
    private String descricao;
    private double valor;
    private int parcela;
    private boolean agendado;
    private LocalDate dataPagamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private PessoaDAO pessoaDAO = new PessoaController();
    private FornecedorDAO fornecedorDAO = new FornecedorController();
    private PagamentoDAO pagamentoDAO = new PagamentoController();

    // Construtor
    public Pagamento(Pessoa pessoa, Fornecedor fornecedor, String descricao, double valor, int parcela, boolean agendado, LocalDate dataPagamento) {
        this.id = (serial++);
        this.pessoa = pessoa;
        this.fornecedor = fornecedor;
        this.descricao = descricao;
        this.valor = valor;
        this.parcela = parcela;
        this.agendado = agendado;
        this.dataPagamento = dataPagamento;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public Pagamento() {
        // Construtor vazio
    }

    // Getters e Setters para acessar os atributos
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
        this.dataModificacao = LocalDateTime.now(); // Atualiza a data de modificação
    }

    public boolean isPagoCompleto() {
        return this.parcela == 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.dataModificacao = LocalDateTime.now();
        this.pessoa = pessoa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.dataModificacao = LocalDateTime.now();
        this.fornecedor = fornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.dataModificacao = LocalDateTime.now();
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.dataModificacao = LocalDateTime.now();
        this.valor = valor;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.dataModificacao = LocalDateTime.now();
        this.parcela = parcela;
    }

    public boolean isAgendado() {
        return agendado;
    }

    public void setAgendado(boolean agendado) {
        this.dataModificacao = LocalDateTime.now();
        this.agendado = agendado;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================= Pagamento ID {" + getId() + "} =================\n");
        sb.append(String.format("Quem fez o pagamento     : %s\n", this.getPessoa().getNome()));
        sb.append(String.format("Quem recebeu o pagamento : %s\n", this.getFornecedor().getNome()));
        sb.append(String.format("Descrição do pagamento   : %s\n", descricao));
        sb.append(String.format("Valor pago               : %.2f\n", valor));
        sb.append(String.format("Parcelas pagas           : %d\n", parcela));
        sb.append(String.format("Foi agendado             : %s\n", pagamentoDAO.getAgendamento(getId())));
        sb.append(String.format("Data do pagamento        : %s\n", dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        sb.append(String.format("Data de Criação          : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação      : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("====================================================\n");
        return sb.toString();
    }


}
