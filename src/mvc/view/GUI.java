package mvc.view;

import mvc.dao.*;
import mvc.model.Evento;
import mvc.model.Fornecedor;

import mvc.model.Pessoa;
import mvc.model.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class GUI {
    Scanner scanner;
    PessoaDAOMemoria pessoaDao;
    FornecedorDAOMemoria fornecedorDao;
    EventoDAOMemoria eventoDao;
    StringBuilder builder;

    public GUI() {
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

        while (opcao < 1 || opcao > 10) {
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 1 || opcao > 10) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 10.");
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

        while (opcao < 0 || opcao > 6) {
            builder.setLength(0);
            builder.append("\n-----------------------------------------");
            builder.append("\n|  * -> Convites                        |");
            builder.append("\n|                                       |");
            builder.append("\n|  1 - Adicionar convidado em evento    |");
            builder.append("\n|  2 - Confirmar convidado no evento    |");
            builder.append("\n|  3 - Mostrar todos os convidados      |");
            builder.append("\n|  4 - Alterar um convidado ind.        |");
            builder.append("\n|  5 - Alterar um convidado fam.        |");
            builder.append("\n|  6 - Excluir convite                  |");
            builder.append("\n|  0 - Sair                             |");
            builder.append("\n|                                       |");
            builder.append("\n-----------------------------------------");
            builder.append("\n\nQual sua opcao? R: ");
            System.out.print(this.builder.toString());

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 6) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 6.");
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

        while (opcao < 0 || opcao > 4) {
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

            if (this.scanner.hasNextInt()) {
                opcao = Integer.parseInt(this.scanner.nextLine());

                if (opcao < 0 || opcao > 4) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e 4.");
                }
            } else {
                // Limpa o buffer do scanner e avisa sobre a entrada inválida
                this.scanner.nextLine();
                System.out.println("Entrada inválida! Por favor, digite um número (0, 1, 2, 3 ou 4).");
            }
        }

        return opcao;
    }

    // =-=-=-=-=CRIACOES=-=-=-=-=-= //
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

        System.out.println("Pessoa cadastrada com sucesso!\n" + novaPessoa);

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
        LocalDate dataEvento = Util.formataData(scanner.nextLine());
        nE.setDataEvento(dataEvento);

        System.out.println("\nLista de cerimonialistas cadastrados no sistema: \n");
        pessoaDao.buscaCerimonialistas();
        System.out.println("\nInforme o ID do cerimonialista desejado: ");
        long cerimonialId = Long.parseLong(scanner.nextLine());
        Pessoa cerimonialista = pessoaDao.buscaPorId(cerimonialId);
        nE.setCerimonial(cerimonialista);

        System.out.println("\nEm qual igreja será realizado o casamento? ");
        String igrejaEvento = scanner.nextLine();
        nE.setIgreja(igrejaEvento);

        System.out.println("\nInforme em qual cartório será cadastro o matrimônio: ");
        String cartorioEvento  = scanner.nextLine();
        nE.setCartorio(cartorioEvento);

        System.out.println("\nQuem serão os noivos?");
        System.out.println("\nLista de noivos cadastrados no sistema: \n");
        pessoaDao.buscaNoivos();
        System.out.println("\nInforme o ID do noivo(a): ");
        long noivo1Id = Long.parseLong(scanner.nextLine());
        Pessoa noivo1 = pessoaDao.buscaPorId(noivo1Id);
        nE.setPessoaNoivo1(noivo1);
        System.out.println("\nAgora, informe ID do outro noivo(a): ");
        long noivo2Id = Long.parseLong(scanner.nextLine());
        Pessoa noivo2 = pessoaDao.buscaPorId(noivo2Id);
        nE.setPessoaNoivo2(noivo2);

        return nE;
    }

    // TODO Método principal para executar o programa
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.menuBoasVindas();
    }
}