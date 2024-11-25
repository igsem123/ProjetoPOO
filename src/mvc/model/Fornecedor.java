package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Fornecedor {
    public long id;
    public static int totalFornecedores = 0;
    private String nome;
    private String CNPJ;
    private String telefone;
    private String email;
    private Double valorAPagar;
    private Double valorParcela;
    private int parcelas;
    private String estado;
    private Double valorInicial;
    private int parcelaInicial;
    private int totalParcelasPagas = 0;
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor com parâmetros
    public Fornecedor(String nome, String CNPJ, String telefone, Double valorAPagar, int parcelas, String estado, String email) {
        this.id = (totalFornecedores++);
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.telefone = telefone;
        this.email = email;
        this.valorAPagar = valorAPagar;
        this.parcelas = parcelas;
        this.estado = estado;
        this.valorParcela = criaValorDaParcela(valorAPagar, parcelas);
        this.valorInicial = valorAPagar;
        this.parcelaInicial = parcelas;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    // Construtor vazio
    public Fornecedor() {
        this.id = (totalFornecedores++);
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    private double criaValorDaParcela(Double valorAPagar, int parcelas) {
        Double valorParcela = valorAPagar / parcelas;
        return valorParcela;
    }

    public long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = Util.getDia();
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        this.dataModificacao = Util.getDia();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        this.dataModificacao = Util.getDia();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.dataModificacao = Util.getDia();
    }

    public double getValorAPagar() {
        return this.valorAPagar;
    }
    
    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorParcela(Double valorParcela) {
        this.dataModificacao = Util.getDia();
        this.valorParcela = valorParcela;
    }

    public int getParcelas() {
        return this.parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
        this.dataModificacao = Util.getDia();
    }

    public int getParcelaInicial() {
        return parcelaInicial;
    }
    
    public void setParcelaInicial(int parcelaInicial) {
        this.parcelaInicial = parcelaInicial;
    }

    public void setValorAPagar(Double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        this.dataModificacao = Util.getDia();
    }

    public String getDataCriacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataCriacao.format(formatter);
    }

    public String getDataModificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dataModificacao.format(formatter);
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = Util.getDia();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return id == that.id &&
                nome.equals(that.nome) &&
                CNPJ.equals(that.CNPJ) &&
                telefone.equals(that.telefone) &&
                email.equals(that.email) &&
                valorAPagar == that.valorAPagar &&
                parcelas == that.parcelas &&
                estado.equals(that.estado);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.CNPJ);
        hash = 23 * hash + Objects.hashCode(this.telefone);
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

    public String perfil() {
        return "\n|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| Perfil do Fornecedor: " + this.nome + "\n|\n|" +
                "\n| ID                  : " + this.id +
                "\n| CNPJ                : " + this.CNPJ +
                "\n| Telefone            : " + this.telefone +
                "\n| Email               : " + this.email +
                "\n| Data de Criação     : " + this.dataCriacao +
                "\n| Total Fornecedores  : " + Fornecedor.totalFornecedores +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================= Fornecedor ID {" + getId() + "} =================\n");
        sb.append(String.format("Nome                : %s\n", nome));
        sb.append(String.format("CNPJ                : %s\n", CNPJ));
        sb.append(String.format("Telefone            : %s\n", telefone));
        sb.append(String.format("Email               : %s\n", email));
        sb.append(String.format("Valor devido        : %.2f\n", valorAPagar));
        sb.append(String.format("Parcelas            : %d\n", parcelas));
        sb.append(String.format("Valor das Parcelas  : %.2f\n", valorParcela != null ? valorParcela : 0.0));
        sb.append(String.format("Parcelas Pagas      : %d\n", totalParcelasPagas));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("=====================================================\n");
        return sb.toString();
    }

    public int getTotalParcelasPagas() {
        return totalParcelasPagas;
    }

    public void setTotalParcelasPagas(int totalParcelasPagas) {
        this.totalParcelasPagas = totalParcelasPagas;
    }
}
