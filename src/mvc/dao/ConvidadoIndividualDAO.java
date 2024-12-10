package mvc.dao;

import mvc.model.ConvidadoIndividual;
import mvc.model.Evento;

import java.util.ArrayList;

public interface ConvidadoIndividualDAO {
    void criarConvidado(ConvidadoIndividual convidado);
    ConvidadoIndividual buscarPorId(long id);
    ArrayList<ConvidadoIndividual> buscarTodos(Evento eventoDoRecadoConv);
    ArrayList<ConvidadoIndividual> buscarTodosConfirmados(Evento eventoDoRecadoConv);
    void atualizarConvidado(long id, ConvidadoIndividual convidadoAtualizado);
    void removerConvidado(long id);
    void listarConvidados();
    void exibirConvidadosSimples();
    void exibirConvidadosPorEvento(Long idEvento);
    void confirmarConvidado(ConvidadoIndividual convidado);
    void confirmarPresencaPelaPessoa(ConvidadoIndividual convidado);
    ArrayList<ConvidadoIndividual> getConvidados();
    void desconfirmarPresencaPelaPessoa(ConvidadoIndividual convidadoNaoConfirmado);
    ConvidadoIndividual buscarPorPessoaId(long id);
}
