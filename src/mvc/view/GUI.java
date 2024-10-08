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

    // Construtor que recebe as instâncias dos DAOs
    public GUI(PessoaDAO pessoaDAO, FornecedorDAO fornecedorDAO, EventoDAO eventoDAO, ConvidadoIndividualDAO convidadoIndividualDAO) {
        this.pessoaDAO = pessoaDAO;
        this.fornecedorDAO = fornecedorDAO;
        this.eventoDAO = eventoDAO;
        this.convidadoIndividualDAO = convidadoIndividualDAO;
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

    public int menuPrincipal() {
        int opcao = -1; // inicializa com um valor inválido

        while (opcao < 0 || opcao > 9) {
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
            this.builder.append("\n| 0 - Sair                        |");
            this.builder.append("\n|                                 |");
            this.builder.append("\n-----------------------------------");
            this.builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 9) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 9.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida! Por favor, digite um número (0, 1, 2, 3, 4 ou 5).");
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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 3.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 12.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
            }
        }

        return opcao;
    }

    public int opEvento() {
        int opcao = -1; // inicializa com um valor inválido

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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
            }
        }

        return opcao;
    }

    public int opFornecedor() {
        int opcao = -1; // inicializa com um valor inválido

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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
            }
        }

        return opcao;
    }

    public int opConvites() {
        int opcao = -1; // inicializa com um valor inválido

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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 2.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
            }
        }

        return opcao;
    }

    public int opConvitesIndividual() {
        int opcao = -1; // inicializa com um valor inválido

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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
            }
        }

        return opcao;
    }

    public int opConvitesFamilia() {
        int opcao = -1; // inicializa com um valor inválido

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
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 5.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida!");
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
        long valor = Long.parseLong(scanner.nextLine());
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
    public ConvidadoIndividual cadastrarConviteIndividual() {
        ConvidadoIndividual nCi = new ConvidadoIndividual();
        System.out.println("\nLista de pessoas/convidados cadastrados no sistema: ");
        pessoaDAO.buscaConvidados();
        System.out.println("\nInforme o ID do convidado(a): ");
        long convidadoId = Long.parseLong(scanner.nextLine());
        Pessoa ci = pessoaDAO.buscaPorId(convidadoId);

        if(ci.getTipoUsuario() != 4) {
            System.out.println("\nUsuário digitado inválido!");
            return this.cadastrarConviteIndividual();
        } else {
            nCi.setPessoa(ci);
        }

        System.out.println("\nQual o nome da família que você faz parte?");
        String nomeFamilia = scanner.nextLine();
        nCi.setFamilia(nomeFamilia);

        System.out.println("\nQual o parentesco do convidado com algum dos noivos?");
        String parentesco = scanner.nextLine();
        nCi.setParentesco(parentesco);

        nCi.setConfirmacao(false);

        return nCi;
    }

    //TODO Formulário para criar um convite família
}