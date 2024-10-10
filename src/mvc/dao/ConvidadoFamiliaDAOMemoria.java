package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;

import java.time.LocalDateTime;

public class ConvidadoFamiliaDAOMemoria implements ConvidadoFamiliaDAO {
    private ConvidadoFamilia[] familias;
    private int contador = 0;

    public ConvidadoFamiliaDAOMemoria(int capacidade) {
        familias = new ConvidadoFamilia[capacidade];
    }

    // Criar
    public void criarFamilia(ConvidadoFamilia familia) {
        if (contador < familias.length) {
            familias[contador++] = familia;
            System.out.println("Família criada com sucesso.");
        } else {
            System.out.println("Capacidade máxima atingida.");
        }
    }

    // Buscar por ID
    public ConvidadoFamilia buscarPorId(long id) {
        for (ConvidadoFamilia familia : familias) {
            if (familia != null && familia.getId() == id) {
                return familia;
            }
        }
        return null;
    }

    // Atualizar
    public void atualizarFamilia(long id, ConvidadoFamilia familiaAtualizada) {
        for (int i = 0; i < familias.length; i++) {
            if (familias[i] != null && familias[i].getId() == id) {
                familias[i] = familiaAtualizada;
                familias[i].setDataModificacao();
                System.out.println("Família atualizada.");
                return;
            }
        }
        System.out.println("Família não encontrada.");
    }

    // Remover
    public void removerFamilia(long id) {
        for (int i = 0; i < familias.length; i++) {
            if (familias[i] != null && familias[i].getId() == id) {
                familias[i] = null; // Remove a família
                System.out.println("Família removida.");
                return;
            }
        }
        System.out.println("Família não encontrada.");
    }

    // Listar todos
    public void listarFamilias() {
        for (ConvidadoFamilia familia : familias) {
            if (familia != null) {
                System.out.println(familia.toString());
            }
        }
    }
}
