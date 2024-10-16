package mvc.dao;

import mvc.model.ConvidadoIndividual;

public interface ConvidadoIndividualDAO {
    void criarConvidado(ConvidadoIndividual convidado);
    ConvidadoIndividual buscarPorId(long id);
    void atualizarConvidado(long id, ConvidadoIndividual convidadoAtualizado);
    void removerConvidado(long id);
    void listarConvidados();
    void exibirConvidadosSimples();
    void confirmarConvidado(ConvidadoIndividual convidado);
    void confirmarPresencaPelaPessoa(ConvidadoIndividual convidado);
    ConvidadoIndividual[] getConvidados();
}
