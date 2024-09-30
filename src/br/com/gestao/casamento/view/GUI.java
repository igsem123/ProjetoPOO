package br.com.gestao.casamento.view;

import br.com.gestao.casamento.dao.PessoaDAO;
import br.com.gestao.casamento.dao.PessoaDAOMemoria;
import br.com.gestao.casamento.model.Pessoa;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAOMemoria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Criar Pessoa\n2. Buscar Pessoa por ID\n3. Atualizar Pessoa\n4. Deletar Pessoa\n5. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do Scanner

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Data de nascimento (YYYY-MM-DD): ");
                    LocalDate nascimento = LocalDate.parse(scanner.nextLine());

                    System.out.print("Digite seu CPF (XXX.XXX.XXX-XX): ");
                    String cpf = scanner.nextLine();

                    System.out.print("Telefone (+55 34 X XXXX-XXXX): ");
                    String telefone = scanner.nextLine();

                    Pessoa novaPessoa = new Pessoa(nome, nascimento, cpf, telefone, LocalDate.now(), LocalDate.now());
                    pessoaDAO.criarPessoa(novaPessoa);
                    break;

                case 2:
                    System.out.print("ID da Pessoa: ");
                    int idBusca = scanner.nextInt();
                    Pessoa pessoaBuscada = pessoaDAO.buscarPessoaPorId(idBusca);
                    if (pessoaBuscada != null) {
                        System.out.println(pessoaBuscada);
                    }
                    break;

                case 3:
                    System.out.print("ID da Pessoa: ");
                    int idAtualiza = scanner.nextInt();
                    scanner.nextLine();  // Limpa o buffer
                    Pessoa pessoaAtualizar = pessoaDAO.buscarPessoaPorId(idAtualiza);
                    if (pessoaAtualizar != null) {
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        pessoaAtualizar.setNome(novoNome);
                        pessoaDAO.atualizarPessoa(pessoaAtualizar);
                    }
                    break;

                case 4:
                    System.out.print("ID da Pessoa: ");
                    int idDeletar = scanner.nextInt();
                    pessoaDAO.deletarPessoa(idDeletar);
                    break;

                case 5:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
