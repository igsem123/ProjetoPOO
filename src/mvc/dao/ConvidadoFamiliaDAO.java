package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;

import java.util.ArrayList;

public interface ConvidadoFamiliaDAO {
    void criarFamilia(ConvidadoFamilia familia);
    ConvidadoFamilia buscarPorId(long id);
    void atualizarFamilia(long id, ConvidadoFamilia familiaAtualizada);
    void removerFamilia(long id);
    void exibirFamiliasPorEvento(Long idEvento);
    void listarFamilias();
    ArrayList<ConvidadoFamilia> getFamilias();
    void confirmarPresenca(String acessoFamilia, ArrayList<ConvidadoIndividual> convidadosIndividuais, ArrayList<ConvidadoFamilia> convidadosFamilia);
}
