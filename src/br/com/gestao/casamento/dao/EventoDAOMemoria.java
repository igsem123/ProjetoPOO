package br.com.gestao.casamento.dao;

import br.com.gestao.casamento.model.Evento;
import br.com.gestao.casamento.model.Fornecedor;
import br.com.gestao.casamento.model.Util;

import java.time.LocalDateTime;

public class EventoDAOMemoria implements EventoDAO {
    private static final int TAMANHO_MAXIMO = 50;
    private final Evento[] eventos;
    private int totalEventos;
    PessoaDAOMemoria pessoaDAO;

    public EventoDAOMemoria() {
        this.eventos = new Evento[TAMANHO_MAXIMO];
        this.totalEventos = 0;

        // Armazenando eventos de exemplo
        Evento evento1 = new Evento(LocalDateTime.now(), pessoaDAO.buscaPorId(5L), "Igreja Central", "Cartório Central", pessoaDAO.buscaPorId(0L), pessoaDAO.buscaPorId(1L));
        Evento evento2 = new Evento(LocalDateTime.now().plusDays(30), pessoaDAO.buscaPorId(6L), "Igreja Nova", "Cartório Nova", pessoaDAO.buscaPorId(7L), pessoaDAO.buscaPorId(8L));

    }

    // Criar um novo evento
    public void criarEvento(Evento evento) {
        if (totalEventos < eventos.length) {
            eventos[totalEventos++] = evento;
            System.out.println("Evento criado com sucesso.");
        } else {
            System.out.println("Capacidade máxima de eventos atingida.");
        }
    }

    // Buscar evento por ID
    public Evento buscarPorId(long id) {
        for (Evento evento : eventos) {
            if (evento != null && evento.getId() == id) {
                return evento;
            }
        }
        return null;  // Retorna null se o evento não for encontrado
    }

    // Atualizar um evento existente
    public void atualizarEvento(long id, Evento eventoAtualizado) {
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null && eventos[i].getId() == id) {
                eventos[i] = eventoAtualizado;
                eventos[i].setDataModificacao(Util.getDia());  // Atualiza a data de modificação
                System.out.println("Evento atualizado.");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    // Remover evento por ID
    public void removerEvento(long id) {
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null && eventos[i].getId() == id) {
                eventos[i] = null;  // Remove o evento
                System.out.println("Evento removido.");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    // Listar todos os eventos
    public void listarEventos() {
        for (Evento evento : eventos) {
            if (evento != null) {
                System.out.println(evento.toString());
                System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
            }
        }
    }

    // Exibir lista de eventos simples
    public void exibirListaEventosSimples() {
        for (Evento evento : eventos) {
            if (evento != null) {
                System.out.println(evento.getId() + " - " + evento.getDataEvento() + " - " + evento.getIgreja() + " - " + evento.getPessoaNoivo1().getNome() + " - " + evento.getPessoaNoivo2().getNome());
            }
        }
    }
}
