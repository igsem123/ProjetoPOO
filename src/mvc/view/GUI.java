package mvc.view;

import mvc.dao.*;
import mvc.model.*;

import java.time.LocalDate;
import java.util.Scanner;

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

        while (opcao < 1 || opcao > 3) {
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 1 || opcao > 3) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 1 e 3.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida! Por favor, digite um número (1, 2 ou 3).");
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
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 4) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida! Por favor, digite um número (0, 1, 2, 3, 4 ou 5).");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 3) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 3.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 1 || opcao > 3) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 1 e 3.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 12) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 12.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 5) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 5) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 2) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 2.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 5) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 5) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 5) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 2) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 2.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida! Escolha entre 0, 1 ou 2.");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 6) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 6.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 5) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
            }
        }

        return opcao;
    }

    public int opCalendario() {
        int opcao = -1;

        while (opcao < 0 || opcao > 2) {
            builder.setLength(0);
            builder.append("\n--------------------------------------------------");
            builder.append("\n|  * -> Calendário do Sistema                    |");
            builder.append("\n|                                                |");
            builder.append("\n|  1 - Verificar pagamentos agendados para hoje  |");
            builder.append("\n|  2 - Modificar data do calendário              |");
            builder.append("\n|  0 - Sair                                      |");
            builder.append("\n|                                                |");
            builder.append("\n--------------------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 2) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 2.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida! Escolha entre 0, 1 ou 2.");
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 6) {
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção entre 0 e 6.");
                }
            } else {
                this.scanner.nextLine();
                System.out.println("\nEntrada inválida!");
            }
        }

        return opcao;
    }
    
    // =-=-=-=-==-=-=-=-=-=-=-=-=-=-= CRIACOES =-=-=-=-==-=-=-=-=-=-=-=-=-=-= //
    // TODO Formulário para cadastrar uma nova pessoa
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

        System.out.println("\nDigite seu sexo [Homem] - [Mulher]: ");
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

        while (tipo < 1 || tipo > 4) {
            System.out.println("\nTipo inválido, tente novamente:");
            tipo = Integer.parseInt(scanner.nextLine());
        }
        novaPessoa.setTipoUsuario(tipo);

        return novaPessoa;
    }

    // TODO Formulário para cadastrar um novo Fornecedor
    public Fornecedor cadastrarFornecedor() {
        Fornecedor nF = new Fornecedor();
        System.out.println("\nQual o nome da empresa? ");
        String nome = scanner.nextLine();
        nF.setNome(nome);

        System.out.println("\nQual o CNPJ da empresa? ");
        String CNPJ = scanner.nextLine();
        nF.setCNPJ(CNPJ);

        System.out.println("\nQual o telefone da empresa? ");
        String telefone = scanner.nextLine();
        nF.setTelefone(telefone);

        System.out.println("\nQual o email da empresa? ");
        String email = scanner.nextLine();
        nF.setEmail(email);

        System.out.println("\nQual o valor em débito com a empresa?");
        double valor = Long.parseLong(scanner.nextLine());
        nF.setValorAPagar(valor);

        System.out.println("\nEm quantas parcelas pagará?");
        System.out.println("\n -> Digite 0 se o pagamento for à vista!");
        int parcelas = Integer.parseInt(scanner.nextLine());
        nF.setParcelas(parcelas);

        String estado = "Aberto";
        nF.setEstado(estado);

        return nF;
    }

    // TODO Formulário para criar um novo evento
    public Evento cadastrarEvento() {
        Evento nE = new Evento();
        System.out.println("\nQual a data do evento? ");
        System.out.println("-> Digite desta forma: DD/MM/AAAA ");
        String dataEvento = scanner.nextLine();
        nE.setDataEvento(dataEvento);

        System.out.println("\nLista de cerimonialistas cadastrados no sistema: \n");
        pessoaDAO.buscaCerimonialistas();
        System.out.println("\nInforme o ID do cerimonialista desejado: ");
        long cerimonialId = Long.parseLong(scanner.nextLine());
        Pessoa cerimonialista = pessoaDAO.buscaPorId(cerimonialId);
        nE.setCerimonial(cerimonialista);

        System.out.println("\nEm qual igreja será realizado o casamento? ");
        String igrejaEvento = scanner.nextLine();
        nE.setIgreja(igrejaEvento);

        System.out.println("\nInforme em qual cartório será cadastro o matrimônio: ");
        String cartorioEvento  = scanner.nextLine();
        nE.setCartorio(cartorioEvento);

        System.out.println("\nQuem serão os noivos?");
        System.out.println("\nLista de noivos cadastrados no sistema: \n");
        pessoaDAO.buscaNoivos();
        System.out.println("\nInforme o ID do noivo(a): ");
        long noivo1Id = Long.parseLong(scanner.nextLine());
        Pessoa noivo1 = pessoaDAO.buscaPorId(noivo1Id);
        nE.setPessoaNoivo1(noivo1);
        System.out.println("\nAgora, informe ID do outro noivo(a): ");
        long noivo2Id = Long.parseLong(scanner.nextLine());
        Pessoa noivo2 = pessoaDAO.buscaPorId(noivo2Id);
        nE.setPessoaNoivo2(noivo2);

        return nE;
    }

    // TODO Formulário para criar um convite individual
    public ConvidadoIndividual cadastrarConviteIndividual(ConvidadoFamiliaDAO convidadoFamiliaDAO) {
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

        System.out.println("\nLista de famílias cadastradas no sistema:\n");
        convidadoFamiliaDAO.listarFamilias();
        System.out.println("\nQual o [ID] da família que o convidado faz parte?");
        System.out.println("\n->Se não possui uma família cadastrada, digite [-1] para poder cadastrar uma nova.");
        long idFamilia = Long.parseLong(scanner.nextLine());
        ConvidadoFamilia familia = convidadoFamiliaDAO.buscarPorId(idFamilia);

        if (familia != null)
        {
            nCi.setFamilia(familia); // Setando a família informada
        } else {
            System.out.println("\nFamília não identificada.");
            System.out.println("\nDigite o nome da sua família para cadastrá-la no sistema: ");
            String nomeFamiliaInd = scanner.nextLine();

            while (nomeFamiliaInd.isEmpty()) {
                System.out.println("\nNome da família não informado, tente novamente!");
                nomeFamiliaInd = scanner.nextLine();
            }

            System.out.println("\nDeseja gerar um acesso familiar?");
            System.out.println("\n-> Digite [0] para SIM ou [1] para NÃO.");
            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha == 0) { // Criando o convite familiar com um acesso gerado
                System.out.println("\nPara qual evento você deseja criar o convite familiar?\n");
                eventoDAO.exibirListaEventosSimples();
                System.out.println("\nDigite o [ID]: ");
                long id = Integer.parseInt(scanner.nextLine());

                Evento evento = eventoDAO.buscarPorId(id);
                if (evento != null) {
                    String noivo1 = evento.getPessoaNoivo1().getNome();
                    String noivo2 = evento.getPessoaNoivo1().getNome();
                    String data = evento.getDataEvento();
                    ConvidadoFamilia novoConviteFamiliar = new ConvidadoFamilia(nomeFamiliaInd ,noivo1, noivo2, data);
                    convidadoFamiliaDAO.criarFamilia(novoConviteFamiliar);
                    nCi.setFamilia(novoConviteFamiliar);
                }
            } else {
                // Criar convite familiar sem o acesso gerado
                System.out.println("\nConvite familiar criado sem acesso de confirmação. Contate o administrador!");
                ConvidadoFamilia novoConviteFamiliar = new ConvidadoFamilia(nomeFamiliaInd);
                convidadoFamiliaDAO.criarFamilia(novoConviteFamiliar);
                nCi.setFamilia(novoConviteFamiliar);
            }
        }

        System.out.println("\nQual o parentesco do convidado com algum dos noivos?");
        String parentesco = scanner.nextLine();
        nCi.setParentesco(parentesco);

        nCi.setConfirmacao(false);

        return nCi;
    }

    //TODO Formulário para criar um convite família
    public ConvidadoFamilia cadastraConviteFamiliar() {
        System.out.println("\nDigite o nome da família: ");
        String nomeFamilia = scanner.nextLine();

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
            return new ConvidadoFamilia(nomeFamilia, noivo, noiva, data);
        } else {
            System.out.println("\nEvento informado inválido. Tente novamente!");
            this.cadastraConviteFamiliar();
        }
        return null;
    }

    //TODO Formulário para criar um presente
    public Presentes cadastraPresente() {
        System.out.println("\nDigite qual será o presente: ");
        String nomePresente = scanner.nextLine();

        System.out.println("\nInforme o tipo de presente: ");
        System.out.println("\n-> Tipos: 1 [Cozinha/Geral]\n" +
                "   2 [Cozinha/Eletrodomésticos]\n" +
                "   3 [Decoração/Cama/Mesa/Banho]\n" +
                "   4 [Moveis/Eletronicos]\n" +
                "   5 [Dinheiro]\n");
        int tipoPresente = Integer.parseInt(scanner.nextLine());

        while (tipoPresente < 1 || tipoPresente > 5) {
            System.out.println("\nTipo inválido, tente novamente:");
            tipoPresente = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("\nInfome o valor do presente: ");
        double valorPresente = Double.parseDouble(scanner.nextLine());

        return new Presentes(nomePresente, tipoPresente, valorPresente);
    }

    //TODO Formulário para cadastro de um comentário/recado no mural
    public MuralRecados cadastraRecados() {
        System.out.println("\nLista de eventos cadastrados no sistema: ");
        eventoDAO.exibirListaEventosSimples();
        System.out.println("\nDigite para qual evento deseja deixar um recado: ");
        long idEvento = Long.parseLong(scanner.nextLine());
        Evento eventoDoRecado = eventoDAO.buscarPorId(idEvento);
        
        System.out.println("\nQual mensagem você deseja escrever para os noivos de recado: ");
        String recado = scanner.nextLine();
        
        if(Util.getPessoaLogada() == null) {
        	System.out.println("\nQual é o seu nome: ");
            String nome = scanner.nextLine();
            return new MuralRecados(recado, nome, eventoDoRecado);
        } else return new MuralRecados(recado, Util.getPessoaLogada(), eventoDoRecado);
    }

    //TODO Formulário para cadastrar um novo pagamento a ser feito pelo sistema
    public Pagamento cadastraPagamento() {
        System.out.println("\nQuem fará o pagamento: \n\nLista de noivos cadastrados no sistema: \n");
        pessoaDAO.buscaNoivos();
        System.out.println("\nInforme o [ID] do noivo(a) que está efetuando o pagamento: ");
        long idNoivoPagamento = Long.parseLong(scanner.nextLine());
        Pessoa noivoPagando = pessoaDAO.buscaPorId(idNoivoPagamento);

        System.out.println("\nPara qual fornecedor o pagamento está sendo feito? \n");
        fornecedorDAO.exibeFornecedoresSimples();
        System.out.println("\nInforme o [ID] do fornecedor que está recebendo o pagamento: ");
        long idFornecedorPagamento = Long.parseLong(scanner.nextLine());
        Fornecedor fornecedorPagamento = fornecedorDAO.buscaPorId(idFornecedorPagamento);

        // Obtenção do valor do débito e parcelas restantes
        double valorEmAberto = fornecedorPagamento.getValorAPagar();
        int parcelasRestantes = fornecedorPagamento.getParcelas();

        if (valorEmAberto <= 0) {
            System.out.println("\nO fornecedor já foi pago integralmente.");
            return null; // Caso o fornecedor já tenha sido pago, retornamos null
        }

        System.out.println("\nQual a descrição do pagamento? ");
        String descricao = scanner.nextLine();

        System.out.println("\nO débito com o fornecedor foi encontrado, segue informações abaixo: ");
        System.out.printf("-> O valor em aberto é: %.2f", valorEmAberto);
        System.out.printf("\n-> Restam %d parcelas. ", parcelasRestantes);

        System.out.println("\n\nQuantas parcelas deseja pagar?");
        int parcelasPagas = Integer.parseInt(scanner.nextLine());

        // Cálculo do valor de cada parcela baseado no valor em aberto
        double valorParcela = fornecedorPagamento.getValorParcela(); // Agr faço dentro do fornecedor e só pego de volta
        double valorTotalPagamento = parcelasPagas * valorParcela; //Fiz assim pra poder mostrar o quanto estaria sendo pago nesse ciclo

        System.out.printf("\n-> O valor de cada parcela é: %.2f", valorParcela);
        System.out.printf("\n-> O valor total deste pagamento será: %.2f", valorTotalPagamento);

        // Verifica se o pagamento será agendado
        System.out.println("\n\nDeseja pagar agora ou agendar uma data para o pagamento?");
        System.out.println("-> Digite [0] para PAGAR AGORA ou [1] para AGENDAR");
        int opcaoAgendar = Integer.parseInt(scanner.nextLine());

        Pagamento novoPagamento;

        if (opcaoAgendar == 1) {
            System.out.println("\nEm que data será efetuado o pagamento?");
            String dataPagamentoString = scanner.nextLine();
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