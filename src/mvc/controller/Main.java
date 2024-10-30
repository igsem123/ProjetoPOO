package mvc.controller;

import mvc.dao.*;
import mvc.model.*;
import mvc.view.GUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Inicializa os DAOs uma única vez
    PessoaDAO pessoaDAO = new PessoaDAOMemoria();
    FornecedorDAO fornecedorDAO = new FornecedorDAOMemoria();
    EventoDAO eventoDAO = new EventoDAOMemoria(pessoaDAO, fornecedorDAO, 100);
    ConvidadoFamiliaDAO convidadoFamiliaDAO = new ConvidadoFamiliaDAOMemoria(100, pessoaDAO, eventoDAO);
    ConvidadoIndividualDAO convidadoIndividualDAO = new ConvidadoIndividualDAOMemoria(pessoaDAO, convidadoFamiliaDAO, eventoDAO, 100);
    PresentesDAO presentesDAO = new PresentesDAOMemoria(pessoaDAO, 500);
    MuralRecadosDAO muralRecadosDAO = new MuralRecadosDAOMemoria(pessoaDAO, eventoDAO, 1000);
    PagamentoDAO pagamentoDAO = new PagamentoDAOMemoria(pessoaDAO, fornecedorDAO, 1000);
    RelatorioDAOMemoria relatorio = new RelatorioDAOMemoria();

    // Inicializa a GUI passando as instâncias dos DAOs para evitar duplicação de dados
    GUI gui = new GUI(pessoaDAO, fornecedorDAO, eventoDAO, convidadoIndividualDAO, convidadoFamiliaDAO, presentesDAO, muralRecadosDAO, pagamentoDAO);

    // Scanner pode ser utilizado para interações no console, se necessário
    Scanner s = new Scanner(System.in);

    // Calendário do sistema
    Calendario calendario = new Calendario(Util.getDia2());

    public Main() {
        int opcao = -1;

        while(opcao != 4) {  // Continua o loop enquanto não for a opção de sair (4)
            opcao = gui.menuBoasVindas();
            switch (opcao) {
                case 1:
                    int login = 0;
                    while(login != 1) {
                        System.out.println("\nDigite seu login [email]:");
                        String email = this.s.nextLine().trim();
                        System.out.println("\nDigite sua senha: ");
                        String senha = this.s.nextLine().trim();
                        Pessoa logada = pessoaDAO.buscaPessoaLogin(email, senha);

                        if (logada != null) {
                            System.out.println("\nVoce esta logado!");
                            Util.setPessoaLogada(logada);
                            System.out.println("\nSeus dados: \n" + Util.getPessoaLogada().toString());
                            login = 1;
                            this.menuPrincipal();
                        } else {
                            System.out.println("\nLogin Invalido. Tente novamente!");
                        }
                    }

                    break;
                case 2:
                    Pessoa criar = gui.cadastrarPessoa();
                    pessoaDAO.criarPessoa(criar);
                    break;
                case 3:  // Entrar sem login
                    Util.setPessoaLogada(null); // Seta null para os casos de ter feito login antes
                    this.menuSemLogin();  // Método responsável pelo menu sem login
                    break;
                case 4:
                    System.out.println("\nFinalizando sessão no programa. Volte sempre!!");
                    break;
                default:
                    System.out.println("\nDigite um número válido!");
                    break;
            }
        }
    }

    // Método responsável pelo menu sem login
    public void menuSemLogin() {
        int opcaoPrincipal = -1;
        do {
            opcaoPrincipal = gui.menuSemLogin();
            switch (opcaoPrincipal) {
                case 1:
                    int opcaoPresentes = gui.opPresentesConvidados();
                    switch (opcaoPresentes) {
                        case 1:
                            System.out.println("\nPresentes na lista dos noivos: ");
                            presentesDAO.exibeListaPresentesSimples();
                            System.out.println("\nDigite o [ID] do presente que você deseja dar aos noivos: ");
                            long idPresentear = Long.parseLong(s.nextLine());
                            System.out.println("\nDigite o seu nome: ");
                            String nome = s.nextLine();
                            presentesDAO.darPresente(idPresentear, nome);
                            break;
                        case 2:
                            presentesDAO.exibeListaPresentesSimples();
                            break;
                        case 0:
                            System.out.println("\nRetornando ao menu de convidados!");
                            break;
                        default:
                            System.out.println("\nDigite uma opção válida.");
                            break;
                    }
                    break;
                case 2:
                    MuralRecados novoRecado = gui.cadastraRecados();
                    muralRecadosDAO.criarRecado(novoRecado);
                    break;
                case 3:
                    System.out.println("\nRetornando ao menu inicial.");
                    // O loop do `menuSemLogin` será encerrado e voltará ao loop principal
                    break;
                default:
                    System.out.println("\nDigite uma opção válida.");
                    break;
            }
        } while (opcaoPrincipal != 3);  // Sai do menu sem login quando o usuário escolhe "Sair"
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void menuPrincipal() {
        int opcaoPrincipal = -1;

        if(Util.getPessoaLogada().getTipoUsuario() == 1 || Util.getPessoaLogada().getTipoUsuario() == 2 || Util.getPessoaLogada().getTipoUsuario() == 3) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuPrincipal();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        break;
                    case 2:
                        menuPessoa();
                        break;
                    case 3:
                        menuEventos();
                        break;
                    case 4:
                        menuFornecedor();
                        break;
                    case 5:
                        menuConvites();
                        break;
                    case 6:
                        menuPresentes();
                        break;
                    case 7:
                        menuRecados();
                        break;
                    case 8:
                        menuPagamentos();
                        break;
                    case 9:
                        menuRelatorios();
                        break;
                    case 10:
                        menuCalendario();
                        break;
                    case 0:
                        System.out.println("\nSaindo do menu principal!");
                        break;
                    default:
                        System.out.println("\nDigite uma opcao valida");
                        break;
                }
            }
        } else if(Util.getPessoaLogada().getTipoUsuario() == 4) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuConvidado();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        long idConvidado = Util.getPessoaLogada().getId();
                        ConvidadoIndividual perfil = convidadoIndividualDAO.buscarPorId(idConvidado);
                        System.out.println("\nConvite da pessoa logada: \n" + perfil.perfil());
                        break;

                    case 2:
                        int opcaoPresentes = gui.opPresentesConvidados();
                        switch (opcaoPresentes) {
                            case 1:
                                System.out.println("\nPresentes na lista dos noivos: ");
                                presentesDAO.exibeListaPresentesSimples();
                                System.out.println("\nDigite o [ID] do presente que você deseja dar aos noivos: ");
                                long idPresentear = Long.parseLong(s.nextLine());
                                presentesDAO.darPresente(idPresentear, Util.getPessoaLogada());
                                break;

                            case 2:
                                presentesDAO.exibeListaPresentesSimples();
                                break;

                            case 0:
                                System.out.println("\nRetornando ao menu de convidados!");
                                break;

                            default:
                                System.out.println("\nDigite uma opcao valida");
                                break;
                        }

                        break;
                    case 3:
                        MuralRecados novoRecado = gui.cadastraRecados();
                        muralRecadosDAO.criarRecado(novoRecado);
                        break;
                    case 4:
                        long idPessoaLogada = Util.getPessoaLogada().getId();
                        System.out.println("\nDeseja confirmar ou desconfirmar sua presença no evento?");
                        System.out.println("\n->Digite [1] para SIM ou [0] para CANCELAR");
                        int opPresenca = Integer.parseInt(s.nextLine());

                        if(opPresenca == 1) {
                            ConvidadoIndividual convidadoConfirmar = convidadoIndividualDAO.buscarPorId(idPessoaLogada);
                            convidadoIndividualDAO.confirmarPresencaPelaPessoa(convidadoConfirmar);
                        } else {
                            ConvidadoIndividual convidadoNaoConfirmado = convidadoIndividualDAO.buscarPorId(idPessoaLogada);
                            convidadoNaoConfirmado.isConfirmacao();
                        }

                        break;
                    case 5:
                        // Opção confirmar a presença de todos os convites individuais atrelados ao respectivo convite familiar
                        ConvidadoIndividual[] convidados = convidadoIndividualDAO.getConvidados(); // Tive que criar um getter dentro do DAOMemoria para poder acessar os arrays
                        ConvidadoFamilia[] familias = convidadoFamiliaDAO.getFamilias();
                        System.out.println("\nDigite o acesso da família para confirmar seus integrantes: ");
                        String acesso = s.nextLine();

                        if (!acesso.isEmpty()) {
                            convidadoFamiliaDAO.confirmarPresenca(acesso, convidados, familias);
                        }
                        break;
                    case 0:
                        System.out.println("\n0 - Sair");
                        break;
                    default:
                        System.out.println("\nDigite uma opcao valida");
                        break;
                }
            }
        } else {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuUsuarioDefault();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        break;

                    case 2:
                        Pessoa editar = Util.getPessoaLogada();

                        if (editar != null) {
                            System.out.println("\nDigite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
                            String nome = s.nextLine();
                            if(!nome.isEmpty()) {
                                editar.setNome(nome);
                            }

                            System.out.println("\nDigite o novo sexo (ou pressione ENTER para manter o sexo atual): " + editar.getSexo());
                            String sexo = s.nextLine();
                            if(!sexo.isEmpty()) {
                                editar.setSexo(sexo);
                            }

                            System.out.println("\nDigite a nova Data de Nascimento (ou pressione ENTER para manter a data atual): " + editar.getDataNascimento());
                            System.out.println("\nDigite desta forma-> dd/MM/yyyy");
                            String nascimento = s.nextLine();
                            if(!nascimento.isEmpty()) {
                                editar.setDataNascimento(nascimento);
                            }

                            System.out.println("\nDigite o novo email (ou pressione ENTER para manter o email atual): " + editar.getEmail());
                            String login = s.nextLine();
                            if(!login.isEmpty()) {
                                editar.setEmail(login);
                            }

                            System.out.println("\nDigite a nova senha (ou pressione ENTER para manter a senha atual): " + editar.getSenha());
                            String senha = s.nextLine();
                            if(!senha.isEmpty()) {
                                editar.setSenha(senha);
                            }

                            System.out.println("\nDigite o novo tipo do usuario (ou pressione ENTER para manter o tipo atual): " + editar.getTipoUsuario());
                            System.out.println("Escolha um número -> 1 - Noivo(a) | 2 - Cerimonialista | 3 - Administrador | 4 - Convidado");
                            String tipo = s.nextLine();
                            if(!tipo.isEmpty()) {
                                int num = Integer.parseInt(tipo);
                                editar.setTipoUsuario(num);
                            }

                            System.out.println("\nDigite o novo CPF (ou pressione ENTER para manter o CPF atual): " + editar.getCpf());
                            System.out.println("\nDigite desta forma-> 000.000.000-00");
                            String cpfNovo = s.nextLine();
                            if(!cpfNovo.isEmpty()) {
                                editar.setCpf(cpfNovo);
                            }

                            pessoaDAO.atualizarPessoa(editar);
                            System.out.println("\nPessoa alterada com sucesso, alterações:\n");
                            System.out.println(editar.toString());
                        } else {
                            System.out.println("\nNão foi possível alterar o seu perfil!");
                        }
                        break;

                    case 3:
                        menuPrincipal();
                        break;

                    case 0:
                        System.out.println("\n0 - Sair");
                        Util.setPessoaLogada(null);
                        break;

                    default:
                        System.out.println("\nDigite uma opcao valida");
                        break;

                }
            }
        }
    }

    public void menuPessoa() {
        int op;
        do {
            op = gui.opPessoa();
            switch (op) {
                case 1:
                    Pessoa p = gui.cadastrarPessoa();

                    boolean pessoaInserida = pessoaDAO.criarPessoa(p);
                    if (pessoaInserida) {
                        System.out.println("\nPessoa inserida com sucesso!");
                    } else {
                        System.out.println("\nPessoa nao inserida!");
                    }
                    break;

                case 2:
                    pessoaDAO.listarPessoas();
                    break;

                case 3:
                    System.out.println("\nDigite o CPF da pessoa: ");
                    System.out.println("\nEx: 000.000.000-00 ");
                    String cpf = s.nextLine();
                    Pessoa achou = pessoaDAO.buscaPessoa(cpf);
                    if(achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("\nPessoa nao encontrada!");
                    }
                    break;

                case 4:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar: ");
                    String cpfDaPessoa = s.nextLine();
                    atualizarPessoa(cpfDaPessoa);
                    break;

                case 5:
                    System.out.println("\nDigite o CPF da pessoa que deseja excluir: ");
                    System.out.println("Ex: 000.000.000-00 ");
                    String cpfExclusao = s.nextLine();

                    if (pessoaDAO.deletarPessoa(cpfExclusao)) {
                        System.out.println("\nPessoa deletada com sucesso.");
                    } else {
                        System.out.println("\nPessoa nao encontrada!");
                    }
                    break;

                case 6:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar o nome: ");
                    String editaNome = s.nextLine();
                    System.out.println("\nQual o novo nome? ");
                    String novoNome = s.nextLine();

                    if (pessoaDAO.alterarNome(editaNome, novoNome)) {
                        System.out.println("\nPessoa alterada!");
                        novoNome = pessoaDAO.buscaPessoa(editaNome).getNome();
                        System.out.println("Novo nome: " + novoNome);
                    } else {
                        System.out.println("\nPessoa nao alterada!");
                    }
                    break;

                case 7:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar o sexo: ");
                    String editaSexo = s.nextLine();
                    System.out.println("\nQual o novo sexo? ");
                    String novoSexo = s.nextLine();

                    if (pessoaDAO.alterarSexo(editaSexo, novoSexo)) {
                        System.out.println("\nPessoa alterada!");
                        novoSexo = pessoaDAO.buscaPessoa(editaSexo).getSexo();
                        System.out.println("Novo sexo: " + novoSexo);
                    } else {
                        System.out.println("\nPessoa nao alterada!");
                    }
                    break;

                case 8:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar a data de nascimento: ");
                    String editaNascimento = s.nextLine();
                    System.out.println("\nQual a nova data de nascimento? ");
                    String novoNascimento = s.nextLine();

                    if (pessoaDAO.alterarNascimento(editaNascimento, novoNascimento)) {
                        System.out.println("\n Pessoa alterada!");
                        novoNascimento = pessoaDAO.buscaPessoa(editaNascimento).getDataNascimento();
                        System.out.println("Nova data de nascimento: " + novoNascimento);
                    } else {
                        System.out.println("\n Pessoa nao alterada!");
                    }
                    break;

                case 9:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar o email: ");
                    String editaEmail = s.nextLine();
                    System.out.println("\nQual o novo email [Login]? ");
                    String novoEmail = s.nextLine();

                    if (pessoaDAO.alterarEmail(editaEmail, novoEmail)) {
                        System.out.println("\nPessoa alterada!");
                        novoEmail = pessoaDAO.buscaPessoa(editaEmail).getEmail();
                        System.out.println("Novo email: " + novoEmail);
                    } else {
                        System.out.println("\nPessoa nao alterada!");
                    }
                    break;

                case 10:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar a senha: ");
                    String editaSenha = s.nextLine();
                    System.out.println("\nQual a nova senha? ");
                    String novaSenha = s.nextLine();

                    if (pessoaDAO.alterarSenha(editaSenha, novaSenha)) {
                        System.out.println("\nPessoa alterada!");
                        novaSenha = pessoaDAO.buscaPessoa(editaSenha).getSenha();
                        System.out.println("Nova senha: " + novaSenha);
                    } else {
                        System.out.println("\nPessoa nao alterada!");
                    }
                    break;

                case 11:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar o tipo de usuario: ");
                    String editaTipoUsuario = s.nextLine();
                    System.out.println("\nQual o novo tipo de usuário? ");
                    System.out.println("Escolha um número -> 1 - Noivo(a) | 2 - Cerimonialista | 3 - Administrador | 4 - Convidado");
                    int novoTipoUsuario = Integer.parseInt(s.nextLine());

                    if (pessoaDAO.alterarTipoUsuario(editaTipoUsuario, novoTipoUsuario)) {
                        System.out.println("\nPessoa alterada!");
                        novoTipoUsuario = pessoaDAO.buscaPessoa(editaTipoUsuario).getTipoUsuario();
                        System.out.println("Novo tipo de usuário: " + novoTipoUsuario);
                    } else {
                        System.out.println("\nPessoa nao alterada!");
                    }
                    break
                            ;
                case 12:
                    System.out.println("\nDigite o CPF da pessoa que deseja alterar o cadastro de pessoa fisica: ");
                    String editaCPF = s.nextLine();
                    System.out.println("\nQual o novo CPF? ");
                    String novoCPF = s.nextLine();

                    if (pessoaDAO.alterarCpf(editaCPF, novoCPF)) {
                        System.out.println("\nPessoa alterada!");
                        novoCPF = pessoaDAO.buscaPessoa(editaCPF).getCpf();
                        System.out.println("Novo CPF: " + novoCPF);
                    } else {
                        System.out.println("\nPessoa nao alterada!");
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo do modulo Pessoa!");
                    break;

                default:
                    System.out.println("\nDigite um numero valido!");
                    break;
            }
        } while (op != 0);
    }

    public void menuFornecedor() {
        int op = 10;
        do {
            op = gui.opFornecedor();
            switch(op) {
                case 1:
                    System.out.println("\nQual o CNPJ da empresa? ");
                    String cnpj = s.nextLine();

                    Fornecedor achouF = fornecedorDAO.buscaFornecedor(cnpj);
                    if (achouF != null) {
                        System.out.println("\nFornecedor encontrado com sucesso!\n");
                        System.out.println(achouF.toString());
                    } else {
                        System.out.println("\nFornecedor nao encontrado!\n");
                    }
                    break;

                case 2:
                    Fornecedor f = gui.cadastrarFornecedor();

                    boolean fornecedorInserido = fornecedorDAO.criarFornecedor(f);
                    if (fornecedorInserido) {
                        System.out.println("\nFornecedor inserido com sucesso!");
                    } else {
                        System.out.println("\nFornecedor nao inserido!");
                    }
                    break;

                case 3:
                    System.out.println("\nO total de fornecedores é          : ["+ Fornecedor.totalFornecedores +"] ");
                    System.out.println("Esses são os fornecedores cadastrados:\n");
                    fornecedorDAO.listarFornecedores();
                    break;

                case 4:
                    System.out.println("\nPara atualizar, primeiro digite o CNPJ do fornecedor que deseja atualizar: ");
                    String fornecedorCNPJ = s.nextLine();

                    Fornecedor fornecedorEditar = fornecedorDAO.buscaFornecedor(fornecedorCNPJ);

                    if(fornecedorDAO.buscaFornecedor(fornecedorCNPJ) != null) {
                        Fornecedor semEditar = fornecedorDAO.buscaFornecedor(fornecedorCNPJ); // Armazenando o fornecedor sem editar
                        fornecedorEditar = fornecedorDAO.buscaFornecedor(fornecedorCNPJ);

                        System.out.println("\nDigite o novo nome da empresa (ou pressione ENTER para manter o nome atual): " + fornecedorEditar.getNome());
                        String novoNome = s.nextLine();
                        if(!novoNome.isEmpty()) {
                            fornecedorEditar.setNome(novoNome);
                        }

                        System.out.println("\nDigite o novo CNPJ (ou pressione ENTER para manter o CNPJ atual): " + fornecedorEditar.getCNPJ());
                        System.out.println("Digite desta forma-> 00.000.000/0000-00");
                        String novoCnpj = s.nextLine();
                        if(!novoCnpj.isEmpty()) {
                            fornecedorEditar.setCNPJ(novoCnpj);
                        }

                        System.out.println("\nDigite o novo telefone da empresa (ou pressione ENTER para manter o telefone atual): " + fornecedorEditar.getTelefone());
                        System.out.println("Digite desta forma -> +55 DDD X XXXX-XXXX");
                        String novoTelefone = s.nextLine();
                        if(!novoTelefone.isEmpty()) {
                            fornecedorEditar.setTelefone(novoTelefone);
                        }

                        System.out.println("\nDigite o novo valor em débito com a empresa (ou pressione ENTER para manter o valor atual): " + fornecedorEditar.getValorAPagar());
                        String valorAPagar = this.s.nextLine();
                        if(!valorAPagar.isEmpty()) {
                            fornecedorEditar.setValorAPagar(Double.parseDouble(valorAPagar));
                        }

                        System.out.println("\nDigite a nova quantidade de parcelas (ou pressione ENTER para manter a parcela atual): " + fornecedorEditar.getParcelas());
                        String novaParcelaInput = this.s.nextLine();
                        if (!novaParcelaInput.isEmpty()) {
                            int novaParcela = Integer.parseInt(novaParcelaInput);
                            fornecedorEditar.setParcelas(novaParcela);
                        }

                        System.out.println("\nDigite o estado atual da dívida (ou pressione ENTER para manter o estado atual): " + fornecedorEditar.getEstado());
                        String novoEstado = s.nextLine();
                        if(!novoEstado.isEmpty()) {
                            fornecedorEditar.setEstado(novoEstado);
                        }

                        fornecedorDAO.atualizarFornecedor(fornecedorEditar.getCNPJ());

                        if(!semEditar.equals(fornecedorEditar)) {
                            System.out.println("\nFornecedor nao foi alterado!");
                        } else {
                            System.out.println("\nFornecedor alterado com sucesso, alteracoes: ");
                            System.out.println(fornecedorEditar.toString());
                        }
                    }
                    break;

                case 5:
                    System.out.println("\nQual o [ID] do fornecedor que deseja deletar do sistema?\n");
                    fornecedorDAO.exibeFornecedoresSimples();
                    System.out.println("\nDigite o [ID]: ");
                    long fornecedorId = Long.parseLong(s.nextLine());
                    fornecedorDAO.deletarFornecedor(fornecedorId);
                    break;

            }
        } while (op != 0);
    }

    public void menuEventos() {
        int op = -1;
        do {
            op = gui.opEvento();
            switch(op) {
                case 1:
                    Evento e = gui.cadastrarEvento();
                    eventoDAO.criarEvento(e);
                    break;

                case 2:
                    System.out.println("\nQual o [ID] do evento? ");
                    long buscaEventoId = Long.parseLong(s.nextLine()); //Conferir se vai dar certo

                    Evento achouE = eventoDAO.buscarPorId(buscaEventoId);
                    if (achouE != null) {
                        System.out.println("\nEvento encontrado com sucesso!\n");
                        System.out.println(achouE.toString());
                    } else {
                        System.out.println("\nEvento nao encontrado!");
                    }
                    break;

                case 3:
                    System.out.println("\nO total de eventos no sistema é    : ["+ Evento.totalEventos +"] ");
                    System.out.println("Esses são os eventos encontrados:\n");
                    eventoDAO.listarEventos();
                    break;

                case 4:
                    System.out.println("\nLista de eventos cadastrados no sistema: ");
                    eventoDAO.exibirListaEventosSimples();

                    System.out.println("\nDigite o [ID] de qual evento deseja editar: ");
                    long editarId = Long.parseLong(s.nextLine());

                    Evento editarEvento = eventoDAO.buscarPorId(editarId);

                    if (editarEvento != null) {
                        System.out.println("\nEvento encontrado com sucesso!");

                        System.out.println("\nDigite a nova data do evento (ou pressione ENTER para manter a data atual): " + editarEvento.getDataEvento());
                        System.out.println("-> Digite desta forma: DD/MM/AAAA ");
                        String dataEvento = s.nextLine();
                        if(dataEvento.equals(editarEvento.getDataEvento())) {
                            editarEvento.setDataEvento(dataEvento);
                        }

                        System.out.println("\nAinda é o mesmo cerimonialista? (ou pressione [0] para manter o cerimonialista atual): " + editarEvento.getCerimonial().getNome());
                        System.out.println("\nDigite 1 para [SIM] ou 0 para [NAO]");
                        int respostaCerimonial = Integer.parseInt(s.nextLine());

                        if(respostaCerimonial == 0) {
                            System.out.println("\nEscolha o novo cerimonialista do evento: ");
                            System.out.println("\nLista de cerimonialistas cadastrados no sistema: \n");
                            pessoaDAO.buscaCerimonialistas();
                            System.out.println("\nDigite o [ID] abaixo: ");
                            long cerimonialId = Long.parseLong(s.nextLine());
                            Pessoa cerimonialista = pessoaDAO.buscaPorId(cerimonialId);
                            if (cerimonialista != null) {
                                editarEvento.setCerimonial(cerimonialista);
                            }
                        }

                        System.out.println("\nDigite a nova igreja em que será realizado o casamento (ou pressione ENTER para manter a igreja atual): " + editarEvento.getIgreja());
                        String igrejaEvento = s.nextLine();
                        if(!igrejaEvento.isEmpty()) {
                            editarEvento.setIgreja(igrejaEvento);
                        }

                        System.out.println("\nDigite qual o novo cartório que cadastrará o matrimônio (ou pressione ENTER para manter o cartório atual): " + editarEvento.getCartorio());
                        String cartorioEvento  = s.nextLine();
                        if(!cartorioEvento.isEmpty()) {
                            editarEvento.setCartorio(cartorioEvento);
                        }

                        System.out.println("\nAinda são os mesmo noivos?");
                        System.out.println("\nDigite 1 para [SIM] ou 0 para [NAO]");
                        int respostaNoivos = Integer.parseInt(s.nextLine());

                        if(respostaNoivos == 0) {
                            System.out.println("\nLista de noivos(a) cadastrados no sistema: ");
                            pessoaDAO.buscaNoivos();

                            System.out.println("\nDigite qual o [ID] do noivo(a) (ou pressione ENTER para manter o noivo(a) atual)" + editarEvento.getPessoaNoivo1().getNome());
                            long noivo1Id = Long.parseLong(s.nextLine());

                            if(noivo1Id != 0) {
                                Pessoa noivo1 = pessoaDAO.buscaPorId(noivo1Id);
                                editarEvento.setPessoaNoivo1(noivo1);
                            }

                            System.out.println("\nAgora, digite qual o [ID] do outro(a) noivo(a) (ou pressione ENTER para manter o noivo(a) atual)" + editarEvento.getPessoaNoivo2().getNome());
                            long noivo2Id = Long.parseLong(s.nextLine());

                            if(noivo2Id != 0) {
                                Pessoa noivo2 = pessoaDAO.buscaPorId(noivo2Id);
                                editarEvento.setPessoaNoivo2(noivo2);
                            }

                            atualizarListaDeFornecedoresNoEvento(editarId, editarEvento); // Metodo para atualizar a lista de fornecedores caso haja alteração nos noivos
                            System.out.println("\nEvento atualizado com sucesso!");
                            System.out.println("\nEvento com os dados atualizados:\n\n" + editarEvento.toString());

                        } else {
                            atualizarListaDeFornecedoresNoEvento(editarId, editarEvento); // Metodo para atualizar a lista de fornecedores caso não haja alteração nos noivos
                            System.out.println("\nEvento com os dados atualizados:\n\n" + editarEvento.toString());
                        }

                    } else {
                        System.out.println("\nEvento nao encontrado!");
                    }
                    break;

                case 5:
                    System.out.println("\nQual o [ID] do evento que deseja remover do sistema?\n");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nDigite o [ID]: ");
                    long eventoId = Long.parseLong(s.nextLine());
                    eventoDAO.removerEvento(eventoId);
                    break;

            }
        } while (op != 0);
    }

    private void atualizarListaDeFornecedoresNoEvento(long editarId, Evento editarEvento) {
        System.out.println("\nAinda são os mesmos fornecedores?");
        System.out.println("\nDigite 1 para [SIM] ou 0 para [NAO]");
        int respostaFornecedores = Integer.parseInt(s.nextLine());

        if(respostaFornecedores == 0) {
            editarEvento.clearFornecedores(); // Limpa a lista de fornecedores
            System.out.println("\nLista de fornecedores cadastrados no sistema: ");
            fornecedorDAO.exibeFornecedoresSimples();

            System.out.println("\nDigite o [ID] do fornecedor que deseja adicionar: ");
            long fornecedorId = Long.parseLong(s.nextLine());

            if(fornecedorId != 0) {
                Fornecedor fornecedor = fornecedorDAO.buscaPorId(fornecedorId);
                editarEvento.addFornecedor(fornecedor);
            }

            System.out.println("\nHá mais fornecedores para adicionar?");
            System.out.println("\nDigite 1 para [SIM] ou 0 para [NAO]");
            int respostaMaisFornecedores = Integer.parseInt(s.nextLine());

            while (respostaMaisFornecedores == 1) {
                System.out.println("\nLista de fornecedores cadastrados no sistema: ");
                fornecedorDAO.exibeFornecedoresSimples();

                System.out.println("\nDigite o [ID] do fornecedor que deseja adicionar: ");
                long fornecedorIdAdicionar = Long.parseLong(s.nextLine());

                if (fornecedorIdAdicionar != 0) {
                    Fornecedor fornecedorAdicionar = fornecedorDAO.buscaPorId(fornecedorIdAdicionar);
                    editarEvento.addFornecedor(fornecedorAdicionar);
                }

                System.out.println("\nHá mais fornecedores para adicionar?");
                System.out.println("\nDigite 1 para [SIM] ou 0 para [NAO]");
                respostaMaisFornecedores = Integer.parseInt(s.nextLine());
            }
        }

        editarEvento.setNomeDoEvento(editarEvento.getNomeDoEvento()); // Atualiza o nome do evento
        eventoDAO.atualizarEvento(editarId, editarEvento); // Atualiza o evento com os fornecedores adicionados e outros dados atualizados
    }

    public void menuConvites() {
        int op = -1;
        do {
            op = gui.opConvites();
            switch(op) {
                case 1:

                    int opConviteInd = -1;
                    do {
                        opConviteInd = gui.opConvitesIndividual();
                        switch(opConviteInd) {
                            case 1:
                                ConvidadoIndividual criaConv = gui.cadastrarConviteIndividual(convidadoFamiliaDAO);

                                if (criaConv != null) {
                                    convidadoIndividualDAO.criarConvidado(criaConv);
                                } else {
                                    System.out.println("\nNão foi possível criar o convite individual!");
                                }
                                break;

                            case 2:
                                System.out.println("\nLista de convidados cadastrados no sistema: ");
                                convidadoIndividualDAO.exibirConvidadosSimples();
                                System.out.println("\nDigite o [ID] do convidado que deseja confirmar a presença: ");
                                long id = Long.parseLong(s.nextLine());
                                ConvidadoIndividual confirmar = convidadoIndividualDAO.buscarPorId(id);
                                convidadoIndividualDAO.confirmarConvidado(confirmar);
                                break;

                            case 3:
                                System.out.println("\nMostrando todos os convites individuais criados no sistema: \n");
                                convidadoIndividualDAO.listarConvidados();
                                break;

                            case 4:
                                System.out.println("\nLista de convidados individuais cadastrados no sistema: ");
                                convidadoIndividualDAO.exibirConvidadosSimples();

                                System.out.println("\nDigite o [ID] de qual convidado deseja editar: ");
                                long convidadoEditarId = Long.parseLong(s.nextLine());

                                ConvidadoIndividual convidadoEditar = convidadoIndividualDAO.buscarPorId(convidadoEditarId);

                                if (convidadoEditar != null) {
                                    System.out.println("\nConvidado individual encontrado com sucesso!");

                                    System.out.println("\nDeseja mudar a pessoa cadastrada no convite (ou pressione ENTER para manter a pessoa atual): " + convidadoEditar.getPessoa().getNome() + "\n");
                                    pessoaDAO.buscaConvidados();
                                    System.out.println("\nSe deseja mudar, digite o [ID] da pessoa que possui este convite: ");
                                    long idPessoaConvite = Long.parseLong(s.nextLine());
                                    Pessoa novoConvidado = pessoaDAO.buscaPorId(idPessoaConvite);

                                    if (novoConvidado != null) {
                                        convidadoEditar.setPessoa(novoConvidado);
                                    }

                                    System.out.println("\nDeseja mudar a familia de qual o convidado faz parte (ou pressione ENTER para manter a familia atual): " + convidadoEditar.getFamilia().getNomeFamilia());
                                    System.out.println("\nLista de Famílias cadastradas no sistema: ");
                                    convidadoFamiliaDAO.listarFamilias();
                                    System.out.println("\nDigite o [ID] ou pressione ENTER para manter: ");
                                    long idNovaFam = Long.parseLong(s.nextLine());
                                    ConvidadoFamilia novaFamiliaEditar = convidadoFamiliaDAO.buscarPorId(idNovaFam);

                                    if (novaFamiliaEditar != null) {
                                        convidadoEditar.setFamilia(novaFamiliaEditar);
                                    }

                                    System.out.println("\nDeseja mudar o parentesco do convidado (ou pressione ENTER para manter o parentesco atual): " + convidadoEditar.getParentesco());
                                    String novoParentesco = s.nextLine();

                                    if(novoParentesco != null) {
                                        convidadoEditar.setParentesco(novoParentesco);
                                    }

                                    convidadoIndividualDAO.atualizarConvidado(convidadoEditarId, convidadoEditar);
                                    System.out.println("\nConvidado com os dados atualizados:\n\n" + convidadoEditar.toString());
                                }
                                break;

                            case 5:
                                System.out.println("\nLista de convidados cadastrados no sistema: ");
                                convidadoIndividualDAO.exibirConvidadosSimples();
                                System.out.println("\nDigite o [ID] do convidado que deseja remover do sistema: ");
                                long idRemover = Long.parseLong(s.nextLine());
                                convidadoIndividualDAO.removerConvidado(idRemover);
                                break;
                            case 0:
                                System.out.println("\nSaindo do modulo de gerenciamento dos convites individuais!");
                                break;
                        }

                    } while (opConviteInd != 0);
                    break;

                case 2:
                    int opConviteFam = -1;
                    do {
                        opConviteFam = gui.opConvitesFamilia();
                        switch (opConviteFam) {
                            case 1:
                                ConvidadoFamilia criaConvFam = gui.cadastraConviteFamiliar();
                                convidadoFamiliaDAO.criarFamilia(criaConvFam);
                                break;

                            case 2:
                                ConvidadoIndividual[] convidados = convidadoIndividualDAO.getConvidados();
                                ConvidadoFamilia[] familias = convidadoFamiliaDAO.getFamilias();
                                System.out.println("\nDigite o acesso da família para confirmar seus integrantes: ");
                                String acesso = s.nextLine();

                                if (!acesso.isEmpty()) {
                                    convidadoFamiliaDAO.confirmarPresenca(acesso, convidados, familias);
                                }
                                break;

                            case 3:
                                System.out.println("\nLista de Famílias cadastradas no sistema: \n");
                                convidadoFamiliaDAO.exibirFamilias();
                                break;

                            case 4:
                                System.out.println("\nLista de Famílias cadastradas no sistema: ");
                                convidadoFamiliaDAO.listarFamilias();
                                System.out.println("\nDigite o [ID] da família que deseja editar: ");
                                long idFam = Long.parseLong(s.nextLine());
                                ConvidadoFamilia editar = convidadoFamiliaDAO.buscarPorId(idFam);

                                if (editar != null) {
                                    System.out.println("\nDigite o novo nome da família: (Nome atual: " + editar.getNomeFamilia() + ")");
                                    String nome = s.nextLine();
                                    if(!nome.isEmpty()) {
                                        editar.setNomeFamilia(nome);
                                    }
                                    convidadoFamiliaDAO.atualizarFamilia(idFam, editar);
                                }
                                break;

                            case 5:
                                System.out.println("\nLista de Famílias cadastradas no sistema: ");
                                convidadoFamiliaDAO.listarFamilias();
                                System.out.println("\nDigite o [ID] da família que deseja remover: ");
                                long idFamRemover = Long.parseLong(s.nextLine());
                                convidadoFamiliaDAO.removerFamilia(idFamRemover);
                                break;

                            case 0:
                                System.out.println("\nSaindo do modulo de gerenciamento dos convites familiar!");
                                break;

                        }

                    } while (opConviteFam != 0);

                    break;
            }
        } while (op != 0);
    }

    public void menuPresentes() {
        int op;
        do {
            op = gui.opPresentes();
            switch(op) {
                case 1:
                    Presentes novoPresente = gui.cadastraPresente();
                    presentesDAO.adicionarPresente(novoPresente);
                    break;

                case 2:
                    System.out.println("\nLista de presentes cadastrados no sistema: ");
                    presentesDAO.exibeListaPresentesSimples();
                    break;

                case 3:
                    System.out.println("\nInforme o [ID] do presente que deseja visualizar as informações: ");
                    long idPresente = Long.parseLong(s.nextLine());
                    presentesDAO.buscarPorId(idPresente);
                    break;

                case 4:
                    System.out.println("\nLista de presentes cadastrados no sistema: ");
                    presentesDAO.exibeListaPresentesSimples();
                    System.out.println("\nInforme o [ID] do presente que deseja atualizar: ");
                    long idPresenteAtualizar = Long.parseLong(s.nextLine());

                    Presentes presenteEditar = presentesDAO.buscarPorId(idPresenteAtualizar);

                    while (presenteEditar == null) {
                        System.out.println("\nO [ID] informado é inválido! Digite novamente: ");
                        idPresenteAtualizar = Long.parseLong(s.nextLine());
                        presenteEditar = presentesDAO.buscarPorId(idPresenteAtualizar);
                    }

                    System.out.println("\nDigite o novo nome do presente (ou pressione ENTER para manter o nome atual): " + presenteEditar.getNome());
                    String presenteNome = s.nextLine();

                    if(!presenteNome.isEmpty()) {
                        presenteEditar.setNome(presenteNome);
                    }

                    System.out.println("\nDigite o novo tipo do presente (ou pressione ENTER para manter o tipo atual): " + presenteEditar.getTipo());
                    System.out.println("->Digite desta forma: 1 - Cozinha/Geral | 2 - Cozinha/Eletrodomésticos | 3 - Decoração/Cama/Mesa/Banho | 4 - Moveis/Eletronicos | 5 - Dinheiro");
                    String presenteTipo = s.nextLine();

                    if(!presenteTipo.isEmpty()) {
                        presenteEditar.setTipo(Integer.parseInt(presenteTipo));
                    }

                    System.out.println("\nDigite o novo valor do presente (ou pressione ENTER para manter o valor atual): " + presenteEditar.getValor());
                    double presenteValor = Double.parseDouble(s.nextLine());

                    if (presenteValor != -1) {
                        presenteEditar.setValor(presenteValor);
                    }

                    System.out.println("\nEsse presente já foi presentado aos noivos por algum convidado?");
                    System.out.println("\n->Digite [1] para SIM e [0] para NÃO.");
                    int opcaoPresente = Integer.parseInt(s.nextLine());

                    if (opcaoPresente == 1) {
                        System.out.println("\nLista de convidados cadastrados no sistema: ");
                        pessoaDAO.buscaConvidados();
                        System.out.println("\nDigite o [ID] do convidado que enviou este presente: ");
                        long idConvPresente = Long.parseLong(s.nextLine());
                        Pessoa pessoaPresente = pessoaDAO.buscaPorId(idConvPresente);

                        if (pessoaPresente != null) {
                            presenteEditar.setPessoa(pessoaPresente);
                        }
                    } else {
                        presenteEditar.setPessoa(null);
                    }

                    presentesDAO.atualizarPresente(idPresenteAtualizar, presenteEditar); // Atualiza o presente com as novas infos
                    break;

                case 5:
                    System.out.println("\nLista de presentes cadastrados no sistema: ");
                    presentesDAO.exibeListaPresentesSimples();
                    System.out.println("\nInforme o [ID] do presente que deseja remover: ");
                    long idPresenteRemover = Long.parseLong(s.nextLine());

                    presentesDAO.removerPresente(idPresenteRemover);
                    break;

                case 0:
                    System.out.println("\nSaindo do modulo de gerenciamento dos presentes!");
                    break;

            }
        } while (op != 0);
    }

    public void menuRecados() {
        int op;
        do {
            op = gui.opRecados();
            switch(op) {
                case 1:
                    MuralRecados novoRecado = gui.cadastraRecados();
                    muralRecadosDAO.criarRecado(novoRecado);
                    break;

                case 2:
                    System.out.println("\nLista de recados cadastrados no sistema: \n");
                    muralRecadosDAO.exibeListaDeRecados();
                    break;

                case 3:
                    System.out.println("\nDe qual evento deseja visualizar os recados? ");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nInforme o [ID]: ");
                    long idEventoDoRecado = Long.parseLong(s.nextLine());
                    Evento eventoDoRecado = eventoDAO.buscarPorId(idEventoDoRecado);

                    if (eventoDoRecado != null) {
                        System.out.println("\nRecados registrados para o " + eventoDoRecado.getNomeDoEvento() + "\n");
                        muralRecadosDAO.exibeListaDeRecadosPorEvento(eventoDoRecado);
                    }
                    break;

                case 4:
                    System.out.println("\nDe qual convidado você deseja visualizar o recado escrito? ");
                    pessoaDAO.buscaConvidados();
                    System.out.println("\nInforme o [ID] do convidado: ");
                    long idPessoaQueEscreveu = Long.parseLong(s.nextLine());
                    Pessoa quemEscreveu = pessoaDAO.buscaPorId(idPessoaQueEscreveu);
                    if (quemEscreveu != null) {
                        muralRecadosDAO.buscaRecadoPorPessoa(quemEscreveu);
                    }

                    break;

                case 5:
                    System.out.println("\nQual recado deseja editar? ");
                    muralRecadosDAO.exibeListaDeRecados();
                    System.out.println("\nInforme o [Nº] do recado: ");
                    long idRecadoEditar = Long.parseLong(s.nextLine());
                    MuralRecados recadoEditar = muralRecadosDAO.buscarPorId(idRecadoEditar);

                    if (recadoEditar != null) {
                        System.out.println("\nFoi esse convidado(a) quem escreveu o recado? Ou deseja alterá-lo? (pressione ENTER para manter o convidado(a) atual: " + recadoEditar.getPessoa().getNome() + ")");
                        System.out.println("\n->Se deseja alterá-lo digite [1], se não pressione ENTER");
                        int opcaoAlterarRecado = Integer.parseInt(s.nextLine());

                        if (opcaoAlterarRecado == 1) {
                            System.out.println("\nLista de convidados cadastrados no sistema: ");
                            pessoaDAO.buscaConvidados();
                            System.out.println("\nInforme o [ID] do convidado(a) correto: ");
                            long idConvidado = Long.parseLong(s.nextLine());

                            while (pessoaDAO.buscaPorId(idConvidado) == null) {
                                System.out.println("\n[ID] informado inválido, tente novamente informando o convidado(a) correto: ");
                                idConvidado = Long.parseLong(s.nextLine());
                            }

                            Pessoa convidadoCorretoDoRecado = pessoaDAO.buscaPorId(idConvidado);
                            recadoEditar.setPessoa(convidadoCorretoDoRecado);
                        }

                        System.out.println("\nDigite um novo comentário (ou pressione ENTER para manter o recado atual)");
                        String novoComentario = s.nextLine();

                        if (!novoComentario.isEmpty()) {
                            recadoEditar.setComentario(novoComentario);
                        }

                        muralRecadosDAO.editaRecado(recadoEditar);
                    }
                    break;
                case 6:
                    System.out.println("\nLista de recados cadastrados no sistema: ");
                    muralRecadosDAO.exibeListaDeRecados();
                    System.out.println("\nInforme o [Nº] do recado: ");
                    long idRecadoRemover = Long.parseLong(s.nextLine());
                    MuralRecados recadoRemovido = muralRecadosDAO.buscarPorId(idRecadoRemover);

                    if (recadoRemovido != null) {
                        muralRecadosDAO.removeRecado(recadoRemovido);
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo do modulo de gerenciamento do mural de recados!");
                    break;
            }
        } while (op != 0);
    }

    public void menuPagamentos() {
        int op;
        do {
            op = gui.opPagamentos();
            switch(op) {
                case 1:
                    Pagamento novoPagamento = gui.cadastraPagamento();
                    if (pagamentoDAO.criarPagamento(novoPagamento)) {
                        System.out.println("\nPagamento feito ou agendado com sucesso! ");
                    }
                    break;

                case 2:
                    System.out.println("\nExibindo lista de pagamentos registrados no sistema: \n");
                    pagamentoDAO.exibirListaSimplesPagamentos();
                    System.out.println("\nInforme o [ID] do pagamento que deseja buscar informações completas: ");
                    long idPagamento = Long.parseLong(s.nextLine());
                    pagamentoDAO.buscarPagamentoPorId(idPagamento);
                    break;

                case 3:
                    System.out.println("\nLista de pagamentos registrados pelo sistema: ");
                    pagamentoDAO.listarPagamentos();
                    break;

                case 4:
                    System.out.println("\nLista de pagamentos registrados no sistema: ");
                    pagamentoDAO.exibirListaSimplesPagamentos();
                    System.out.println("\nInforme o [ID] do pagamento que deseja atualizar: ");
                    long idPagamentoAtualizar = Long.parseLong(s.nextLine());

                    Pagamento pagamentoEditar = pagamentoDAO.buscarPagamentoPorId(idPagamentoAtualizar);

                    while (pagamentoEditar == null) {
                        System.out.println("\nO [ID] informado é inválido! Digite novamente: ");
                        idPagamentoAtualizar = Long.parseLong(s.nextLine());
                        pagamentoEditar = pagamentoDAO.buscarPagamentoPorId(idPagamentoAtualizar);
                    }

                    System.out.println("\nFoi registrado o pagador correto? (pressione [1] para manter o pagador atual: " + pagamentoEditar.getPessoa().getNome() + ")");
                    System.out.println("\n->Digite [1] para SIM ou [0] para NÃO.");
                    int opcaoPagador = Integer.parseInt(s.nextLine());

                    if (opcaoPagador == 0) {
                        System.out.println("\nLista de noivos cadastrados no sistema: ");
                        pessoaDAO.buscaNoivos();
                        System.out.println("\nDigite o [ID] do noivo que realizou este pagamento: ");
                        long idNoivoPagamento = Long.parseLong(s.nextLine());
                        Pessoa noivoQuePagou = pessoaDAO.buscaPorId(idNoivoPagamento);

                        while (noivoQuePagou.getTipoUsuario() != 1 || pessoaDAO.buscaPorId(idNoivoPagamento) == null) {
                            System.out.println("\nTente novamente, o [ID] informado não é de um noivo(a).");
                            System.out.println("\nInforme um [ID] de uma pessoa que se encontra na lista:");
                            idNoivoPagamento = Long.parseLong(s.nextLine());
                            noivoQuePagou = pessoaDAO.buscaPorId(idNoivoPagamento);
                        }

                        pagamentoEditar.setPessoa(noivoQuePagou);
                    }

                    System.out.println("\nFoi registrado o fornecedor correto? (pressione [1] para manter o fornecedor atual: " + pagamentoEditar.getFornecedor().getNome() + ")");
                    System.out.println("\n->Digite [1] para SIM ou [0] para NÃO.");
                    int opcaoFornecedor = Integer.parseInt(s.nextLine());

                    if (opcaoFornecedor == 0) {
                        System.out.println("\nLista de fornecedores cadastrados no sistema: ");
                        fornecedorDAO.exibeFornecedoresSimples();
                        System.out.println("\nDigite o [ID] do fornecedor que recebeu este pagamento: ");
                        long idFornecedorPagamento = Long.parseLong(s.nextLine());
                        Fornecedor fornecedorQueRecebeu = fornecedorDAO.buscaPorId(idFornecedorPagamento);

                        while (fornecedorQueRecebeu == null) {
                            System.out.println("\nTente novamente, o [ID] informado é inválido.");
                            System.out.println("\nInforme um [ID] de um fornecedor que se encontra na lista:");
                            idFornecedorPagamento = Long.parseLong(s.nextLine());
                            fornecedorQueRecebeu = fornecedorDAO.buscaPorId(idFornecedorPagamento);
                        }

                        pagamentoEditar.setFornecedor(fornecedorQueRecebeu);
                    }

                    System.out.println("\nDigite uma nova descrição (ou pressione ENTER para manter a descrição atual) ");
                    String descricao = s.nextLine();

                    if (!descricao.isEmpty()) {
                        pagamentoEditar.setDescricao(descricao);
                    }

                    System.out.println("\nAs parcelas foram registradas corretamente? As parcelas informadas foram: " + pagamentoEditar.getParcela());
                    System.out.println("Digite [1] para SIM ou [0] para NÃO?");
                    int opcaoParcela = Integer.parseInt(s.nextLine());

                    if (opcaoParcela == 0) {
                        double valorInicial = pagamentoEditar.getFornecedor().getValorInicial();
                        int parcelaInicial = pagamentoEditar.getFornecedor().getParcelaInicial();
                        double valorDaParcelaInicial = (valorInicial / parcelaInicial);

                        System.out.println("\nO débito inicial foi encontrado em nosso sistema, era de R$ " + valorInicial + ".");
                        System.out.println("\nO noivo(a) havia escolhido pagar em " + parcelaInicial + " parcela(s).");
                        System.out.printf("\nO valor da(s) parcela(s) é de R$ " + valorDaParcelaInicial + ".");

                        System.out.println("\nQual o número correto de parcelas pagas pelo noivo(a) neste ciclo? ");
                        int parcelaCorreta = Integer.parseInt(s.nextLine());
                        pagamentoEditar.setParcela(parcelaCorreta);

                        // Cálculo do valor correto deste pagamento com base nos valores iniciais
                        double valorTotalPagamento = parcelaCorreta * valorDaParcelaInicial;

                        System.out.println("\nApós essa atualização, o valor correto total deste pagamento será: " + valorTotalPagamento);
                        pagamentoEditar.setValor(valorTotalPagamento);

                        System.out.println("\nO pagamento foi agendado ou não?");
                        System.out.println("Digite [1] para SIM ou [0] para NÃO?");
                        int opcaoAgendado = Integer.parseInt(s.nextLine());

                        if (opcaoAgendado == 1) {
                            System.out.println("\nQual a nova data para o pagamento? (ou pressione ENTER para manter a data atual: " + pagamentoEditar.getDataPagamento() + ")");
                            String novaData = s.nextLine();

                            if (!novaData.isEmpty()) {
                                LocalDate novaDataDePagamento = Util.formataData(novaData);
                                pagamentoEditar.setDataPagamento(novaDataDePagamento);
                                pagamentoEditar.setAgendado(true);
                            }
                        } else {
                            // Atualizações do fornecedor
                            pagamentoEditar.getFornecedor().setValorAPagar(pagamentoEditar.getFornecedor().getValorAPagar() - valorTotalPagamento);
                            pagamentoEditar.getFornecedor().setParcelas(pagamentoEditar.getFornecedor().getParcelas() - parcelaCorreta);

                            pagamentoEditar.setAgendado(false);
                            pagamentoEditar.setDataPagamento(LocalDate.now());
                        }

                        // Se todas as parcelas foram pagas, atualiza o estado do fornecedor
                        if (pagamentoEditar.getFornecedor().getValorAPagar() <= 0) {
                            pagamentoEditar.getFornecedor().setEstado("Pago Completo");
                            System.out.println("O fornecedor " + pagamentoEditar.getFornecedor().getNome() + " foi totalmente pago.");
                        } else {
                            System.out.println("O fornecedor " + pagamentoEditar.getFornecedor().getNome() + " ainda possui saldo em aberto: " + pagamentoEditar.getFornecedor().getValorAPagar());
                        }

                        // Comentário para o professor: não sei se precisava atualizar um pagamento, mas tentei fazer
                        pagamentoDAO.atualizarPagamento(pagamentoEditar); // aq atualizo os dados com parcela editada e/ou agendamento editado
                    } else {

                        System.out.println("\nO pagamento foi agendado ou não?");
                        System.out.println("Digite [1] para SIM ou [0] para NÃO?");
                        int opcaoAgendado = Integer.parseInt(s.nextLine());

                        if (opcaoAgendado == 1) {
                            System.out.println("\nQual a nova data para o pagamento? (ou pressione ENTER para manter a data atual: " + pagamentoEditar.getDataPagamento() + ")");
                            String novaData = s.nextLine();

                            if (!novaData.isEmpty()) {
                                LocalDate novaDataDePagamento = Util.formataData(novaData);
                                pagamentoEditar.setDataPagamento(novaDataDePagamento);
                            }
                        } else {
                            pagamentoEditar.setDataPagamento(LocalDate.now());
                        }
                        // Atualizando o pagamento sem a parcela editada / mas com possivelmente o agendamento editado
                        pagamentoDAO.atualizarPagamento(pagamentoEditar); // Fiz essa buçanha sem ir testando, se não funcionar eu me **** e dou um update :DDD
                    }
                    break;

                case 5:
                    System.out.println("\nLista de pagamentos registrados no sistema: ");
                    pagamentoDAO.exibirListaSimplesPagamentos();
                    System.out.println("\nInforme o [ID] do pagamento que deseja remover dos registros: ");
                    long idPagamentoRemover = Long.parseLong(s.nextLine());

                    if (pagamentoDAO.removerPagamento(idPagamentoRemover)) {
                        System.out.println("\nPagamento removido dos registros com sucesso!");
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo do modulo de gerenciamento dos pagamentos!");
                    break;

            }
        } while (op != 0);
    }

    public void menuCalendario() {
        // Métodos intrinsecos do calendário, vão funcionar ao abrir a opção
        calendario.notificarPagamentosProximos(pagamentoDAO.getPagamentos(), 3); // Setando dia de antecência para notificar o adm sobre um pagamento agendado em data próx
        calendario.notificarPagamentosAtrasados(pagamentoDAO.getPagamentos());

        int op;
        do {
            op = gui.opCalendario();
            switch(op) {
                case 1:
                    calendario.verificarPagamentosAgendados(Util.getDia(), pagamentoDAO.getPagamentos());
                    LocalDateTime dataAtual = calendario.getDataAtual().atStartOfDay();
                    if (calendario.verificarPagamentosAgendados(dataAtual, pagamentoDAO.getPagamentos())) {
                        System.out.println("\nExistem pagamentos feitos com sucesso para hoje, deseja visualizá-los?");
                        System.out.println("Digite [1] para SIM e [0] para NÃO.");
                        int opcao = Integer.parseInt(s.nextLine());
                        if (opcao == 1) {
                            calendario.exibirPagamentosAgendados(dataAtual, pagamentoDAO.getPagamentos());
                        }
                    } else {
                        System.out.println("\nNão existem pagamentos agendados para hoje.");
                    }
                    break;

                case 2:
                    System.out.println("\nConferindo se existem pagamentos a serem feitos e realizando-os:");
                    calendario.avancarDiasEconferirPagamentos(pagamentoDAO.getPagamentos());
                    break;
                case 3:
                    calendario.exibirDataAtual();
                    System.out.println("\nDigite a nova data do sistema (dd/MM/yyyy):");
                    String novaData = s.next();
                    LocalDate dataModificada = Util.formataData(novaData);
                    calendario.atualizarData(dataModificada);
                    break;

                case 0:
                    System.out.println("\nSaindo do modulo calendario!");
                    break;
            }
        } while (op != 0);
    }

    private void atualizarPessoa(String cpfDaPessoa) {
        Pessoa editar = pessoaDAO.buscaPessoa(cpfDaPessoa); // Retorna o objeto Pessoa e armazena

        if(editar != null) {
            System.out.println("\nDigite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
            String nome = s.nextLine();
            if(!nome.isEmpty()) {
                editar.setNome(nome);
            }

            System.out.println("\nDigite o novo sexo (ou pressione ENTER para manter o sexo atual): " + editar.getSexo());
            String sexo = s.nextLine();
            if(!sexo.isEmpty()) {
                editar.setSexo(sexo);
            }

            System.out.println("\nDigite a nova Data de Nascimento (ou pressione ENTER para manter a data atual): " + editar.getDataNascimento());
            System.out.println("\nDigite desta forma-> dd/MM/yyyy");
            String nascimento = s.nextLine();
            if(!nascimento.isEmpty()) {
                editar.setDataNascimento(nascimento);
            }

            System.out.println("\nDigite o novo email (ou pressione ENTER para manter o email atual): " + editar.getEmail());
            String login = s.nextLine();
            if(!login.isEmpty()) {
                editar.setEmail(login);
            }

            System.out.println("\nDigite a nova senha (ou pressione ENTER para manter a senha atual): " + editar.getSenha());
            String senha = s.nextLine();
            if(!senha.isEmpty()) {
                editar.setSenha(senha);
            }

            System.out.println("\nDigite o novo tipo do usuario (ou pressione ENTER para manter o tipo atual): " + editar.getTipoUsuario());
            System.out.println("Escolha um número -> 1 - Noivo(a) | 2 - Cerimonialista | 3 - Administrador | 4 - Convidado");
            String tipo = s.nextLine();
            if(!tipo.isEmpty()) {
                int num = Integer.parseInt(tipo);
                editar.setTipoUsuario(num);
            }

            System.out.println("\nDigite o novo CPF (ou pressione ENTER para manter o CPF atual): " + editar.getCpf());
            System.out.println("\nDigite desta forma-> 000.000.000-00");
            String cpfNovo = s.nextLine();
            if(!cpfNovo.isEmpty()) {
                editar.setCpf(cpfNovo);
            }

            pessoaDAO.atualizarPessoa(editar);
            System.out.println("\nPessoa alterado com sucesso, alteracoes: \n\n");
            System.out.println(editar.toString());
        } else {
            System.out.println("\nPessoa nao encontrada para alterar!");
        }
    }

    private void menuRelatorios() {
        int op;
        String reportPath; // Caminho do relatório
        do {
            op = gui.opRelatorios();
            switch (op) {
                case 1:
                    System.out.println("\nDe qual evento deseja extrair o relatorio? ");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nInforme o [ID]: ");
                    long idEventoDoRecado = Long.parseLong(s.nextLine());
                    Evento eventoDoRecado = eventoDAO.buscarPorId(idEventoDoRecado);
                    reportPath = "reports/RelatorioRecadosRecebidos.pdf";

                    if (eventoDoRecado != null) {
                        MuralRecados[] recados = muralRecadosDAO.buscarTodosPorEvento(eventoDoRecado);
                        relatorio.recadosRecebidosPDF(recados, eventoDoRecado.getNomeDoEvento(), reportPath);
                    }
                    break;

                case 2:
                    System.out.println("\nDe qual evento é o convite? ");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nInforme o [ID]: ");
                    long idEventoDoConvite = Long.parseLong(s.nextLine());
                    Evento eventoDoConvite = eventoDAO.buscarPorId(idEventoDoConvite);
                    System.out.println("\nLista de convidados cadastrados no sistema: ");
                    convidadoIndividualDAO.exibirConvidadosPorEvento(idEventoDoConvite);
                    System.out.println("\nDigite o [ID] do convidado que deseja gerar o convite: ");
                    long id = Long.parseLong(s.nextLine());
                    ConvidadoIndividual convite = convidadoIndividualDAO.buscarPorId(id);
                    reportPath = "reports/ConviteDeCasamento.pdf";
                    relatorio.conviteIndividualPDF(convite, eventoDoConvite, reportPath);
                    break;

                case 3:
                    System.out.println("\nDe qual evento é o convite? ");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nInforme o [ID]: ");
                    long idEventoDoConviteFamilia = Long.parseLong(s.nextLine());
                    Evento eventoDoConviteFalimia = eventoDAO.buscarPorId(idEventoDoConviteFamilia);
                    System.out.println("\nLista das familias cadastradas no sistema: ");
                    convidadoFamiliaDAO.exibirFamiliasPorEvento(idEventoDoConviteFamilia);
                    System.out.println("\nDigite o [ID] da familia que deseja gerar o convite: ");
                    long idFamilia = Long.parseLong(s.nextLine());
                    ConvidadoFamilia conviteFamilia = convidadoFamiliaDAO.buscarPorId(idFamilia);
                    reportPath = "reports/ConviteDeCasamentoParaFamilia.pdf";
                    relatorio.conviteIndividualFamiliaPDF(conviteFamilia, eventoDoConviteFalimia, reportPath);
                    break;

                case 4:
                    Pagamento[] pagamentos = pagamentoDAO.buscarTodos();
                    reportPath = "reports/PagamentosDosNoivos.pdf";
                    relatorio.pagamentosRealizadosPDF(pagamentos, reportPath);
                    break;

                case 5:
                    System.out.println("\nDe qual evento deseja extrair o relatorio de convidados? ");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nInforme o [ID]: ");
                    long idEventoDoRecadoConv = Long.parseLong(s.nextLine());
                    Evento eventoDoRecadoConv = eventoDAO.buscarPorId(idEventoDoRecadoConv);

                    if (eventoDoRecadoConv != null) {
                        ConvidadoIndividual[] convidados = convidadoIndividualDAO.buscarTodos(eventoDoRecadoConv);
                        reportPath = "reports/ListaDeConvidados.pdf";
                        relatorio.listaConvidadosPDF(convidados, reportPath);
                    }
                    break;

                case 6:
                    System.out.println("\nDe qual evento deseja extrair o relatorio de convidados confirmados? ");
                    eventoDAO.exibirListaEventosSimples();
                    System.out.println("\nInforme o [ID]: ");
                    long idEventoDoRecadoConvConf = Long.parseLong(s.nextLine());
                    Evento eventoDoRecadoConvConf = eventoDAO.buscarPorId(idEventoDoRecadoConvConf);

                    if (eventoDoRecadoConvConf != null) {
                        ConvidadoIndividual[] convidadosConfirmados = convidadoIndividualDAO.buscarTodosConfirmados(eventoDoRecadoConvConf);
                        reportPath = "reports/ListaDeConvidadosConfirmados.pdf";
                        relatorio.listaConvidadosConfirmadosPDF(convidadosConfirmados, reportPath);
                    }
                    break;

                case 0:
                    System.out.println("\nSaindo do modulo de relatorios!");
                    break;
            }
        } while (op != 0);
    }
}
