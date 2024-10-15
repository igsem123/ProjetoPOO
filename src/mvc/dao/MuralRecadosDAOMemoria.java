package mvc.dao;

import mvc.model.*;

public class MuralRecadosDAOMemoria implements MuralRecadosDAO{
    private MuralRecados[] recadosCriados;
    private int indice;

    public MuralRecadosDAOMemoria(PessoaDAO pessoaDAO, EventoDAO eventoDAO, int capacidade) {
        this.indice = 0;
        this.recadosCriados = new MuralRecados[capacidade];

        // Recados de ex.:
        MuralRecados recado1 = new MuralRecados(
                "Que emoção participar desse momento tão especial! Desejo toda felicidade do mundo para vocês!",
                null,
                eventoDAO.buscarPorId(0L)
        );
        this.criarRecado(recado1);

        MuralRecados recado2 = new MuralRecados(
                "Parabéns Raphael e Lucas! Que essa nova fase seja repleta de amor e alegrias!",
                pessoaDAO.buscaPorId(11L),
                eventoDAO.buscarPorId(0L)
        );
        this.criarRecado(recado2);

        MuralRecados recado3 = new MuralRecados(
                "Estou muito feliz por vocês dois! Que Deus abençoe essa união!",
                pessoaDAO.buscaPorId(12L),
                eventoDAO.buscarPorId(0L)
        );
        this.criarRecado(recado3);

        MuralRecados recado4 = new MuralRecados(
                "Ana Paula e Carlos, desejo a vocês um casamento repleto de momentos lindos e inesquecíveis!",
                null,
                eventoDAO.buscarPorId(1L) // Ana Paula e Carlos Alberto
        );
        this.criarRecado(recado4);

        MuralRecados recado5 = new MuralRecados(
                "Que alegria ver vocês se casando! Que sejam sempre felizes e abençoados!",
                new Pessoa("Ricardo Barbosa", "Masculino", Util.formataData("14/04/1987"), "+55 34 9 3344-5566", "ricardo.barbosa@gmail.com", "ricardo123", 4, "654.321.987-00"),
                eventoDAO.buscarPorId(1L)
        );
        this.criarRecado(recado5);

        MuralRecados recado6 = new MuralRecados(
                "Parabéns Ana Paula e Carlos! Que esse seja o início de uma vida cheia de realizações!",
                new Pessoa("Camila Rocha", "Feminino", Util.formataData("10/10/1995"), "+55 34 9 4455-6677", "camila.rocha@gmail.com", "camila456", 4, "123.321.456-98"),
                eventoDAO.buscarPorId(1L)
        );
        this.criarRecado(recado6);

    }

    @Override
    public void criarRecado(MuralRecados recado) {
        if (indice < recadosCriados.length) {
            recadosCriados[indice] = recado;
            indice++;
            System.out.println("\nRecado registrado com sucesso: \n\n" + recado.toString());
        } else {
            System.out.println("\nCapacidade máxima de recados atingida!");
        }
    }

    // Buscar por ID
    public MuralRecados buscarPorId(long id) {
        for (MuralRecados recadoProcurado : recadosCriados) {
            if (recadoProcurado != null && recadoProcurado.getId() == id) {
                return recadoProcurado;
            }
        }
        return null;
    }

    @Override
    public MuralRecados buscaRecadoPorPessoa(Pessoa quemEscreveu) {
        for (MuralRecados recado : recadosCriados) {
            if (recado != null && recado.getPessoa() == quemEscreveu && recado.getPessoa().getTipoUsuario() == 4) {
                System.out.println("\nConvidado(a) que escreveu este recado: " + quemEscreveu.getNome());
                System.out.println("\n\n" + recado.toString());
                return recado;
            }
        }

        if (quemEscreveu.getTipoUsuario() != 4) {
            System.out.println("\nPessoa informada não é um convidado(a). Pessoa informada: " + quemEscreveu.getNome());
        } else {
            System.out.println("\nNão foi encontrado recado desta pessoa: \n" + quemEscreveu.getNome());
        }

        return null;
    }

    @Override
    public void exibeListaDeRecados() {
        for (MuralRecados recado : recadosCriados) {
            if (recado != null) {
                System.out.println(recado);
            }
        }
    }

    @Override
    public void exibeListaDeRecadosPorEvento(Evento evento) {
        for (MuralRecados recado : recadosCriados) {
            if (recado != null && recado.getNomeDoEvento().equals(evento.getNomeDoEvento())) {
                System.out.println(recado);
            }
        }
    }

    @Override
    public void editaRecado(MuralRecados recadoAtualizado) {
        for (MuralRecados recados : recadosCriados) {
            if (recados != null && recados.getId() == recadoAtualizado.getId()) {
                recadosCriados[indice] = recadoAtualizado;
                recadosCriados[indice].setDataModificacao();
                System.out.println("\nRecado editado com sucesso: \n\n" + recadoAtualizado.getComentario());
                return;
            }
        }

        System.out.println("\nRecado não encontrado para editar!");
    }

    @Override
    public void removeRecado(MuralRecados recadoRemover) {
        for (int i = 0; i < recadosCriados.length; i++) {
            if (recadosCriados[i] != null && recadosCriados[i].getId() == recadoRemover.getId())
            {
                recadosCriados[i] = null;
                reorganizarArray(i);
                System.out.println("\nRecado excluído com sucesso!");
                return;
            }
        }
        System.out.println("\nRecado não encontrado para remoção!");
    }

    private void reorganizarArray(int posicaoRemovida) {
        for (int i = posicaoRemovida; i < recadosCriados.length - 1; i++) {
            recadosCriados[i] = recadosCriados[i + 1];
        }
        recadosCriados[recadosCriados.length - 1] = null;
        indice--;
    }
}
