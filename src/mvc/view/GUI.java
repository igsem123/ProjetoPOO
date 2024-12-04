package mvc.view;

import mvc.controller.Main;
import mvc.dao.*;
import mvc.model.*;

import java.time.LocalDate;
import java.util.Scanner;

import static mvc.controller.Main.SENHA_ADMIN;

public class GUI {
    private Scanner scanner;
    private StringBuilder builder;
    private PessoaDAO pessoaDAO;
    private FornecedorDAO fornecedorDAO;
    private EventoDAO eventoDAO;
    private ConvidadoIndividualDAO convidadoIndividualDAO;
    private ConvidadoFamiliaDAO convidadoFamiliaDAO;
    private PresentesDAO presentesDAO;
    private MuralRecadosDAO muralRecadosDAO;
    private PagamentoDAO pagamentoDAO;

    // Construtor que recebe as instâncias dos DAOs
    public GUI(PessoaDAO pessoaDAO, FornecedorDAO fornecedorDAO, EventoDAO eventoDAO, ConvidadoIndividualDAO convidadoIndividualDAO, ConvidadoFamiliaDAO convidadoFamiliaDAO, PresentesDAO presentesDAO, MuralRecadosDAO muralRecadosDAO, PagamentoDAO pagamentoDAO) {
        this.pessoaDAO = pessoaDAO;
        this.fornecedorDAO = fornecedorDAO;
        this.eventoDAO = eventoDAO;
        this.convidadoIndividualDAO = convidadoIndividualDAO;
        this.convidadoFamiliaDAO = convidadoFamiliaDAO;
        this.presentesDAO = presentesDAO;
        this.muralRecadosDAO = muralRecadosDAO;
        this.pagamentoDAO = pagamentoDAO;
        this.scanner = new Scanner(System.in);
        this.builder = new StringBuilder();
    }

    //TODO Menus do software de gestão de casamentos
    public int menuBoasVindas() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 1 || opcao > 4) {
            this.builder.setLength(0);
            this.builder.append("\n----------------------------");
            this.builder.append("\n|   GESTÃO DE CASAMENTOS   |");
            this.builder.append("\n|                          |");
            this.builder.append("\n| 1 - Login no Sistema     |");
            this.builder.append("\n| 2 - Cadastrar            |");
            this.builder.append("\n| 3 - Entrar sem Login     |");
            this.builder.append("\n| 4 - Sair do Programa     |");
            this.builder.append("\n|                          |");
            this.builder.append("\n----------------------------");
            this.builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 1 || opcao > 4) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 1 e 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número (1, 2, 3 ou 4).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (1, 2, 3 ou 4).");
            }
        }

        return opcao;
    }

    public int menuPrincipal() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 0 || opcao > 10) {
            this.builder.setLength(0);
            this.builder.append("\n------------------------------------");
            this.builder.append("\n|           MENU PRINCIPAL         |");
            this.builder.append("\n|      Bem vindo ao gerenciador    |");
            this.builder.append("\n|                                  |");
            this.builder.append("\n| 1  - Perfil                      |");
            this.builder.append("\n| 2  - Pessoas / Usuários          |");
            this.builder.append("\n| 3  - Evento                      |");
            this.builder.append("\n| 4  - Fornecedores                |");
            this.builder.append("\n| 5  - Convites                    |");
            this.builder.append("\n| 6  - Presentes                   |");
            this.builder.append("\n| 7  - Mural de Recados            |");
            this.builder.append("\n| 8  - Pagamento                   |");
            this.builder.append("\n| 9  - Relatórios                  |");
            this.builder.append("\n| 10 - Calendário                  |");
            this.builder.append("\n| 0  - Sair                        |");
            this.builder.append("\n|                                  |");
            this.builder.append("\n------------------------------------");
            this.builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 10) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 10.");
                }
            } else {
                // Limpa o ‘buffer’ do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
            }
        }

        return opcao;
    }

    public int menuConvidado() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 0 || opcao > 5) {
            this.builder.setLength(0);
            this.builder.append("\n----------------------------------------");
            this.builder.append("\n|          BEM VINDO CONVIDADO         |");
            this.builder.append("\n|                                      |");
            this.builder.append("\n| 1 - Perfil                           |");
            this.builder.append("\n| 2 - Presentes                        |");
            this.builder.append("\n| 3 - Mural de Recados                 |");
            this.builder.append("\n| 4 - Confirmar Presença no Evento     |");
            this.builder.append("\n| 5 - Confirmar Presença de Familia    |");
            this.builder.append("\n| 0 - Sair                             |");
            this.builder.append("\n|                                      |");
            this.builder.append("\n----------------------------------------");
            this.builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número (0, 1, 2, 3, 4 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (0, 1, 2, 3, 4 ou 5).");
            }
        }

        return opcao;
    }

    public int menuUsuarioDefault() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 0 || opcao > 3) {
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

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 3) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número (0, 1, 2 ou 3).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (0, 1, 2 ou 3).");
            }
        }

        return opcao;
    }

    public int menuSemLogin() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 1 || opcao > 3) {
            this.builder.setLength(0);
            this.builder.append("\n----------------------------------------");
            this.builder.append("\n|          BEM VINDO CONVIDADO         |");
            this.builder.append("\n|                                      |");
            this.builder.append("\n| 1 - Presentes                        |");
            this.builder.append("\n| 2 - Mural de Recados                 |");
            this.builder.append("\n| 3 - Sair                             |");
            this.builder.append("\n|                                      |");
            this.builder.append("\n----------------------------------------");
            this.builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 1 || opcao > 3) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 1 e 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número (1, 2 ou 3).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (1, 2 ou 3).");
            }
        }

        return opcao;
    }

    public int opPessoa() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 0 || opcao > 12) {
            this.builder.setLength(0);
            this.builder.append("\n-----------------------------------");
            this.builder.append("\n|      MENU PESSOAS/USUARIOS      |");
            this.builder.append("\n|                                 |");
            this.builder.append("\n|                                 |");
            this.builder.append("\n| 1 - Criar nova pessoa           |");
            this.builder.append("\n| 2 - Exibir lista de pessoas     |");
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

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 12) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 12.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 12).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 12).");
            }
        }

        return opcao;
    }

    public int opEvento() {
        int opcao = -1;

        while (opcao < 0 || opcao > 5) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------");
            builder.append("\n|  * -> Eventos                         |");
            builder.append("\n|                                       |");
            builder.append("\n|  1 - Criar um novo evento             |");
            builder.append("\n|  2 - Buscar evento pelo ID            |");
            builder.append("\n|  3 - Listar todos os eventos          |");
            builder.append("\n|  4 - Atualizar dados do evento        |");
            builder.append("\n|  5 - Remover evento                   |");
            builder.append("\n|  0 - Sair                             |");
            builder.append("\n|                                       |");
            builder.append("\n-----------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 5).");
            }
        }

        return opcao;
    }

    public int opFornecedor() {
        int opcao = -1;

        while (opcao < 0 || opcao > 5) {
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
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 5).");
            }
        }

        return opcao;
    }

    public int opConvites() {
        int opcao = -1;

        while (opcao < 0 || opcao > 2) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------");
            builder.append("\n|  * -> Convites                        |");
            builder.append("\n|                                       |");
            builder.append("\n|  1 - Convidados Individuais           |");
            builder.append("\n|  2 - Convidados Familia               |");
            builder.append("\n|  0 - Sair                             |");
            builder.append("\n|                                       |");
            builder.append("\n-----------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 2) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número  (0, 1 ou 2).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (0, 1 ou 2).");
            }
        }

        return opcao;
    }

    public int opConvitesIndividual() {
        int opcao = -1;

        while (opcao < 0 || opcao > 5) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------------------");
            builder.append("\n|  * -> Convites Individuais                        |");
            builder.append("\n|                                                   |");
            builder.append("\n|  1 - Criar um convite individual                  |");
            builder.append("\n|  2 - Confirmar presença do convidado              |");
            builder.append("\n|  3 - Mostrar todos os convites individuais        |");
            builder.append("\n|  4 - Alterar um convidado individual              |");
            builder.append("\n|  5 - Excluir um convite individual                |");
            builder.append("\n|  0 - Sair                                         |");
            builder.append("\n|                                                   |");
            builder.append("\n-----------------------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 5).");
            }
        }

        return opcao;
    }

    public int opConvitesFamilia() {
        int opcao = -1;

        while (opcao < 0 || opcao > 5) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------------------");
            builder.append("\n|  * -> Convites Familia                            |");
            builder.append("\n|                                                   |");
            builder.append("\n|  1 - Criar um convite familiar                    |");
            builder.append("\n|  2 - Confirmar presença da familia                |");
            builder.append("\n|  3 - Mostrar todos os convites familiares         |");
            builder.append("\n|  4 - Alterar um convite familiar                  |");
            builder.append("\n|  5 - Excluir um convite familiar                  |");
            builder.append("\n|  0 - Sair                                         |");
            builder.append("\n|                                                   |");
            builder.append("\n-----------------------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 5).");
            }
        }

        return opcao;
    }

    public int opPresentes() {
        int opcao = -1;

        while (opcao < 0 || opcao > 5) {
            builder.setLength(0);
            builder.append("\n----------------------------------------");
            builder.append("\n|  * -> Gerenciamento de Presentes     |");
            builder.append("\n|                                      |");
            builder.append("\n|  1 - Criar um presente               |");
            builder.append("\n|  2 - Lista de presentes              |");
            builder.append("\n|  3 - Busca de presente pelo [ID]     |");
            builder.append("\n|  4 - Atualizar um presente           |");
            builder.append("\n|  5 - Excluir um presente             |");
            builder.append("\n|  0 - Sair                            |");
            builder.append("\n|                                      |");
            builder.append("\n----------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 5).");
            }
        }

        return opcao;
    }

    public int opPresentesConvidados() {
        int opcao = -1;

        while (opcao < 0 || opcao > 2) {
            builder.setLength(0);
            builder.append("\n-----------------------------------");
            builder.append("\n|  * -> Presentes                 |");
            builder.append("\n|                                 |");
            builder.append("\n|  1 - Dar um presente            |");
            builder.append("\n|  2 - Lista de presentes         |");
            builder.append("\n|  0 - Sair                       |");
            builder.append("\n|                                 |");
            builder.append("\n-----------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 2) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número (0, 1 ou 2).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (0, 1 ou 2).");
            }
        }

        return opcao;
    }

    public int opRecados() {
        int opcao = -1;

        while (opcao < 0 || opcao > 6) {
            builder.setLength(0);
            builder.append("\n----------------------------------------");
            builder.append("\n|  * -> Gerenciamento de Recados       |");
            builder.append("\n|                                      |");
            builder.append("\n|  1 - Registrar um recado             |");
            builder.append("\n|  2 - Lista de recados no sistema     |");
            builder.append("\n|  3 - Lista de recados por evento     |");
            builder.append("\n|  4 - Buscar recado de convidado      |");
            builder.append("\n|  5 - Editar um recado                |");
            builder.append("\n|  6 - Excluir um recado               |");
            builder.append("\n|  0 - Sair                            |");
            builder.append("\n|                                      |");
            builder.append("\n----------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 6) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 6).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 6).");
            }
        }

        return opcao;
    }

    public int opPagamentos() {
        int opcao = -1;

        while (opcao < 0 || opcao > 5) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------------");
            builder.append("\n|  * -> Gerenciador de Pagamentos             |");
            builder.append("\n|                                             |");
            builder.append("\n|  1 - Pagar ou agendar um pagamento          |");
            builder.append("\n|  2 - Buscar um registro de pagamento        |");
            builder.append("\n|  3 - Listar pagamentos feitos no sistema    |");
            builder.append("\n|  4 - Atualizar um pagamento                 |");
            builder.append("\n|  5 - Excluir um pagamento do sistema        |");
            builder.append("\n|  0 - Sair                                   |");
            builder.append("\n|                                             |");
            builder.append("\n-----------------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 5) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 5).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 5).");
            }
        }

        return opcao;
    }

    public int opCalendario() {
        int opcao = -1;

        while (opcao < 0 || opcao > 3) {
            builder.setLength(0);
            builder.append("\n--------------------------------------------------");
            builder.append("\n|  * -> Calendário do Sistema                    |");
            builder.append("\n|                                                |");
            builder.append("\n|  1 - Verificar pagamentos agendados para hoje  |");
            builder.append("\n|  2 - Verificar pagamentos agendados e pagar    |");
            builder.append("\n|  3 - Modificar data do calendário              |");
            builder.append("\n|  0 - Sair                                      |");
            builder.append("\n|                                                |");
            builder.append("\n--------------------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 3) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número (0, 1, 2 ou 3).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número (0, 1, 2 ou 3).");
            }
        }

        return opcao;
    }

    public int opRelatorios() {
        int opcao = -1;

        while (opcao < 0 || opcao > 6) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------------");
            builder.append("\n|  * -> Gerenciador de Relatorios             |");
            builder.append("\n|                                             |");
            builder.append("\n|  1 - Recados Recebidos                      |");
            builder.append("\n|  2 - Convite Individual                     |");
            builder.append("\n|  3 - Convite para Familia                   |");
            builder.append("\n|  4 - Pagamentos Realizados pelos noivos     |");
            builder.append("\n|  5 - Lista Total de Convidados              |");
            builder.append("\n|  6 - Lista Total de Convidados Confirmados  |");
            builder.append("\n|  0 - Sair                                   |");
            builder.append("\n|                                             |");
            builder.append("\n-----------------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    opcao = Integer.parseInt(input);

                    if (opcao < 0 || opcao > 6) {
                        System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Por favor, digite um número entre (0 ou 6).");
                }
            } else {
                System.out.println("\nEntrada vazia! Por favor, digite um número entre (0 ou 6).");
            }
        }

        return opcao;
    }

    // =-=-=-=-==-=-=-=-=-=-=-=-=-=-= CRIAÇÕES =-=-=-=-==-=-=-=-=-=-=-=-=-=-= //
    // TODO Formulário para cadastrar uma nova pessoa
    public Pessoa cadastrarPessoa() {
        Pessoa novaPessoa = new Pessoa();
        System.out.println("\nDigite seu nome: ");
        String nome = this.scanner.nextLine();

        while (nome.length() < 3 || nome.length() > 100 || nome.contains("  ") || nome.isBlank()) {
            System.out.println("\nNome inválido, tente novamente:");
            nome = this.scanner.nextLine();
        }

        novaPessoa.setNome(nome);

        System.out.println("\nDigite seu CPF: ");
        System.out.println("Digite desta forma -> 000.000.000-00");
        String CPF = this.scanner.nextLine();

        while (CPF.length() != 14 || CPF.contains("  ") || CPF.isBlank()) {
            System.out.println("\nCPF inválido, tente novamente:");
            CPF = this.scanner.nextLine();
        }

        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");

        novaPessoa.setCpf(CPF);

        System.out.println("\nDigite sua data de nascimento:");
        System.out.println("Digite desta forma -> dd/MM/yyyy");
        String data = this.scanner.nextLine();

        while (data.length() != 10 || data.contains("  ") || data.isBlank()) {
            System.out.println("\nData inválida, tente novamente:");
            data = this.scanner.nextLine();
        }

        novaPessoa.setDataNascimento(data);

        System.out.println("\nDigite seu sexo [Homem] - [Mulher]: ");
        String sexo = this.scanner.nextLine();

        while (!sexo.equalsIgnoreCase("Homem") && !sexo.equalsIgnoreCase("Mulher")) {
            System.out.println("\nSexo inválido, tente novamente:");
            System.out.println("Digite seu sexo [Homem] - [Mulher]: ");
            sexo = this.scanner.nextLine();
        }

        novaPessoa.setSexo(sexo);

        System.out.println("\nDigite seu telefone: ");
        System.out.println("Digite desta forma -> +55 DDD X XXXX-XXXX");
        String telefone = this.scanner.nextLine();

        while (telefone.length() < 14 || telefone.contains("  ") || telefone.isBlank()) {
            System.out.println("\nTelefone inválido, tente novamente:");
            telefone = this.scanner.nextLine();
        }

        novaPessoa.setTelefone(telefone);

        System.out.println("\nDigite seu email [Login]: ");
        String email = this.scanner.nextLine();

        while (email.length() < 5 || email.length() > 100 || email.contains("  ") || email.isBlank()) {
            System.out.println("\nEmail inválido, tente novamente:");
            email = this.scanner.nextLine();
        }

        novaPessoa.setEmail(email);

        System.out.println("\nDigite a sua senha: ");
        String senha = this.scanner.nextLine();

        while (senha.length() < 4 || senha.length() > 100 || senha.contains("  ") || senha.isBlank()) {
            System.out.println("\nSenha inválida, tente novamente:");
            senha = this.scanner.nextLine();
        }

        novaPessoa.setSenha(senha);

        System.out.println("\nDigite o tipo de usuário: ");
        System.out.println("Escolha um número -> 1 - Noivo(a) | 2 - Cerimonialista | 3 - Administrador | 4 - Convidado");
        int tipo = Integer.parseInt(this.scanner.nextLine());

        while (tipo < 1 || tipo > 4) {
            System.out.println("\nTipo inválido, tente novamente:");
            tipo = Integer.parseInt(scanner.nextLine());
        }

        if (tipo == 3) {
            System.out.println("\nPara criar um novo administrador, é necessário informar a senha de administrador global. \nSe deseja cancelar a operação, digite 0.");
            System.out.println("Digite a senha de administrador: ");
            String senhaAdmin = this.scanner.nextLine();

            while (!senhaAdmin.equals(SENHA_ADMIN)) {
                System.out.println("\nSenha de administrador inválida, tente novamente:");
                senhaAdmin = this.scanner.nextLine();

                if (senhaAdmin.equals("0")) {
                    return null;
                }
            }

            novaPessoa.setTipoUsuario(tipo);
        }
        novaPessoa.setTipoUsuario(tipo);

        return novaPessoa;
    }

    // TODO Formulário para cadastrar um novo Fornecedor
    public Fornecedor cadastrarFornecedor() {
        Fornecedor nF = new Fornecedor();
        System.out.println("\nQual o nome da empresa? ");
        String nome = scanner.nextLine();

        while (nome.length() < 3 || nome.length() > 100 || nome.contains("  ") || nome.isBlank()) {
            System.out.println("\nNome inválido, tente novamente:");
            nome = scanner.nextLine();
        }
        nF.setNome(nome);

        System.out.println("\nQual o CNPJ da empresa? ");
        System.out.println("Digite desta forma -> 00.000.000/0000-00");
        String CNPJ = scanner.nextLine();

        while (CNPJ.length() != 18 || CNPJ.contains("  ") || CNPJ.isBlank()) {
            System.out.println("\nCNPJ inválido, tente novamente:");
            CNPJ = scanner.nextLine();
        }

        CNPJ = CNPJ.replace(".", "");
        CNPJ = CNPJ.replace("/", "");
        CNPJ = CNPJ.replace("-", "");

        nF.setCNPJ(CNPJ);

        System.out.println("\nQual o telefone da empresa? ");
        System.out.println("Digite desta forma -> +55 DDD X XXXX-XXXX");
        String telefone = scanner.nextLine();

        while (telefone.length() < 14 || telefone.contains("  ") || telefone.isBlank()) {
            System.out.println("\nTelefone inválido, tente novamente:");
            telefone = scanner.nextLine();
        }
        nF.setTelefone(telefone);

        System.out.println("\nQual o email da empresa? ");
        System.out.println("Digite desta forma -> nomedoemail@email.com");
        String email = scanner.nextLine();

        while (email.length() < 5 || email.length() > 100 || email.contains("  ") || email.isBlank()) {
            System.out.println("\nEmail inválido, tente novamente:");
            email = scanner.nextLine();
        }
        nF.setEmail(email);

        System.out.println("\nQual o valor em débito com a empresa?");
        String valor = scanner.nextLine().trim();
        while (valor.equals("0") || valor.isBlank()) {
            System.out.println("\nValor inválido, tente novamente:");
            valor = scanner.nextLine();
        }

        // Substitui a vírgula pelo ponto
        valor = valor.replace(".", "").replace(",", ".");

        double valorDebito = Double.parseDouble(valor);
        try {
            nF.setValorAPagar(valorDebito);
            System.out.println("Valor em débito: " + valorDebito);
        } catch (NumberFormatException e) {
            System.out.println("Formato de número inválido. Por favor, digite no formato 0000.00");
        }

        System.out.println("\nEm quantas parcelas pagará?");
        System.out.println("\n -> Digite 0 se o pagamento for à vista!");
        int parcelas = Integer.parseInt(scanner.nextLine());

        while (parcelas < 0) {
            System.out.println("\nParcelas inválidas, tente novamente:");
            parcelas = Integer.parseInt(scanner.nextLine());
        }
        nF.setParcelas(parcelas);

        if(parcelas == 0) {
            nF.setValorParcela(valorDebito);
        } else {
            double parcelado = valorDebito / parcelas; // Valor da parcela a ser paga
            nF.setValorParcela(parcelado);
        }

        String estado = "A Pagar";
        nF.setEstado(estado);
        nF.setValorInicial(valorDebito);
        return nF;
    }

    // TODO Formulário para criar um novo evento
    public Evento cadastrarEvento() {
        Evento nE = new Evento();
        System.out.println("\nQual a data do evento? ");
        System.out.println("-> Digite desta forma: DD/MM/AAAA");
        String dataEvento = scanner.nextLine();

        while (dataEvento.length() != 10 || dataEvento.contains("  ") || dataEvento.isBlank()) {
            System.out.println("\nData inválida, tente novamente:");
            dataEvento = scanner.nextLine();
        }
        nE.setDataEvento(dataEvento);

        System.out.println("\nLista de cerimonialistas cadastrados no sistema: \n");
        pessoaDAO.buscaCerimonialistas();
        System.out.println("\nInforme o [ID] do cerimonialista desejado: ");
        long cerimonialId = Long.parseLong(scanner.nextLine());
        Pessoa cerimonialista = pessoaDAO.buscaPorId(cerimonialId);

        while (cerimonialista.getTipoUsuario() != 2) {
            System.out.println("\nUsuário digitado inválido!");
            System.out.println("\nInforme o [ID] do cerimonialista desejado: ");
            cerimonialId = Long.parseLong(scanner.nextLine());
            cerimonialista = pessoaDAO.buscaPorId(cerimonialId);
        }
        nE.setCerimonial(cerimonialista);

        System.out.println("\nEm qual igreja será realizado o casamento? ");
        String igrejaEvento = scanner.nextLine();

        while (igrejaEvento.length() < 3 || igrejaEvento.length() > 100 || igrejaEvento.contains("  ") || igrejaEvento.isBlank()) {
            System.out.println("\nNome inválido, tente novamente:");
            igrejaEvento = scanner.nextLine();
        }
        nE.setIgreja(igrejaEvento);

        System.out.println("\nInforme em qual cartório será cadastro o matrimônio: ");
        String cartorioEvento  = scanner.nextLine();

        while (cartorioEvento.length() < 3 || cartorioEvento.length() > 100 || cartorioEvento.contains("  ") || cartorioEvento.isBlank()) {
            System.out.println("\nNome inválido, tente novamente:");
            cartorioEvento = scanner.nextLine();
        }
        nE.setCartorio(cartorioEvento);

        System.out.println("\nQuem serão os noivos?");
        System.out.println("\nLista de noivos cadastrados no sistema: \n");
        pessoaDAO.buscaNoivos();
        System.out.println("\nInforme o [ID] do noivo(a): ");
        long noivo1Id = Long.parseLong(scanner.nextLine());
        Pessoa noivo1 = pessoaDAO.buscaPorId(noivo1Id);

        while (noivo1.getTipoUsuario() != 1) {
            System.out.println("\nUsuário digitado inválido!");
            System.out.println("\nInforme o [ID] do noivo(a): ");
            noivo1Id = Long.parseLong(scanner.nextLine());
            noivo1 = pessoaDAO.buscaPorId(noivo1Id);
        }
        nE.setPessoaNoivo1(noivo1);

        System.out.println("\nAgora, informe [ID] do outro noivo(a): ");
        long noivo2Id = Long.parseLong(scanner.nextLine());
        Pessoa noivo2 = pessoaDAO.buscaPorId(noivo2Id);

        while (noivo2.getTipoUsuario() != 1) {
            System.out.println("\nUsuário digitado inválido!");
            System.out.println("\nInforme o [ID] do noivo(a): ");
            noivo2Id = Long.parseLong(scanner.nextLine());
            noivo2 = pessoaDAO.buscaPorId(noivo2Id);
        }
        nE.setPessoaNoivo2(noivo2);

        System.out.println("\nQuais os fornecedores que estarão presentes no evento?");
        System.out.println("\nLista de fornecedores cadastrados no sistema:");
        fornecedorDAO.exibeFornecedoresSimples();
        System.out.println("\nInforme o [ID] do fornecedor: ");
        long fornecedorId = Long.parseLong(scanner.nextLine());
        Fornecedor fornecedor = fornecedorDAO.buscaPorId(fornecedorId);

        while (fornecedor == null) {
            System.out.println("\nFornecedor não identificado.");
            System.out.println("\nInforme o [ID] do fornecedor: ");
            fornecedorId = Long.parseLong(scanner.nextLine());
            fornecedor = fornecedorDAO.buscaPorId(fornecedorId);
        }

        System.out.println("\nFornecedor adicionado com sucesso: " + fornecedor.getNome());
        nE.addFornecedor(fornecedor);

        while (true) {
            System.out.println("\nDeseja adicionar mais fornecedores?");
            System.out.println("\n-> Digite [0] para SIM ou [1] para NÃO.");
            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha == 0) {
                System.out.println("\nInforme o [ID] do fornecedor: ");
                long id = Long.parseLong(scanner.nextLine());
                Fornecedor f = fornecedorDAO.buscaPorId(id);

                while (f == null) {
                    System.out.println("\nFornecedor não identificado.");
                    System.out.println("\nInforme o [ID] do fornecedor: ");
                    id = Long.parseLong(scanner.nextLine());
                    f = fornecedorDAO.buscaPorId(id);
                }

                System.out.println("\nFornecedor adicionado com sucesso: " + f.getNome());
                nE.addFornecedor(f);

            } else {
                break;
            }
        }

        // Gerar o nome do evento após inicializar todos os campos
        nE.setNomeDoEvento(nE.gerarNomeDoEvento(noivo1, noivo2));

        return nE;
    }

    // TODO Formulário para criar um convite individual
    public ConvidadoIndividual cadastrarConviteIndividual(ConvidadoFamiliaDAO convidadoFamiliaDAO) { // Passo o DAO para poder buscar a família
        ConvidadoIndividual nCi = new ConvidadoIndividual();
        System.out.println("\nLista de pessoas/convidados cadastrados no sistema: \n");
        pessoaDAO.buscaConvidados();
        System.out.println("\nInforme o [ID] do convidado(a): ");
        long convidadoId = Long.parseLong(scanner.nextLine());
        Pessoa ci = pessoaDAO.buscaPorId(convidadoId);

        if(ci.getTipoUsuario() != 4) {
            System.out.println("\nUsuário digitado inválido!");
            return this.cadastrarConviteIndividual(convidadoFamiliaDAO);
        } else {
            nCi.setPessoa(ci);
        }

        if (convidadoIndividualDAO.buscarPorId(convidadoId) != null) {
            System.out.println("\nConvidado já cadastrado no sistema. Caso deseje alterar o convite, utilize a opção de alteração.");
            System.out.println("\nDeseja cadastrar outro convidado?");
            System.out.println("\n-> Digite [0] para SIM ou [1] para NÃO.");
            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha == 0) {
                return this.cadastrarConviteIndividual(convidadoFamiliaDAO);
            } else {
                return null; // Retorna null para sair do metodo
            }
        }

        System.out.println("\nPara qual evento você deseja criar o convite?");
        eventoDAO.exibirListaEventosSimples();
        System.out.println("\nInforme o [ID] do evento: ");
        long idEvento = Long.parseLong(scanner.nextLine());
        Evento eventoCi = eventoDAO.buscarPorId(idEvento);

        if (eventoCi != null) {
            nCi.setEvento(eventoCi);
        } else {
            System.out.println("\nEvento não identificado.");
            return this.cadastrarConviteIndividual(convidadoFamiliaDAO);
        }

        System.out.println("\nLista de famílias cadastradas no sistema:");
        convidadoFamiliaDAO.listarFamilias();
        System.out.println("\nQual o [ID] da família que o convidado faz parte?");
        System.out.println("-> Se não possui uma família cadastrada, digite [-1] para poder cadastrar uma nova.");
        long idFamilia = Long.parseLong(scanner.nextLine());

        ConvidadoFamilia familia = null;

        if (idFamilia != -1) {
            familia = convidadoFamiliaDAO.buscarPorId(idFamilia);
        }

        if (familia != null) {
            nCi.setFamilia(familia); // Setando a família informada
        } else {
            familia = this.cadastraConviteFamiliar(); // Cadastrando uma nova família
            convidadoFamiliaDAO.criarFamilia(familia); // Criando a família
            nCi.setFamilia(familia); // Setando a família criada
        }

        System.out.println("\nQual o parentesco do convidado com algum dos noivos?");
        String parentesco = scanner.nextLine();

        while (parentesco.isEmpty()) {
            System.out.println("\nParentesco não informado, tente novamente!");
            parentesco = scanner.nextLine();
        }
        nCi.setParentesco(parentesco);

        nCi.setConfirmacao(false);

        return nCi;
    }

    //TODO Formulário para criar um convite família
    public ConvidadoFamilia cadastraConviteFamiliar() {
        System.out.println("\nDigite o nome da família: ");
        String nomeFamilia = scanner.nextLine();

        while (nomeFamilia.isEmpty()) {
            System.out.println("\nNome da família não informado, tente novamente!");
            nomeFamilia = scanner.nextLine();
        }

        System.out.println("\nPara gerar o acesso da família, precisamos das seguintes informações: ");
        System.out.println("\nLista de eventos cadastrados no sistema: ");
        eventoDAO.exibirListaEventosSimples();
        System.out.println("\nInforme o [ID] do evento: ");
        long idEvento = Long.parseLong(scanner.nextLine());
        Evento eventoDoConvite = eventoDAO.buscarPorId(idEvento);

        if(eventoDoConvite != null) {
            String noivo = eventoDoConvite.getPessoaNoivo1().getNome();
            String noiva = eventoDoConvite.getPessoaNoivo2().getNome();
            String data = eventoDoConvite.getDataEvento();
            return new ConvidadoFamilia(nomeFamilia, noivo, noiva, data, eventoDoConvite);
        } else {
            System.out.println("\nEvento informado inválido. Tente novamente!");
            this.cadastraConviteFamiliar();
        }
        return null;
    }

    //TODO Formulário para criar um presente
    public Presentes cadastraPresente() {
        System.out.println("\nDigite o nome do presente: ");
        String nomePresente = scanner.nextLine();

        while (nomePresente.isEmpty()) {
            System.out.println("\nNome do presente não informado, tente novamente!");
            nomePresente = scanner.nextLine();
        }

        System.out.println("\nInforme o tipo de presente: ");
        System.out.println("\n-> Tipos: 1 [Cozinha/Geral]\n" +
                "   2 [Cozinha/Eletrodomésticos]\n" +
                "   3 [Decoração/Cama/Mesa/Banho]\n" +
                "   4 [Moveis/Eletronicos]\n" +
                "   5 [Dinheiro]\n");
        System.out.println("Digite o número correspondente ao tipo de presente: ");
        int tipoPresente = Integer.parseInt(scanner.nextLine());

        while (tipoPresente < 1 || tipoPresente > 5) {
            System.out.println("\nTipo inválido, tente novamente:");
            tipoPresente = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("\nInfome o valor do presente: ");
        double valorPresente = Double.parseDouble(scanner.nextLine());

        while (valorPresente < 0) {
            System.out.println("\nValor inválido, tente novamente!");
            valorPresente = Double.parseDouble(scanner.nextLine());
        }

        return new Presentes(nomePresente, tipoPresente, valorPresente);
    }

    //TODO Formulário para cadastro de um comentário/recado no mural
    public MuralRecados cadastraRecados() {
        System.out.println("\nLista de eventos cadastrados no sistema: ");
        eventoDAO.exibirListaEventosSimples();
        System.out.println("\nDigite para qual evento deseja deixar um recado: ");
        String idEvento = scanner.nextLine();
        Evento eventoDoRecado = null;

        if (!idEvento.isEmpty()) {
            eventoDoRecado = eventoDAO.buscarPorId(Long.parseLong(idEvento));

            if (eventoDoRecado == null) {
                System.out.println("\nEvento não identificado. Tente novamente!");
                this.cadastraRecados();
            }
        } else {
            System.out.println("\nEvento não identificado. Tente novamente!");
            this.cadastraRecados();
        }

        System.out.println("\nQual mensagem você deseja escrever para os noivos de recado: ");
        String recado = scanner.nextLine();

        while (recado.isEmpty()) {
            System.out.println("\nRecado não foi digitado, tente novamente!");
            recado = scanner.nextLine();
        }

        if (Util.getPessoaLogada() == null) {
            System.out.println("\nQual é o seu nome: ");
            String nome = scanner.nextLine();

            while (nome.isEmpty()) {
                System.out.println("\nNome não foi digitado, tente novamente!");
                nome = scanner.nextLine();
            }

            return new MuralRecados(recado, nome, eventoDoRecado);
        } else {
            return new MuralRecados(recado, Util.getPessoaLogada(), eventoDoRecado);
        }
    }

    //TODO Formulário para cadastrar um novo pagamento a ser feito pelo sistema
    public Pagamento cadastraPagamento() {
        System.out.println("\nQuem fará o pagamento: \n\nLista de noivos cadastrados no sistema: \n");
        pessoaDAO.buscaNoivos();
        System.out.println("\nInforme o [ID] do noivo(a) que está efetuando o pagamento: ");
        long idNoivoPagamento = Long.parseLong(scanner.nextLine());
        Pessoa noivoPagando = pessoaDAO.buscaPorId(idNoivoPagamento);

        while (noivoPagando.getTipoUsuario() != 1) {
            System.out.println("\nUsuário digitado inválido!");
            System.out.println("\nInforme o [ID] do noivo(a) que está efetuando o pagamento: ");
            idNoivoPagamento = Long.parseLong(scanner.nextLine());
            noivoPagando = pessoaDAO.buscaPorId(idNoivoPagamento);
        }

        System.out.println("\nPara qual fornecedor o pagamento está sendo feito? \n");
        fornecedorDAO.exibeFornecedoresSimples();
        System.out.println("\nInforme o [ID] do fornecedor que está recebendo o pagamento: ");
        long idFornecedorPagamento = Long.parseLong(scanner.nextLine());
        Fornecedor fornecedorPagamento = fornecedorDAO.buscaPorId(idFornecedorPagamento);

        while (fornecedorPagamento == null) {
            System.out.println("\nFornecedor não identificado.");
            System.out.println("\nInforme o [ID] do fornecedor que está recebendo o pagamento: ");
            idFornecedorPagamento = Long.parseLong(scanner.nextLine());
            fornecedorPagamento = fornecedorDAO.buscaPorId(idFornecedorPagamento);
        }

        // Obtenção do valor do débito e parcelas restantes
        double valorEmAberto = fornecedorPagamento.getValorAPagar();
        int parcelasRestantes = fornecedorPagamento.getParcelas();

        if (valorEmAberto <= 0) {
            System.out.println("\nO fornecedor já foi pago integralmente.");
            return null; // Caso o fornecedor já tenha sido pago, retornamos null
        }

        System.out.println("\nQual a descrição do pagamento? ");
        String descricao = scanner.nextLine();

        while (descricao.isEmpty()) {
            System.out.println("\nDescrição não informada, tente novamente!");
            descricao = scanner.nextLine();
        }

        System.out.println("\nO débito com o fornecedor foi encontrado, segue informações abaixo: ");
        System.out.printf("-> O valor em aberto é: %.2f", valorEmAberto);
        System.out.printf("\n-> Restam %d parcelas. ", parcelasRestantes);

        System.out.println("\n\nQuantas parcelas deseja pagar?");
        int parcelasPagas = Integer.parseInt(scanner.nextLine());

        while (parcelasPagas < 0 || parcelasPagas > parcelasRestantes) {
            System.out.println("\nQuantidade de parcelas inválida, tente novamente!");
            parcelasPagas = Integer.parseInt(scanner.nextLine());
        }

        // Cálculo do valor de cada parcela baseado no valor em aberto
        double valorParcela = fornecedorPagamento.getValorParcela(); // Agr faço dentro do fornecedor e só pego de volta
        double valorTotalPagamento = parcelasPagas * valorParcela; //Fiz assim pra poder mostrar o quanto estaria sendo pago nesse ciclo

        System.out.printf("\n-> O valor de cada parcela é: %.2f", valorParcela);
        System.out.printf("\n-> O valor total deste pagamento será: %.2f", valorTotalPagamento);

        // Verifica se o pagamento será agendado
        System.out.println("\n\nDeseja pagar agora ou agendar uma data para o pagamento?");
        System.out.println("-> Digite [0] para PAGAR AGORA ou [1] para AGENDAR");
        int opcaoAgendar = Integer.parseInt(scanner.nextLine());

        while (opcaoAgendar < 0 || opcaoAgendar > 1) {
            System.out.println("\nOpção inválida, tente novamente!");
            opcaoAgendar = Integer.parseInt(scanner.nextLine());
        }

        Pagamento novoPagamento;

        if (opcaoAgendar == 1) {
            System.out.println("\nEm que data será efetuado o pagamento?");
            System.out.println("-> Digite desta forma: DD/MM/AAAA");
            String dataPagamentoString = scanner.nextLine();

            while (dataPagamentoString.length() != 10 || dataPagamentoString.contains("  ") || dataPagamentoString.isBlank()) {
                System.out.println("\nData inválida, tente novamente!");
                dataPagamentoString = scanner.nextLine();
            }

            LocalDate dataPagamento = Util.formataData(dataPagamentoString);
            novoPagamento = new Pagamento(noivoPagando, fornecedorPagamento, descricao, valorTotalPagamento, parcelasPagas, true, dataPagamento);
        } else {
            // Atualizando o valor a pagar e as parcelas do fornecedor, setando já direto após a criação do pagamento
            fornecedorPagamento.setValorAPagar(valorEmAberto - valorTotalPagamento);
            fornecedorPagamento.setParcelas(parcelasRestantes - parcelasPagas);

            novoPagamento = new Pagamento(noivoPagando, fornecedorPagamento, descricao, valorTotalPagamento, parcelasPagas, false, LocalDate.now());
        }

        // Verificando se todas as parcelas foram pagas e atualiza o estado do fornecedor
        if (fornecedorPagamento.getValorAPagar() <= 0) {
            fornecedorPagamento.setEstado("Pago!");
            System.out.println("\nO fornecedor " + fornecedorPagamento.getNome() + " foi totalmente pago.");
        } else {
            System.out.println("\nO fornecedor ainda possui um saldo aberto de: " + fornecedorPagamento.getValorAPagar());
        }

        return novoPagamento;
    }
}