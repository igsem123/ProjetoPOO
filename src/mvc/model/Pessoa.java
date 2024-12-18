package mvc.model;

import mvc.dao.PessoaController;
import mvc.dao.PessoaDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Pessoa {
    public long id;
    public static int totalPessoas = 0;
    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private String telefone;
    private String login;
    private String senha;
    private int tipoUsuario;
    private String cpf;
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    // Instâncias para manipulação de dados
    private Util util = new Util();

    public Pessoa(String nome, String sexo, LocalDate dataNascimento, String telefone, String login, String senha, int tipoUsuario, String cpf) {
        this.id = (totalPessoas++);
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.cpf = cpf;
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public Pessoa() {
        this.id = (totalPessoas++);
        this.dataCriacao = Util.getDia();
        this.dataModificacao = Util.getDia();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = Util.getDia();
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
        this.dataModificacao = Util.getDia();
    }

    public String getDataNascimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dataNascimento.format(formatter);
    }

    public void setDataNascimento(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
        this.dataModificacao = Util.getDia();
    }

    public void setDataNascimentoDB(String dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.login;
    }

    public void setEmail(String login) {
        this.login = login;
        this.dataModificacao = Util.getDia();
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        this.dataModificacao = Util.getDia();
    }

    public int getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        this.dataModificacao = Util.getDia();
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    
    public int getIdade() {
        if (this.dataNascimento == null) {
            throw new IllegalStateException("Data de nascimento não está definida para a pessoa: " + this.nome);
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    public String tipoUsuario(int tipo) {
        switch (tipo) {
            case 1 -> {
                return "Noivo(a)";
            }
            case 2 -> {
                return "Cerimonialista";
            }
            case 3 -> {
                return "Administrador(a)";
            }
            case 4 -> {
                return "Convidado(a)";
            }
            default -> {
                return "Usuário Indefinido";
            }
        }
    }

    public String perfil() {
        return  "\n|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" +
                "\n| Este e seu perfil " + this.nome +
                "\n|" + "\n|" +
                "\n| ID                 : " + this.id +
                "\n| Sexo               : " + this.sexo +
                "\n| Nascimento         : " + getDataNascimento() +
                "\n| Email              : " + this.login +
                "\n| Tipo de Usuario    : " + tipoUsuario(this.tipoUsuario) +
                "\n| CPF                : " + util.formataCpf(this.cpf) +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.sexo);
        hash = 23 * hash + Objects.hashCode(this.dataNascimento);
        hash = 23 * hash + Objects.hashCode(this.login);
        hash = 23 * hash + Objects.hashCode(this.senha);
        hash = 23 * hash + this.tipoUsuario;
        hash = 23 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.tipoUsuario != other.tipoUsuario) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return Objects.equals(this.dataNascimento, other.dataNascimento);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================ Pessoa de ID {" + getId() + "} ================\n");
        sb.append(String.format("Nome                : %s\n", nome));
        sb.append(String.format("Sexo                : %s\n", sexo));
        sb.append(String.format("Data de Nascimento  : %s\n", dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        sb.append(String.format("CPF                 : %s\n", util.formataCpf(cpf)));
        sb.append(String.format("Telefone            : %s\n", telefone));
        sb.append(String.format("Login               : %s\n", login));
        sb.append(String.format("Senha               : %s\n", senha));
        sb.append(String.format("Tipo de Usuário     : %s\n", tipoUsuario(getTipoUsuario())));
        sb.append(String.format("Data de Criação     : %s\n", dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append(String.format("Data de Modificação : %s\n", dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        sb.append("==================================================\n");
        return sb.toString();
    }
}

