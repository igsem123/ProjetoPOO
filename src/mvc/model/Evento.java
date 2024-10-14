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
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor com parâmetros
    public Evento(LocalDate dataEvento, Pessoa cerimonial, String igreja, String cartorio, Pessoa pessoaNoivo1, Pessoa pessoaNoivo2) {
        this.id = (totalEventos++);
        this.dataEvento = dataEvento;
        this.cerimonial = cerimonial;
        this.igreja = igreja;
        this.cartorio = cartorio;
        this.pessoaNoivo1 = pessoaNoivo1;
        this.pessoaNoivo2 = pessoaNoivo2;
        this.nomeDoEvento = gerarNomeDoEvento(pessoaNoivo1, pessoaNoivo2);
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
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

    private String gerarNomeDoEvento(Pessoa pessoaNoivo1, Pessoa pessoaNoivo2) {
        String nomeDoEvento = ("Casamento de " + pessoaNoivo1.getNome() + " e " + pessoaNoivo2.getNome() +" em " + this.getDataEvento());
        this.dataModificacao = LocalDateTime.now();

        return nomeDoEvento;
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
        sb.append("================== Evento ID {" + getId() + "} ==================\n");
        sb.append(String.format("Data do Evento      : %s\n", dataEvento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        sb.append(String.format("Cerimonial          : %s\n", cerimonial.getNome()));
        sb.append(String.format("Igreja              : %s\n", igreja));
        sb.append(String.format("Cartorio            : %s\n", cartorio));
        sb.append(String.format("Noivo(a)            : %s\n", pessoaNoivo1.getNome()));
        sb.append(String.format("Noivo(a)            : %s\n", pessoaNoivo2.getNome()));
        sb.append(String.format("Nome do Evento      : %s\n", nomeDoEvento));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("=====================================================\n");
        return sb.toString();
    }
}
