package br.com.gestao.casamento.view;

import br.com.gestao.casamento.dao.PessoaDAO;
import br.com.gestao.casamento.dao.PessoaDAOMemoria;
import br.com.gestao.casamento.model.Pessoa;
import br.com.gestao.casamento.model.Util;

import javax.swing.*;
import java.util.Scanner;

public class GUI {
    Scanner scanner;
    PessoaDAO pessoaDao;
    StringBuilder builder;
    Util util;

    public GUI() {
        this.scanner = new Scanner(System.in);
        this.pessoaDao = new PessoaDAOMemoria();
        this.builder = new StringBuilder();

        // Inicializar pessoas para os testes
        pessoaDao.inicializarPessoasDeExemplo();
    }

    //TODO Menus do software de gestão de casamentos
    public int menuBoasVindas() {
        this.builder.setLength(0);
        this.builder.append("\n----------------------------");
        this.builder.append("\n|   GESTÃO DE CASAMENTOS   |");
        this.builder.append("\n|                          |");
        this.builder.append("\n| 1 - Login no Sistema     |");
        this.builder.append("\n| 2 - Cadastrar            |");
        this.builder.append("\n| 3 - Sair do Programa     |");
        this.builder.append("\n|                          |");
        this.builder.append("\n----------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    public int menuConvidado() {
        this.builder.setLength(0);
        this.builder.append("\n----------------------------------------");
        this.builder.append("\n|          BEM VINDO CONVIDADO         |");
        this.builder.append("\n|                                      |");
        this.builder.append("\n| 1 - Perfil                           |");
        this.builder.append("\n| 2 - Presentes                        |");
        this.builder.append("\n| 3 - Mural de Recados                 |");
        this.builder.append("\n| 4 - Confirmar Presença no Evento     |");
        this.builder.append("\n| 0 - Sair                             |");
        this.builder.append("\n|                                      |");
        this.builder.append("\n----------------------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    public int menuPrincipal() {
        this.builder.setLength(0);
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n|          MENU PRINCIPAL         |");
        this.builder.append("\n|     Bem vindo ao gerenciador    |");
        this.builder.append("\n|                                 |");
        this.builder.append("\n| 1 - Perfil                      |");
        this.builder.append("\n| 2 - Pessoas / Usuários          |");
        this.builder.append("\n| 3 - Evento                      |");
        this.builder.append("\n| 4 - Fornecedores                |");
        this.builder.append("\n| 5 - Convites                    |");
        this.builder.append("\n| 6 - Presentes                   |");
        this.builder.append("\n| 7 - Mural de Recados            |");
        this.builder.append("\n| 8 - Pagamento                   |");
        this.builder.append("\n| 9 - Relatórios                  |");
        this.builder.append("\n| 10 - Sair                       |");
        this.builder.append("\n|                                 |");
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    public int menuPessoa() {
        this.builder.setLength(0);
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n|      MENU PESSOAS/USUARIOS      |");
        this.builder.append("\n|     Bem vindo ao gerenciador    |");
        this.builder.append("\n|                                 |");
        this.builder.append("\n| 1 - Criar nova pessoa           |");
        this.builder.append("\n| 2 - Exibir pessoa               |");
        this.builder.append("\n| 3 - Buscar pessoa               |");
        this.builder.append("\n| 4 - Atualizar pessoa            |");
        this.builder.append("\n| 5 - Deletar pessoa              |");
        this.builder.append("\n| 6 - Alterar nome                |");
        this.builder.append("\n| 7 - Alterar sexo                |");
        this.builder.append("\n| 8 - Alterar nascimento          |");
        this.builder.append("\n| 9 - Alterar email/login         |");
        this.builder.append("\n| 10 - Alterar senha              |");
        this.builder.append("\n| 11 - Alterar tipo do usuario    |");
        this.builder.append("\n| 12 - Alterar CPF                |");
        this.builder.append("\n| 0 - Sair                        |");
        this.builder.append("\n|                                 |");
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    public int menuUsuarioDefault() {
        this.builder.setLength(0);
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n|             MENU                |");
        this.builder.append("\n|     Usuario sem tipo válido!    |");
        this.builder.append("\n|                                 |");
        this.builder.append("\n| 1 - Perfil                      |");
        this.builder.append("\n| 2 - Alterar Cadastro            |");
        this.builder.append("\n| 3 - Tentar Novamente            |");
        this.builder.append("\n| 0 - Sair                        |");
        this.builder.append("\n|                                 |");
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    // TODO Método para cadastrar uma nova pessoa
    public Pessoa cadastrarPessoa() {
        Pessoa novaPessoa = new Pessoa();
        System.out.println("\nDigite seu nome: ");
        String nome = this.scanner.nextLine();
        novaPessoa.setNome(nome);

        System.out.println("\nDigite seu CPF: ");
        System.out.println("Digite desta forma -> 000.000.000-00");
        String CPF = this.scanner.nextLine();
        novaPessoa.setCpf(CPF);

        System.out.println("\nDigite sua data de nascimento:");
        System.out.println("Digite desta forma -> dd/MM/yyyy");
        String data = this.scanner.nextLine();
        novaPessoa.setDataNascimento(data);

        System.out.println("\nDigite seu sexo [Masculino - Feminino]: ");
        String sexo = this.scanner.nextLine();
        novaPessoa.setSexo(sexo);

        System.out.println("\nDigite seu telefone: ");
        System.out.println("Digite desta forma -> +55 DDD X XXXX-XXXX");
        String telefone = this.scanner.nextLine();
        novaPessoa.setTelefone(telefone);

        System.out.println("\nDigite seu email [Login]: ");
        String email = this.scanner.nextLine();
        novaPessoa.setEmail(email);

        System.out.println("\nDigite a sua senha: ");
        String senha = this.scanner.nextLine();
        novaPessoa.setSenha(senha);

        System.out.println("\nDigite o tipo de usuário: ");
        System.out.println("Escolha um número -> 1 - Noivo(a) | 2 - Cerimonialista | 3 - Administrador | 4 - Convidado");
        int tipo = Integer.parseInt(this.scanner.nextLine());
        novaPessoa.setTipoUsuario(tipo);

        // Armazena a Pessoa no DAO e exibe uma mensagem de sucesso
        pessoaDao.criarPessoa(novaPessoa);
        JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!\n" + novaPessoa.toString());
        return novaPessoa;
    }

    // TODO Método principal para executar o programa
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.menuBoasVindas();
    }
}