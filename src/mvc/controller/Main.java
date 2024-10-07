package mvc.controller;

import mvc.dao.EventoDAOMemoria;
import mvc.dao.FornecedorDAOMemoria;
import mvc.dao.PessoaDAOMemoria;
import mvc.model.Evento;
import mvc.model.Fornecedor;
import mvc.model.Pessoa;
import mvc.model.Util;

//Importações das Views
import mvc.view.GUI;

import java.util.Scanner;

public class Main {
    GUI gui = new GUI();
    private PessoaDAOMemoria pessoaDAO;
    private FornecedorDAOMemoria fornecedorDAO;
    private EventoDAOMemoria eventoDAO;
    Scanner s = new Scanner(System.in);

    public Main() {
        pessoaDAO = new PessoaDAOMemoria();
        fornecedorDAO = new FornecedorDAOMemoria();
        eventoDAO = new EventoDAOMemoria(pessoaDAO);

        int opcao = 0;

        while(opcao != 3) {
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
                case 3:
                    System.out.println("\n Volte sempre!!");
                    break;
                default:
                    System.out.println("\n Digite um numero valido!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public void menuPrincipal() {
        int opcaoPrincipal = 20;

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
                        //menuConvites();
                        break;
                    case 6:
                        //menuPresentes();
                        break;
                    case 7:
                        //menuRecados();
                        break;
                    case 8:
                        //menuPagamentos();
                        break;
                    case 9:
                        //menuRelatorios();
                        break;
                    case 0:
                        System.out.println("Saindo do menu principal!");
                        gui.menuBoasVindas();
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }
            }
        } else if(Util.getPessoaLogada().getTipoUsuario() == 4) {
            while (opcaoPrincipal != 0) {
                opcaoPrincipal = gui.menuConvidado();
                switch (opcaoPrincipal) {
                    case 1:
                        System.out.println(Util.getPessoaLogada().perfil());
                        break;
                    case 2:
                        //menuPresentes()
                        break;
                    case 3:
                        //menuRecados()
                        break;
                    case 4:
                        //menuConvidado()
                        break;
                    case 0:
                        System.out.println("0 - Sair");
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
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

                            System.out.println("Pessoa alterada com sucesso, alterações:\n");
                            System.out.println(editar.toString());
                        } else {
                            System.out.println("Não foi possível alterar o seu perfil!");
                        }
                        break;

                    case 3:
                        menuPrincipal();
                        break;

                    case 0:
                        System.out.println("0 - Sair");
                        break;

                    default:
                        System.out.println("Digite uma opcao valida");
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
                        System.out.println("Pessoa nao encontrada!");
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
                        System.out.println(" Nova data de nascimento: " + novoNascimento);
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
                        System.out.println("Novo email: " + novaSenha);
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
                    break;
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
                            fornecedorEditar.setValorAPagar(Long.parseLong(valorAPagar));
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

                        if(!semEditar.equals(fornecedorEditar)) {
                            System.out.println("Fornecedor nao foi alterado!");
                        } else {
                            System.out.println("Fornecedor alterado com sucesso, alteracoes: ");
                            System.out.println(fornecedorEditar.toString());
                        }
                    }
                    break;
                case 5:
                    System.out.println("\nQual o ID do fornecedor que deseja deletar do sistema?\n");
                    fornecedorDAO.exibeFornecedoresSimples();
                    System.out.println("\nDigite o ID: ");
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
                    System.out.println("\nQual o ID do evento? ");
                    long eventoId = Long.parseLong(s.nextLine()); //Conferir se vai dar certo

                    Evento achouE = eventoDAO.buscarPorId(eventoId);
                    if (achouE != null) {
                        System.out.println("\nEvento encontrado com sucesso!\n");
                        System.out.println(achouE.toString());
                    } else {
                        System.out.println("\nEvento nao encontrado!\n");
                    }
                    break;
                case 3:
                    System.out.println("\nO total de eventos no sistema é    : ["+ Evento.totalEventos +"] ");
                    System.out.println("Esses são os eventos encontrados:\n");
                    eventoDAO.listarEventos();
                    break;
                case 4:
                    // Precisa terminar o convidado individual
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

            System.out.println("Pessoa alterado com sucesso, alteracoes: ");
            System.out.println(editar.toString());
        } else {
            System.out.println("Pessoa nao encontrada para alterar!");
        }
    }
}