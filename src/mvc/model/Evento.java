package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Evento {
    public long id;
    public static int totalEventos = 0;
    private LocalDate dataEvento;
    Pessoa cerimonial;
    public String igreja;
    public String cartorio;
    Pessoa pessoaNoivo1;
    Pessoa pessoaNoivo2;
    private String nomeDoEvento;
    private Fornecedor[] fornecedores;
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor com parâmetros
    public Evento(LocalDate dataEvento, Pessoa cerimonial, String igreja, String cartorio, Pessoa pessoaNoivo1, Pessoa pessoaNoivo2, Fornecedor[] fornecedores) {
        this.id = (totalEventos++);
        this.dataEvento = dataEvento;
        this.cerimonial = cerimonial;
        this.igreja = igreja;
        this.cartorio = cartorio;
        this.pessoaNoivo1 = pessoaNoivo1;
        this.pessoaNoivo2 = pessoaNoivo2;
        this.fornecedores = fornecedores;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
        this.nomeDoEvento = gerarNomeDoEvento(pessoaNoivo1, pessoaNoivo2);
    }

    // Construtor vazio
    public Evento() {
        this.id = (totalEventos++);
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return this.id;
    }

    public String getDataEvento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataEvento.format(formatter);
    }

    public void setDataEvento(String dataEvento) {
        this.dataModificacao = LocalDateTime.now();
        this.dataEvento = Util.formataData(dataEvento);
    }

    public String gerarNomeDoEvento(Pessoa pessoaNoivo1, Pessoa pessoaNoivo2) {
        if (pessoaNoivo1 == null || pessoaNoivo2 == null || dataEvento == null) {
            return "Evento sem nome";
        }
        String nomeDoEvento = "Casamento de " + pessoaNoivo1.getNome() + " e " + pessoaNoivo2.getNome() + " em " + getDataEvento();
        this.dataModificacao = LocalDateTime.now();
        return nomeDoEvento;
    }

    public void setNomeDoEvento(String nomeDoEvento) {
        this.dataModificacao = LocalDateTime.now();
        this.nomeDoEvento = nomeDoEvento;
    }

    public Pessoa getCerimonial() {
        return cerimonial;
    }

    public void setCerimonial(Pessoa cerimonial) {
        this.cerimonial = cerimonial;
    }

    public String getIgreja() {
        return igreja;
    }

    public void setIgreja(String igreja) {
        this.igreja = igreja;
    }

    public String getCartorio() {
        return cartorio;
    }

    public void setCartorio(String cartorio) {
        this.cartorio = cartorio;
    }

    public Pessoa getPessoaNoivo1() {
        return pessoaNoivo1;
    }

    public void setPessoaNoivo1(Pessoa pessoaNoivo1) {
        this.pessoaNoivo1 = pessoaNoivo1;
    }

    public Pessoa getPessoaNoivo2() {
        return pessoaNoivo2;
    }

    public void setPessoaNoivo2(Pessoa pessoaNoivo2) {
        this.pessoaNoivo2 = pessoaNoivo2;
    }

    public String getNomeDoEvento() {
        return nomeDoEvento;
    }

    public void addFornecedor(Fornecedor fornecedor) { // Adiciona um fornecedor à lista de fornecedores
        this.dataModificacao = LocalDateTime.now();
        if (this.fornecedores == null) {
            this.fornecedores = new Fornecedor[]{fornecedor}; // Se a lista de fornecedores for nula, cria um novo array com um único fornecedor
        } else {
            Fornecedor[] novoFornecedores = new Fornecedor[this.fornecedores.length + 1]; // Cria um novo array com tamanho maior
            System.arraycopy(this.fornecedores, 0, novoFornecedores, 0, this.fornecedores.length); // Copia os elementos do array antigo para o novo array criado por meio do arraycopy que é mais eficiente, indico o array de origem, a posição de início, o array de destino, a posição de início e a quantidade de elementos a serem copiados, sendo todos os elementos do array antigo
            novoFornecedores[this.fornecedores.length] = fornecedor; // Adiciona o novo fornecedor ao final do novo array
            this.fornecedores = novoFornecedores; // Atualiza a lista de fornecedores
        }
    }

    public void clearFornecedores() { // Limpa a lista de fornecedores
        this.fornecedores = new Fornecedor[0]; // Cria um novo array vazio
        this.dataModificacao = LocalDateTime.now();
    }

    public Fornecedor[] getFornecedores() {
        return fornecedores;
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
        Evento that = (Evento) o;
        return dataEvento.equals(that.dataEvento) &&
                cerimonial.equals(that.cerimonial) &&
                igreja.equals(that.igreja) &&
                cartorio.equals(that.cartorio) &&
                pessoaNoivo1 == that.pessoaNoivo1 &&
                pessoaNoivo2 == that.pessoaNoivo2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.dataEvento);
        hash = 23 * hash + Objects.hashCode(this.cerimonial);
        hash = 23 * hash + Objects.hashCode(this.igreja);
        hash = 23 * hash + Objects.hashCode(this.cartorio);
        hash = 23 * hash + Objects.hashCode(this.pessoaNoivo1);
        hash = 23 * hash + Objects.hashCode(this.pessoaNoivo2);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================== Evento ID {").append(getId()).append("} ==================\n");
        sb.append(String.format("Data do Evento      : %s\n", dataEvento != null ? dataEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A"));
        sb.append(String.format("Cerimonial          : %s\n", cerimonial != null ? cerimonial.getNome() : "N/A"));
        sb.append(String.format("Igreja              : %s\n", igreja != null ? igreja : "N/A"));
        sb.append(String.format("Cartorio            : %s\n", cartorio != null ? cartorio : "N/A"));
        sb.append(String.format("Noivo(a)            : %s\n", pessoaNoivo1 != null ? pessoaNoivo1.getNome() : "N/A"));
        sb.append(String.format("Noivo(a)            : %s\n", pessoaNoivo2 != null ? pessoaNoivo2.getNome() : "N/A"));
        sb.append(String.format("Nome do Evento      : %s\n", nomeDoEvento != null ? nomeDoEvento : "N/A"));
        sb.append("Fornecedores        :\n");
        if (fornecedores != null) {
            for (Fornecedor fornecedor : fornecedores) {
                sb.append(String.format("  - %s\n", fornecedor.getNome()));
            }
        }
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao != null ? dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "N/A"));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao != null ? dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "N/A"));
        sb.append("=====================================================\n");
        return sb.toString();
    }
}
