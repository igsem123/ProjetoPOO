package mvc.dao;

import mvc.model.ConvidadoFamilia;

public interface ConvidadoFamiliaDAO {
    void criarFamilia(ConvidadoFamilia familia);
    ConvidadoFamilia buscarPorId(long id);
    void atualizarFamilia(long id, ConvidadoFamilia familiaAtualizada);
    void removerFamilia(long id);
    void listarFamilias();

}
