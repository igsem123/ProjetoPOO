package mvc.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

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

    // Getters e Setters para acessar os atributos
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
        this.dataModificacao = LocalDateTime.now(); // Atualiza a data de modificação
    }

    // Exemplo de como verificar se o pagamento está completo
    public boolean isPagoCompleto() {
        // Implementação para verificar se todas as parcelas foram pagas
        return this.parcela == 0;
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

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}