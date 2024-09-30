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

    // Exibe o menu e gerencia as opções
    public void exibirMenu() {
        while (true) {
            System.out.println("========= GESTÃO DE CASAMENTOS =========");
            System.out.println("1. Cadastrar Pessoa/Usuário");
            System.out.println("2. Exibir Pessoas/Usuários");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    pessoaDao.listarPessoas();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public int menuBoasVindas() {
        this.builder.setLength(0);
        this.builder.append("\n----------------------------");
        this.builder.append("\n|   GESTÃO DE CASAMENTOS   |");
        this.builder.append("\n|                          |");
        this.builder.append("\n| 1 - Login                |");
        this.builder.append("\n| 2 - Cadastrar            |");
        this.builder.append("\n| 3 - Sair do programa     |");
        this.builder.append("\n|                          |");
        this.builder.append("\n----------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    public int menuConvidado() {
        this.builder.setLength(0);
        this.builder.append("\n----------------------------------------");
        this.builder.append("\n|   BEM VINDO ALUNO                    |");
        this.builder.append("\n|                                      |");
        this.builder.append("\n| 1 - Perfil                           |");
        this.builder.append("\n| 2 - Realizar Entrada                 |");
        this.builder.append("\n| 3 - Visualizar Ficha de Treino       |");
        this.builder.append("\n| 4 - Imprimir Ficha de Treino         |");
        this.builder.append("\n| 5 - Visualizar Avaliacoes Fisicas    |");
        this.builder.append("\n| 0 - Sair                             |");
        this.builder.append("\n|                                      |");
        this.builder.append("\n----------------------------------------");
        this.builder.append("\n\nQual sua opcao? R: ");
        System.out.print(this.builder.toString());
        return Integer.parseInt(this.scanner.nextLine());
    }

    public int menuNoivos() {
        this.builder.setLength(0);
        this.builder.append("\n----------------------------------------");
        this.builder.append("\n|     BEM VINDO NOIVO(A)               |");
        this.builder.append("\n|                                      |");
        this.builder.append("\n| 1 - Perfil                           |");
        this.builder.append("\n| 2 - Realizar Entrada                 |");
        this.builder.append("\n| 3 - Visualizar Ficha de Treino       |");
        this.builder.append("\n| 4 - Imprimir Ficha de Treino         |");
        this.builder.append("\n| 5 - Visualizar Avaliacoes Fisicas    |");
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
        this.builder.append("\n|             MENU                |");
        this.builder.append("\n|     Usuario sem tipo valido!    |");
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

    public int menuAdmin() {
        this.builder.setLength(0);
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n|             MENU                |");
        this.builder.append("\n|     Usuario sem tipo valido!    |");
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

    // Método para cadastrar uma nova pessoa
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
        novaPessoa.setLogin(email);

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