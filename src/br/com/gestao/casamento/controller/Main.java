package br.com.gestao.casamento.controller;

import br.com.gestao.casamento.dao.FornecedorDAOMemoria;
import br.com.gestao.casamento.dao.PessoaDAOMemoria;
import br.com.gestao.casamento.model.Fornecedor;
import br.com.gestao.casamento.model.Pessoa;
import br.com.gestao.casamento.model.Util;

//Importações das Views
import br.com.gestao.casamento.view.GUI;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    GUI gui = new GUI();
    PessoaDAOMemoria pessoaDAO = new PessoaDAOMemoria();
    FornecedorDAOMemoria fornecedorDAO = new FornecedorDAOMemoria();
    Scanner s = new Scanner(System.in);

    public Main() {
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
                        String senha = this.s.nextLine().trim();;
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
        };
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
                        //gui.menuEvento();
                        break;
                    case 4:
                        gui.opFornecedor();
                        break;
                    case 5:
                        //gui.menuConvites();
                        break;
                    case 6:
                        //gui.menuPresentes();
                        break;
                    case 7:
                        //gui.menuRecados();
                        break;
                    case 8:
                        //gui.menuPagamentos();
                        break;
                    case 9:
                        //gui.menuRelatorios();
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

                        break;
                    case 3:

                        break;
                    case 4:
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
                        Util.getPessoaLogada().perfil();
                        break;
                    case 2:
                        atualizarPessoa(Util.getPessoaLogada().getCpf());
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
        int op = 10;
        do {
            op = gui.opPessoa();
            switch (op) {
                case 1:
                    Pessoa p = gui.cadastrarPessoa();

                    boolean pessoaInserida = pessoaDAO.criarPessoa(p);
                    if (pessoaInserida) {
                        System.out.println("\n Pessoa inserida com sucesso!");
                    } else {
                        System.out.println("\n Pessoa nao inserida!");
                    }
                    break;

                case 2:
                    pessoaDAO.listarPessoas();
                    break;

                case 3:
                    System.out.println("\n Digite o CPF da pessoa: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpf = s.nextLine();
                    Pessoa achou = pessoaDAO.buscaPessoa(cpf);
                    if(achou != null) {
                        System.out.println(achou.toString());
                    } else {
                        System.out.println("Pessoa nao encontrada!");
                    }
                    break;

                case 4:
                    System.out.println("\n Digite o CPF da pessoa que deseja alterar: ");
                    String cpfDaPessoa = s.nextLine();
                    atualizarPessoa(cpfDaPessoa);
                    break;

                case 5:
                    System.out.println("\n Digite o CPF da pessoa que deseja excluir: ");
                    System.out.println("\n Ex: 000.000.000-00 ");
                    String cpfExclusao = s.nextLine();

                    if (pessoaDAO.deletarPessoa(cpfExclusao)) {
                        System.out.println("\n Pessoa excluida!");
                    } else {
                        System.out.println("\n Pessoa nao excluida!");
                    }
                    break;

                case 6:
                    System.out.println("\n Digite o CPF da pessoa que deseja alterar o nome: ");
                    String editaNome = s.nextLine();
                    System.out.println("\n Qual o novo nome? ");
                    String novoNome = s.nextLine();

                    if (pessoaDAO.alterarNome(editaNome, novoNome)) {
                        System.out.println("\n Pessoa alterada!");
                        novoNome = pessoaDAO.buscaPessoa(editaNome).getNome();
                        System.out.println(" Novo nome: " + novoNome);
                    } else {
                        System.out.println("\n Pessoa nao alterada!");
                    }
                    break;

                case 7:
                    System.out.println("\n Digite o CPF da pessoa que deseja alterar o sexo: ");
                    String editaSexo = s.nextLine();
                    System.out.println("\n Qual o novo sexo? ");
                    String novoSexo = s.nextLine();

                    if (pessoaDAO.alterarSexo(editaSexo, novoSexo)) {
                        System.out.println("\n Pessoa alterada!");
                    } else {
                        System.out.println("\n Pessoa nao alterada!");
                    }
                    break;

                case 8:
                    System.out.println("\n Digite o CPF da pessoa que deseja alterar a data de nascimento: ");
                    String editaNascimento = s.nextLine();
                    System.out.println("\n Qual a nova data de nascimento? ");
                    String novoNascimento = s.nextLine();

                    if (pessoaDAO.alterarNascimento(editaNascimento, novoNascimento)) {
                        System.out.println("\n Pessoa alterada!");
                    } else {
                        System.out.println("\n Pessoa nao alterada!");
                    }
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 0:
                    System.out.println("Saindo do modulo Pessoa!");
                    break;
                default:
                    System.out.println("Digite um numero valido!");
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
                    fornecedorDAO.buscaFornecedor(cnpj);
                    break;
                case 2:
                    Fornecedor nF = new Fornecedor();
                    System.out.println("\nQual o nome da empresa? ");
                    String nome = s.nextLine();
                    nF.setNome(nome);

                    System.out.println("\nQual o telefone da empresa? ");
                    String telefone = s.nextLine();
                    nF.setTelefone(telefone);

                    System.out.println("\nQual o email da empresa? ");
                    String email = s.nextLine();
                    nF.setEmail(email);

                    System.out.println("\nQual o valor em débito com a empresa?");
                    long valor = Long.parseLong(s.nextLine());
                    nF.setValorAPagar(valor);

                    System.out.println("\nEm quantas parcelas pagará?");
                    System.out.println("\n -> Digite 0 se o pagamento for à vista!");
                    int parcelas = Integer.parseInt(s.nextLine());
                    nF.setParcelas(parcelas);

                    String estado = "Em débito!";
                    nF.setEstado(estado);

                    // Armazena o Fornecedor no DAO e exibe mensagem de sucesso
                    fornecedorDAO.criarFornecedor(nF);
                    JOptionPane.showMessageDialog(null, "Fornecedor criado com sucesso!");

                    break;
                case 3:
                    fornecedorDAO.listarFornecedores();
                    break;
                case 4:
                    Fornecedor fornecedorEditar = new Fornecedor();
                    System.out.println("\nPara atualizar, primeiro digite o CNPJ do fornecedor que deseja atualizar: ");
                    String fornecedorCNPJ = s.nextLine();

                    if(fornecedorDAO.buscaFornecedor(fornecedorCNPJ) != null) {
                        // Armazenando o fornecedor sem editar
                        Fornecedor semEditar = fornecedorDAO.buscaFornecedor(fornecedorCNPJ);
                        fornecedorEditar = fornecedorDAO.buscaFornecedor(fornecedorCNPJ);

                        System.out.println("\n Digite o novo nome da empresa (ou pressione ENTER para manter o nome atual): " + fornecedorEditar.getNome());
                        String novoNome = s.nextLine();
                        if(!novoNome.isEmpty()) {
                            fornecedorEditar.setNome(novoNome);
                        }

                        System.out.println("\n Digite o novo CNPJ (ou pressione ENTER para manter o CNPJ atual): " + fornecedorEditar.getCNPJ());
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
                        long valorAPagar = this.s.nextLong();
                        if(!(valorAPagar == 0)) {
                            fornecedorEditar.setValorAPagar(valorAPagar);
                        }

                        System.out.println("\n Digite a nova quantidade de parcelas (ou pressione ENTER para manter a parcela atual): " + fornecedorEditar.getParcelas());
                        int novaParcela = Integer.parseInt(s.nextLine());
                        if(!(novaParcela == 0)) {
                            fornecedorEditar.setParcelas(novaParcela);
                        }

                        System.out.println("\n Digite o estado atual da dívida (ou pressione ENTER para manter o estado atual): " + fornecedorEditar.getEstado());
                        String novoEstado = s.nextLine();
                        if(!novoEstado.isEmpty()) {
                            fornecedorEditar.setEstado(novoEstado);
                        }

                        if(semEditar.equals(fornecedorEditar)) {
                            System.out.println("Fornecedor nao foi alterado!");
                        } else {
                            System.out.println("Fornecedor alterado com sucesso, alteracoes: ");
                            fornecedorEditar.toString();
                        }
                    } else {
                        System.out.println("Fornecedor nao encontrado para alterar!");
                    }
                    break;
                case 5:
                    break;

            }
        } while (op != 0);
    }

    private void atualizarPessoa(String cpfDaPessoa) {
        Pessoa editar = pessoaDAO.buscaPessoa(cpfDaPessoa); // Retorna o objeto Pessoa e armazena
        Pessoa semEditar = pessoaDAO.buscaPessoa(cpfDaPessoa);

        if(editar != null) {
            System.out.println("\n Digite o novo nome (ou pressione ENTER para manter o nome atual): " + editar.getNome());
            String nome = s.nextLine();
            if(!nome.isEmpty()) {
                editar.setNome(nome);
            }

            System.out.println("\n Digite o novo sexo (ou pressione ENTER para manter o sexo atual): " + editar.getSexo());
            String sexo = s.nextLine();
            if(!sexo.isEmpty()) {
                editar.setSexo(sexo);
            }

            System.out.println("\n Digite a nova Data de Nascimento (ou pressione ENTER para manter a data atual): " + editar.getDataNascimento());
            System.out.println("\n Digite desta forma-> dd/MM/yyyy");
            String nascimento = s.nextLine();
            if(!nascimento.isEmpty()) {
                editar.setDataNascimento(nascimento);
            }

            System.out.println("\n Digite o novo email (ou pressione ENTER para manter o email atual): " + editar.getEmail());
            String login = s.nextLine();
            if(!login.isEmpty()) {
                editar.setEmail(login);
            }

            System.out.println("\n Digite a nova senha (ou pressione ENTER para manter a senha atual): " + editar.getSenha());
            String senha = s.nextLine();
            if(!senha.isEmpty()) {
                editar.setSenha(senha);
            }

            System.out.println("\n Digite o novo tipo do usuario (ou pressione ENTER para manter o tipo atual): " + editar.getTipoUsuario());
            System.out.println("\n Digite um numero-> 1- Aluno | 2- Professor | 3- Administrador");
            String tipo = s.nextLine();
            if(!tipo.isEmpty()) {
                int num = Integer.parseInt(tipo);
                editar.setTipoUsuario(num);
            }

            System.out.println("\n Digite o novo CPF (ou pressione ENTER para manter o CPF atual): " + editar.getCpf());
            System.out.println("\n Digite desta forma-> 000.000.000-00");
            String cpfNovo = s.nextLine();
            if(!cpfNovo.isEmpty()) {
                editar.setCpf(cpfNovo);
            }

            System.out.println("Pessoa alterado com sucesso!");
        } else {
            System.out.println("Pessoa nao encontrada para alterar!");
        }
    }
}
