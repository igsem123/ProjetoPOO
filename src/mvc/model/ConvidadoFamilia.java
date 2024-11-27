package mvc.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class ConvidadoFamilia {
    private long id;
    public static int totalConvitesFamiliares = 0;
    private String nomeFamilia;
    private String acesso;
    private Evento evento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Construtor cheio
    public ConvidadoFamilia(String nomeFamilia, String noivo, String noiva, String data, Evento evento) {
        this.id = (totalConvitesFamiliares++);
        this.nomeFamilia = nomeFamilia;
        this.acesso = gerarAcesso(noivo, noiva, data);
        this.evento = evento;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    // Construtor vazio sem acesso familiar definido
    public ConvidadoFamilia(String nomeFamilia) {
        this.id = (totalConvitesFamiliares++);
        this.nomeFamilia = nomeFamilia;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    // Construtor vazio
    public ConvidadoFamilia() {}

    private String gerarAcesso(String noivo1, String noivo2, String data) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder letrasAleatorias = new StringBuilder();
        Random random = new Random(); // Utilizei o random

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(letras.length());
            letrasAleatorias.append(letras.charAt(index));
        }

        this.dataModificacao = LocalDateTime.now();
        return (noivo1 + noivo2 + data + letrasAleatorias).trim().replace(" ", "").replace("-", "");
    }

    // Getters e Setters
    public void setId(long id) {
        this.id = id;
        this.dataModificacao = LocalDateTime.now();
    }

    public long getId() { return id; }

    public String getNomeFamilia() { return nomeFamilia; }
    public void setNomeFamilia(String nomeFamilia)
    {
        this.dataModificacao = LocalDateTime.now();
        this.nomeFamilia = nomeFamilia;
    }

    public void setAcesso(String acesso) {
        this.dataModificacao = LocalDateTime.now();
        this.acesso = acesso;
    }

    public String getAcesso() { return acesso; }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
        this.dataModificacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataModificacao() { return dataModificacao; }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = LocalDateTime.now();
    }

    public void setEvento(Evento evento) {
        this.dataModificacao = LocalDateTime.now();
        this.evento = evento;
    }

    public Evento getEvento() { return evento; }

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
        sb.append("============= Convidado Família ID {" + getId() + "} =============\n");
        sb.append(String.format("Nome da Família     : %s\n", nomeFamilia));
        sb.append(String.format("Acesso              : %s\n", acesso));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("====================================================\n");
        return sb.toString();
    }
}
