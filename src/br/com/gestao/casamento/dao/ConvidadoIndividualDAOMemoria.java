package br.com.gestao.casamento.dao;

import br.com.gestao.casamento.model.ConvidadoIndividual;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConvidadoIndividualDAOMemoria implements ConvidadoIndividualDAO {
    private ConvidadoIndividual[] convidados;
    private int totalConvidados = 0;

    public ConvidadoIndividualDAOMemoria(int capacidade) {
        convidados = new ConvidadoIndividual[capacidade];
    }

    // Criar
    public void criarConvidado(ConvidadoIndividual convidado) {
        if (totalConvidados < convidados.length) {
            convidados[totalConvidados++] = convidado;
            System.out.println("Convidado criado com sucesso.");
        } else {
            System.out.println("Capacidade máxima atingida.");
        }
    }

    // Buscar por ID
    public ConvidadoIndividual buscarPorId(long id) {
        for (ConvidadoIndividual convidado : convidados) {
            if (convidado != null && convidado.getId() == id) {
                return convidado;
            }
        }
        return null;
    }

    // Atualizar
    public void atualizarConvidado(long id, ConvidadoIndividual convidadoAtualizado) {
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] != null && convidados[i].getId() == id) {
                convidados[i] = convidadoAtualizado;
                convidados[i].setDataModificacao(LocalDateTime.now());
                System.out.println("Convidado atualizado.");
                return;
            }
        }
        System.out.println("Convidado não encontrado.");
    }

    // Remover
    public void removerConvidado(long id) {
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] != null && convidados[i].getId() == id) {
                convidados[i] = null; // Remove o convidado
                System.out.println("Convidado removido.");
                return;
            }
        }
        System.out.println("Convidado não encontrado.");
    }

    // Listar todos
    public void listarConvidados() {
        for (ConvidadoIndividual convidado : convidados) {
            if (convidado != null) {
                System.out.println(convidado.toString());
            }
        }
    }

    // Exibir lista simples
    public void exibirConvidadosSimples() {
        for (ConvidadoIndividual convidado : convidados) {
            if (convidado != null) {
                System.out.println(convidado.getId() + " - " + convidado.getPessoa().getNome() + " - " + convidado.getFamilia() + " - " + convidado.getDataModificacao());
            }
        }
    }
}
