package mvc.dao;

import mvc.model.ConvidadoIndividual;
import mvc.model.Evento;

public interface ConvidadoIndividualDAO {
    void criarConvidado(ConvidadoIndividual convidado);
    ConvidadoIndividual buscarPorId(long id);
    ConvidadoIndividual[] buscarTodos(Evento eventoDoRecadoConv);
    ConvidadoIndividual[] buscarTodosConfirmados(Evento eventoDoRecadoConv);
    void atualizarConvidado(long id, ConvidadoIndividual convidadoAtualizado);
    void removerConvidado(long id);
    void listarConvidados();
    void exibirConvidadosSimples();
    void confirmarConvidado(ConvidadoIndividual convidado);
    void confirmarPresencaPelaPessoa(ConvidadoIndividual convidado);
    ConvidadoIndividual[] getConvidados();
}
