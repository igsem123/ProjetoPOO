package mvc.dao;

import mvc.model.Pessoa;
import mvc.model.Presentes;

public interface PresentesDAO {
    void adicionarPresente(Presentes presente);
    Presentes buscarPorId(long id);
    void darPresente(long id, Pessoa pessoa);
    void exibeListaPresentesSimples();
    void atualizarPresente(long id, Presentes presente);
    void removerPresente(long id);
}
