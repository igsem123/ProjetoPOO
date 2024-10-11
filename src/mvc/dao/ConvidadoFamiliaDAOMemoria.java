package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;

import java.time.LocalDateTime;

public class ConvidadoFamiliaDAOMemoria implements ConvidadoFamiliaDAO {
    private ConvidadoFamilia[] familias;
    private int contador = 0;


    public ConvidadoFamiliaDAOMemoria(int capacidade, PessoaDAO pessoaDAO, EventoDAO eventoDAO) {
        familias = new ConvidadoFamilia[capacidade];
        ConvidadoFamilia convidadoFamilia = new ConvidadoFamilia("Reis", pessoaDAO.buscaPorId(0L).getNome(), pessoaDAO.buscaPorId(1L).getNome(), eventoDAO.buscarPorId(0L).getDataEvento());
        this.criarFamilia(convidadoFamilia);
    }

    public ConvidadoFamilia[] getFamilias() {
        return familias;
    }

    // Criar
    public void criarFamilia(ConvidadoFamilia familia) {
        if (contador < familias.length) {
            familias[contador++] = familia;
            System.out.println("\nConvite família criada com sucesso!\n\n" + familia.toString());
        } else {
            System.out.println("\nCapacidade máxima atingida.");
        }
    }

    // Buscar por ID
    public ConvidadoFamilia buscarPorId(long id) {
        for (ConvidadoFamilia familia : familias) {
            if (familia != null && familia.getId() == id) {
                return familia;
            }
        }
        return null;
    }

    // Buscar convites individuais para confirmar pela familia
    public void confirmarPresenca(String acessoFamilia, ConvidadoIndividual[] convidadosIndividuais, ConvidadoFamilia[] convidadosFamilia) {
        // Primeiro vou buscar se o acesso fornecido existe
        ConvidadoFamilia familia = null;
        int totalDeConvidadosInd = 0;

        for (ConvidadoFamilia convidadoFamilia : familias) {
            if (convidadoFamilia != null && convidadoFamilia.getAcesso().equals(acessoFamilia)) {
                familia = convidadoFamilia;
                break;
            }
        }

        if (familia == null) {
            System.out.println("\nAcesso inválido ou família não encontrada!");
            return;
        }

        // Agora que tenho a familia, preciso percorrer o vetor de convidados individuais e confirmar aqueles que estão atrelados à familia do acesso digitado
        for (ConvidadoIndividual convidado : convidadosIndividuais) {
            if (convidado != null && convidado.getFamilia() != null && convidado.getFamilia().getId() == familia.getId()) {
                convidado.setConfirmacao(true); // Confirmando a presença de cada convidado
                convidado.setDataModificacao(LocalDateTime.now());
                System.out.println("\nConvidado(a): " + convidado.getPessoa().getNome() + " - " + convidado.getConfirmacao());
                totalDeConvidadosInd++;
            }
        }

        if (totalDeConvidadosInd == 0) {
            System.out.println("\nNão há nenhum convidado individual cadastrado nessa família para confirmar.");

            //TODO se der tempo vou implementar a chamada para cadastrar convidados individuais
        }
    }


    // Atualizar
    public void atualizarFamilia(long id, ConvidadoFamilia familiaAtualizada) {
        for (int i = 0; i < familias.length; i++) {
            if (familias[i] != null && familias[i].getId() == id) {
                familias[i] = familiaAtualizada;
                familias[i].setDataModificacao();
                System.out.println("\nConvite família alterada com sucesso! Nome atual: " + familiaAtualizada.getNomeFamilia());
                return;
            }
        }
        System.out.println("\nConvite família não encontrada para alterar seus dados.");
    }

    // Remover
    public void removerFamilia(long id) {
        for (int i = 0; i < familias.length; i++) {
            if (familias[i] != null && familias[i].getId() == id) {
                familias[i] = null; // Remove a família
                System.out.println("\nConvite família removida.");
                return;
            }
        }
        System.out.println("\nConvite família não encontrada.");
    }

    // Mostrar todos os convites família
    public void exibirFamilias() {
        for (ConvidadoFamilia familia : familias) {
            if (familia != null) {
                System.out.println(familia.toString());
            }
        }
    }

    // Listar todos os convites família de maneira simples
    public void listarFamilias() {
        for (ConvidadoFamilia familia : familias) {
            if (familia != null) {
                System.out.println("\nID: [" + familia.getId() + "] - Nome da Família: " + familia.getNomeFamilia());
            }
        }
    }
}
