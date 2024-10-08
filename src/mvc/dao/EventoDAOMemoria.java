package mvc.dao;

import mvc.model.Evento;
import mvc.model.Pessoa;
import mvc.model.Util;

import java.time.LocalDate;

public class EventoDAOMemoria implements EventoDAO {
    private final Evento[] eventos;
    private int totalEventos;

    public EventoDAOMemoria(PessoaDAO pessoaDAO, int capacidade) {
        this.eventos = new Evento[capacidade];
        this.totalEventos = 0;

        // Armazenando eventos de exemplo
        Pessoa cerimonialista1 = pessoaDAO.buscaPorId(5L);
        Pessoa noivo1 = pessoaDAO.buscaPorId(0L);
        Pessoa noivo2 = pessoaDAO.buscaPorId(1L);
        Evento evento1 = new Evento(LocalDate.now(), cerimonialista1, "Igreja Central", "Cartório Central", noivo1, noivo2);
        this.criarEvento(evento1);

        Pessoa cerimonialista2 = pessoaDAO.buscaPorId(6L);
        Pessoa noivo3 = pessoaDAO.buscaPorId(7L);
        Pessoa noivo4 = pessoaDAO.buscaPorId(8L);
        Evento evento2 = new Evento(LocalDate.now().plusDays(30), cerimonialista2, "Igreja Nova", "Cartório Nova", noivo3, noivo4);
        this.criarEvento(evento2);
    }

    // Criar um novo evento
    public void criarEvento(Evento evento) {
        if (totalEventos < eventos.length) {
            eventos[totalEventos++] = evento;
            System.out.println("Evento cadastrado com sucesso:\n\n" + evento);
        } else {
            System.out.println("\nCapacidade máxima de eventos atingida.");
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
                return;
            }
        }
    }

    // Remover evento por ID
    public void removerEvento(long id) {
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null && eventos[i].getId() == id) {
                eventos[i] = null;  // Remove o evento
                System.out.println("\nEvento removido.");
                return;
            }
        }
        System.out.println("\nEvento não encontrado.");
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
