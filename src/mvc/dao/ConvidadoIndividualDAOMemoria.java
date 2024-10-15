package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.model.Pessoa;

import java.time.LocalDateTime;

public class ConvidadoIndividualDAOMemoria implements ConvidadoIndividualDAO {
    public ConvidadoIndividual[] convidados;
    private int totalConvidados = 0;

    public ConvidadoIndividualDAOMemoria(PessoaDAO pessoaDAO, ConvidadoFamiliaDAO convidadoFamiliaDAO, int capacidade) {
        convidados = new ConvidadoIndividual[capacidade];
        this.totalConvidados = 0;

        // Armazenando os convidados individuais de exemplo
        Pessoa convidado1 = pessoaDAO.buscaPorId(11L);
        ConvidadoIndividual cI1 = new ConvidadoIndividual(convidado1, convidadoFamiliaDAO.buscarPorId(0L), "Mãe");
        this.criarConvidado(cI1);

        Pessoa convidado2 = pessoaDAO.buscaPorId(12L);
        ConvidadoIndividual cI2 = new ConvidadoIndividual(convidado2, convidadoFamiliaDAO.buscarPorId(0L), "Irmã");
        this.criarConvidado(cI2);
    }

    public ConvidadoIndividual[] getConvidados() {
        return convidados;
    }

    // Criar
    public void criarConvidado(ConvidadoIndividual convidado) {
        if (totalConvidados < convidados.length) {
            convidados[totalConvidados++] = convidado;
            System.out.println("\nConvidado criado com sucesso: \n\n" + convidado.toString());
        } else {
            System.out.println("\nCapacidade máxima de convidados atingida.");
        }
    }

    // Confirmar presença
    public void confirmarConvidado(ConvidadoIndividual convidado) {
        for (ConvidadoIndividual convidadoConfirmar : convidados) {
            if (convidadoConfirmar != null && convidadoConfirmar.equals(convidado)) {
                convidado.setConfirmacao(true);
                convidado.isConfirmacao();
                return;
            }
        }
        System.out.println("\nConvidado não confirmado!");
    }

    // Confirmar ou desconfirmar presença pela pessoa que possui o convite
    public void confirmarPresencaPelaPessoa(ConvidadoIndividual convidado) {
        for (ConvidadoIndividual convidadoConfirmar : convidados) {
            if (convidadoConfirmar != null && convidadoConfirmar.getPessoa().equals(convidado.getPessoa())) {
                // Alterna o estado de confirmação
                boolean novoEstado = !convidadoConfirmar.getConfirmacaoPrimitivo();
                convidadoConfirmar.setConfirmacao(novoEstado);

                // Exibe mensagem de acordo com o novo estado
                convidadoConfirmar.isConfirmacao();
                return;
            }
        }
    }

    // Buscar por ID
    public ConvidadoIndividual buscarPorId(long id) {
        for (ConvidadoIndividual convidado : convidados) {
            if (convidado != null && convidado.getId() == id) {
                return convidado;
            }
        }
        return null;
    }

    // Atualizar
    public void atualizarConvidado(long id, ConvidadoIndividual convidadoAtualizado) {
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] != null && convidados[i].getId() == id) {
                convidados[i] = convidadoAtualizado;
                convidados[i].setDataModificacao(LocalDateTime.now());
                System.out.println("\nConvidado atualizado.");
                return;
            }
        }
        System.out.println("\nConvidado não encontrado.");
    }

    // Remover
    public void removerConvidado(long id) {
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] != null && convidados[i].getId() == id) {
                convidados[i] = null;
                reorganizarArray(i);
                System.out.println("\nConvidado removido.");
                return;
            }
        }
        System.out.println("\nConvidado não encontrado.");
    }

    private void reorganizarArray(int posicaoRemovida) { // Metódo para reorganizar o array após remoção
        for (int i = posicaoRemovida; i < convidados.length - 1; i++) {
            convidados[i] = convidados[i + 1];
        }
        convidados[convidados.length - 1] = null;
        totalConvidados--;
    }

    // Listar todos
    public void listarConvidados() {
        for (ConvidadoIndividual convidado : convidados) {
            if (convidado != null) {
                System.out.println(convidado.toString());
            }
        }
    }

    // Exibir lista simples
    public void exibirConvidadosSimples() {
        for (ConvidadoIndividual convidado : convidados) {
            if (convidado != null) {
                System.out.println("ID: [" + convidado.getId() + "] - Nome: " + convidado.getPessoa().getNome() + " - Família: " + convidado.getFamilia().getNomeFamilia());
            }
        }
    }
}
