package br.com.gestao.casamento.view;

import br.com.gestao.casamento.controller.Main;
import br.com.gestao.casamento.dao.FornecedorDAO;
import br.com.gestao.casamento.dao.FornecedorDAOMemoria;
import br.com.gestao.casamento.dao.PessoaDAO;
import br.com.gestao.casamento.dao.PessoaDAOMemoria;
import br.com.gestao.casamento.model.Fornecedor;

import br.com.gestao.casamento.model.Pessoa;
import br.com.gestao.casamento.model.Util;

import javax.swing.*;
import java.util.Scanner;

public class GUI {
    Scanner scanner;
    JOptionPane optionPane;
    PessoaDAO pessoaDao;
    FornecedorDAO fornecedorDao;
    StringBuilder builder;
    Util util;

    public GUI() {
        this.scanner = new Scanner(System.in);
        this.pessoaDao = new PessoaDAOMemoria();
        this.fornecedorDao = new FornecedorDAOMemoria();
        this.builder = new StringBuilder();
    }

    //TODO Menus do software de gestão de casamentos
    public int menuBoasVindas() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 1 || opcao > 3) {
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 1 || opcao > 3) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 3.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida! Por favor, digite um número (1, 2 ou 3).");
            }
        }

        return opcao;
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

    public int opPessoa() {
        this.builder.setLength(0);
        this.builder.append("\n-----------------------------------");
        this.builder.append("\n|      MENU PESSOAS/USUARIOS      |");
        this.builder.append("\n|                                 |");
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

    public int opFornecedor() {
        builder.setLength(0);
        builder.append("\n-----------------------------------");
        builder.append("\n|  * -> Fornecedor                 |");
        builder.append("\n|                                  |");
        builder.append("\n|  1 - Buscar Perfil da Empresa    |");
        builder.append("\n|  2 - Cadastrar                   |");
        builder.append("\n|  3 - Mostrar todos               |");
        builder.append("\n|  4 - Alterar um Fornecedor       |");
        builder.append("\n|  5 - Excluir um Fornecedor       |");
        builder.append("\n|  0 - Sair                        |");
        builder.append("\n|                                  |");
        builder.append("\n-----------------------------------");
        builder.append("\n\nQual sua opcao? R: ");
        System.out.print(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }

    // =-=-=-=-=CRIACOES=-=-=-=-=-= //
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
        pessoaDao.criarPessoa(novaPessoa); // Confirmar aqui pois acho que está conflitando isso aqui
        JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!\n" + novaPessoa.toString());

        return novaPessoa;
    }

    // TODO Método para cadastrar um novo Fornecedor
    public Fornecedor cadastrarFornecedor() {
        Fornecedor f = new Fornecedor();
        System.out.println("\nDigite o nome da empresa: ");
        String nome = this.scanner.nextLine();
        f.setNome(nome);

        System.out.println("\nDigite o CNPJ da empresa: ");
        System.out.println("Digite desta forma-> 00.000.000/0000-00");
        String CNPJ = this.scanner.nextLine();
        f.setCNPJ(CNPJ);

        System.out.println("\nDigite o telefone da empresa: ");
        System.out.println("Digite desta forma -> +55 DDD X XXXX-XXXX");
        String telefone = this.scanner.nextLine();
        f.setTelefone(telefone);

        System.out.println("\nDigite o valor em débito com a empresa: ");
        long valorAPagar = this.scanner.nextLong();
        f.setValorAPagar(valorAPagar);

        // Armazena o Fornecedor no DAO e exibe uma mensagem de sucesso
        fornecedorDao.criarFornecedor(f);
        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!\n" + f.toString());
        return f;
    }

    // TODO Método principal para executar o programa
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.menuBoasVindas();
    }

    public void fazerLogin() {
        int login = 0;
        while(login != 1) {
            System.out.println("\nDigite seu login [email]:");
            String email = this.scanner.nextLine();
            System.out.println("Digite sua senha: ");
            String senha = this.scanner.nextLine();
            Pessoa logada = pessoaDao.buscaPessoaLogin(email, senha);

            if (logada != null) {
                System.out.println("Voce esta logado!");
                Util.setPessoaLogada(logada);
                System.out.println("Seus dados: " + Util.getPessoaLogada().toString());
                login = 1;
                menuPrincipal();
            } else {
                System.out.println("Login Invalido. Tente novamente!");
            }
        }
    }
}