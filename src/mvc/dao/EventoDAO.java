package mvc.dao;

import mvc.model.Evento;

public interface EventoDAO {
    void criarEvento(Evento evento);
    Evento buscarPorId(long id);
    void atualizarEvento(long id, Evento eventoAtualizado);
    void removerEvento(long id);
    void listarEventos();
    void exibirListaEventosSimples();
    int contarTotalDeEventos();
}
