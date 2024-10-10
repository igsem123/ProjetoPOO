package mvc.model;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class ConvidadoFamilia {
    private long id;
    public static int totalConvitesFamiliares = 0;
    private String nomeFamilia;
    private String acesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor cheio
    public ConvidadoFamilia(String nomeFamilia, String noivo, String noiva, String data) {
        this.id = (totalConvitesFamiliares++);
        this.nomeFamilia = nomeFamilia;
        this.acesso = gerarAcesso(noivo, noiva, data);
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    // Construtor vazio
    public ConvidadoFamilia() {
        this.id = (totalConvitesFamiliares++);
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    private String gerarAcesso(String noivo1, String noivo2, String data) {
        Random random = new Random();
        String letrasAleatorias = String.format("%04d", random.nextInt(10000));
        this.dataModificacao = LocalDateTime.now();
        return noivo1 + noivo2 + data + letrasAleatorias;
    }

    // Getters e Setters
    public long getId() { return id; }

    public String getNomeFamilia() { return nomeFamilia; }
    public void setNomeFamilia(String nomeFamilia)
    {
        this.dataModificacao = LocalDateTime.now();
        this.nomeFamilia = nomeFamilia;
    }

    public String getAcesso() { return acesso; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataModificacao() { return dataModificacao; }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConvidadoFamilia that = (ConvidadoFamilia) o;
        return id == that.id &&
                nomeFamilia.equals(that.nomeFamilia) &&
                acesso.equals(that.acesso);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.nomeFamilia);
        hash = 23 * hash + Objects.hashCode(this.acesso);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=============== Convidado Família ID {" + getId() + "} ===============\n");
        sb.append(String.format("Nome da Família     : %s\n", nomeFamilia));
        sb.append(String.format("Acesso              : %s\n", acesso));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao));
        sb.append("=====================================================\n");
        return sb.toString();
    }
}
