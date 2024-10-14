package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Presentes {
    private long id;
    public static int serial = 0;
    private String nome;
    private int tipo;
    private double valor;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor cheio
    public Presentes(String nome, int tipo, double valor) {
        this.id = (serial++);
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.pessoa = null;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public Presentes(Pessoa pessoa, String nome, int tipo, double valor) {
        this.id = (serial++);
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.pessoa = null;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.dataModificacao = LocalDateTime.now();
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.dataModificacao = LocalDateTime.now();
        this.tipo = tipo;
    }

    public String tipoDePresente(int tipo) {
        switch (tipo) {
            case 1:
                return "Cozinha/Geral";
            case 2:
                return "Cozinha/Eletrodomésticos";
            case 3:
                return "Decoração/Cama/Mesa/Banho";
            case 4:
                return "Moveis/Eletronicos";
            default:
                return "Dinheiro";
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.dataModificacao = LocalDateTime.now();
        this.valor = valor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.dataModificacao = LocalDateTime.now();
        this.pessoa = pessoa;
    }

    public String getDataCriacao() {
        return dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getDataModificacao() {
        return dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.tipo);
        hash = 23 * hash + Objects.hashCode(this.valor);
        hash = 23 * hash + Objects.hashCode(this.pessoa);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presentes presentes = (Presentes) o;
        return tipo == presentes.tipo && Double.compare(valor, presentes.valor) == 0 && Objects.equals(nome, presentes.nome) && Objects.equals(pessoa, presentes.pessoa);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("============= Presente ID {" + getId() + "} =============\n");
        sb.append(String.format("Nome do presente    : %s\n", nome));
        sb.append(String.format("Tipo de presente    : %s\n", tipoDePresente(getTipo())));
        sb.append(String.format("Valor do presente   : %.2f\n", valor));
        if (pessoa != null) { // Verificando se a pessoa é nula na criação do toString
            sb.append(String.format("Quem deu o presente : %s\n", pessoa.getNome()));
        } else {
            sb.append("Quem deu o presente : Não definido\n");
        }
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("============================================\n");
        return sb.toString();
    }
}
