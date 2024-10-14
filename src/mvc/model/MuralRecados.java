package mvc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MuralRecados {
    private long id;
    public static int serial = 0;
    private Pessoa pessoa;
    private String nomeDoEvento;
    private String comentario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor cheio
    public MuralRecados (String comentario, Pessoa pessoa, Evento nomeDoEvento) {
        this.id = (serial++);
        this.pessoa = pessoa;
        this.comentario = comentario;
        this.nomeDoEvento = nomeDoEvento.getNomeDoEvento();
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.dataModificacao = LocalDateTime.now();
        this.comentario = comentario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.dataModificacao = LocalDateTime.now();
        this.pessoa = pessoa;
    }

    public String getNomeDoEvento() {
        return nomeDoEvento;
    }

    public void setNomeDoEvento(String nomeDoEvento) {
        this.dataModificacao = LocalDateTime.now();
        this.nomeDoEvento = nomeDoEvento;
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
        hash = 23 * hash + Objects.hashCode(this.comentario);
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.pessoa);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuralRecados that = (MuralRecados) o;
        return id == that.id && Objects.equals(pessoa, that.pessoa) && Objects.equals(comentario, that.comentario);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================== Comentário Nº.: {" + getId() + "} ==========================================\n");
        if (pessoa != null) { // Verificando se a pessoa é nula na criação do toString
            sb.append(String.format("Quem comentou       : %s\n", pessoa.getNome()));
        } else {
            sb.append("Quem comentou       : Não definido\n");
        }
        sb.append(String.format("Evento              : [- %s -]\n", nomeDoEvento));
        sb.append(String.format("Comentário          : %s\n", comentario));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("=========================================================================================================\n");
        return sb.toString();
    }
}
