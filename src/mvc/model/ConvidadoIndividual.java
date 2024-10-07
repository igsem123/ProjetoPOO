package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ConvidadoIndividual {
    public long id;
    public static int totalConvidados = 0;
    Pessoa pessoa;
    public String familia;
    public String parentesco;
    public boolean confirmacao; // false = não confirmado, true = confirmado
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor com parâmetros
    public ConvidadoIndividual(Pessoa pessoa, String familia, String parentesco) {
        this.id = (totalConvidados++);
        this.pessoa = pessoa;
        this.familia = familia;
        this.parentesco = parentesco;
        this.confirmacao = false; // inicialmente falso
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    // Construtor vazio
    public ConvidadoIndividual() {
        this.id = (totalConvidados++);
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public long getId() {
        return this.id;
    }

    public Pessoa getPessoa() { return pessoa; }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.dataModificacao = LocalDateTime.now();
    }

    public String getFamilia() { return familia; }

    public void setFamilia(String familia) {
        this.familia = familia;
        this.dataModificacao = LocalDateTime.now();
    }

    public String getParentesco() { return parentesco; }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
        this.dataModificacao = LocalDateTime.now();
    }

    public boolean isConfirmacao() {
        if (this.confirmacao) {
            System.out.println("Confirmado!");
        } else {
            System.out.println("Cancelado!");
        }

        return this.confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
        this.dataModificacao = LocalDateTime.now(); // Atualiza a data de modificação
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
                "\n| Familia            : " + this.familia +
                "\n| Parentesco         : " + this.parentesco +
                "\n| Confirmacao        : " + this.dataCriacao +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=============== Convidado ID {" + getId() + "} ===============\n");
        sb.append(String.format("Nome                : %s\n", pessoa.getNome()));
        sb.append(String.format("Familia             : %s\n", familia));
        sb.append(String.format("Parentesco          : %s\n", parentesco));
        sb.append(String.format("Confirmacao         : %s\n", confirmacao));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao));
        sb.append("=====================================================\n");
        return sb.toString();
    }
}
