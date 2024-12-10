package mvc.dao;

import mvc.model.Evento;
import mvc.model.Pessoa;
import mvc.model.Presentes;

import java.util.ArrayList;

public interface PresentesDAO {
    void adicionarPresente(Presentes presente);
    void adicionarPresenteAoEvento(long presenteId, long eventoId);
    Presentes buscarPorId(long id);
    void darPresente(long id, Pessoa pessoa);
    void darPresente(long id, String nomePessoa);
    void exibePresentesCadastrados();
    void exibeListaPresentesSimples();
    boolean exibeListaPresentesPorEvento(long eventoId);
    void atualizarPresente(long id, Presentes presente);
    void atualizarPresenteInserindoPessoa(long id, Presentes presenteAtualizado, Evento evento, Pessoa pessoa);
    void removerPresente(long id);
    void adicionarTodosPresentesAoEvento(long id);
}
