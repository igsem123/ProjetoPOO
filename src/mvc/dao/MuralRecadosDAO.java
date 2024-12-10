package mvc.dao;

import mvc.model.Evento;
import mvc.model.MuralRecados;
import mvc.model.Pessoa;

import java.util.ArrayList;

public interface MuralRecadosDAO {

    void criarRecado(MuralRecados recado);

    MuralRecados buscaRecadoPorPessoa(Pessoa quemEscreveu);

    MuralRecados buscarPorId(long id);

    ArrayList<MuralRecados> buscarTodosPorEvento(Evento evento);
    
    void exibeListaDeRecados();

    void exibeListaDeRecadosPorEvento(Evento evento);

    void editaRecado(MuralRecados recadoAtualizado);

    void removeRecado(MuralRecados recadoRemover);
}
