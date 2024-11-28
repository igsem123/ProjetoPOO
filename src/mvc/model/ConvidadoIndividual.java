package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ConvidadoIndividual {
    public long id;
    public static int totalConvidados = 0;
    Pessoa pessoa;
    ConvidadoFamilia familia;
    Evento evento;
    public String parentesco;
    public boolean confirmacao; // false = não confirmado, true = confirmado
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor com parâmetros
    public ConvidadoIndividual(Pessoa pessoa, ConvidadoFamilia familia, Evento evento, String parentesco) {
        this.id = pessoa.getId();
        this.pessoa = pessoa;
        this.evento = evento;
        this.familia = familia;
        this.parentesco = parentesco;
        this.confirmacao = false; // inicialmente falso
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    // Construtor com confirmação para ex da lista
    public ConvidadoIndividual(Pessoa pessoa, ConvidadoFamilia familia, Evento evento, String parentesco, boolean confirmacao) {
        this.id = pessoa.getId();
        this.pessoa = pessoa;
        this.evento = evento;
        this.familia = familia;
        this.parentesco = parentesco;
        this.confirmacao = confirmacao;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    // Construtor vazio
    public ConvidadoIndividual() {
        this.id = (totalConvidados++);
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    // Getters e Setters
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public Pessoa getPessoa() { return pessoa; }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.id = pessoa.getId();
    }

    public Evento getEvento() { return evento; }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public ConvidadoFamilia getFamilia() { return familia; }

    public void setFamilia(ConvidadoFamilia familia) {
        this.familia = familia;
    }

    public String getParentesco() { return parentesco; }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String isConfirmacao(boolean confirmacao) {
        if (confirmacao) {
            return "Confirmado(a) no evento!";
        } else {
            return "Presença não confirmada no evento!";
        }
    }

    public boolean getConfirmacaoPrimitivo() {
        return this.confirmacao;
    }

    public String getConfirmacao() {
        String confirmacaoString;
        if (this.confirmacao) {
            confirmacaoString = "Confirmado(a)";
            return confirmacaoString;
        } else {
            confirmacaoString = "Não confirmado(a)";
            return confirmacaoString;
        }
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
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
        ConvidadoIndividual that = (ConvidadoIndividual) o;
        return id == that.id &&
                pessoa.equals(that.pessoa) &&
                familia.equals(that.familia) &&
                parentesco.equals(that.parentesco) &&
                confirmacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.pessoa);
        hash = 23 * hash + Objects.hashCode(this.familia);
        hash = 23 * hash + Objects.hashCode(this.parentesco);
        hash = 23 * hash + Objects.hashCode(this.confirmacao);
        return hash;
    }

    public String perfil() {
        return "\n|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| Perfil do Convidado: " + this.pessoa.getNome() + "\n|\n|" +
                "\n| ID                 : " + this.id +
                "\n| Familia            : " + this.familia.getNomeFamilia() +
                "\n| Parentesco         : " + this.parentesco +
                "\n| Confirmacao        : " + getConfirmacao() +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================== Convidado ID {" + getId() + "} ==================\n");
        sb.append(String.format("Nome                : %s\n", pessoa.getNome()));
        sb.append(String.format("Familia             : %s\n", familia.getNomeFamilia()));
        sb.append(String.format("Parentesco          : %s\n", parentesco));
        sb.append(String.format("Confirmacao         : %s\n", getConfirmacao()));
        sb.append(String.format("Evento convidado    : %s\n", evento.getNomeDoEvento()));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("======================================================\n");
        return sb.toString();
    }
}
