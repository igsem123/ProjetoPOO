package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;

public interface ConvidadoFamiliaDAO {
    void criarFamilia(ConvidadoFamilia familia);
    ConvidadoFamilia buscarPorId(long id);
    void atualizarFamilia(long id, ConvidadoFamilia familiaAtualizada);
    void removerFamilia(long id);
    void exibirFamilias();
    void exibirFamiliasPorEvento(Long idEvento);
    void listarFamilias();
    ConvidadoFamilia[] getFamilias();
    void confirmarPresenca(String acessoFamilia, ConvidadoIndividual[] convidadosIndividuais, ConvidadoFamilia[] convidadosFamilia);
}
