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
        Pessoa cerimonialista1 = pessoaDAO.buscaPorId(3L);
        Pessoa noivo1 = pessoaDAO.buscaPorId(0L);
        Pessoa noivo2 = pessoaDAO.buscaPorId(1L);
        Evento evento1 = new Evento(LocalDate.now(), cerimonialista1, "Igreja Central", "Cartório Central", noivo1, noivo2);
        this.criarEvento(evento1);

        Pessoa cerimonialista2 = pessoaDAO.buscaPorId(4L);
        Pessoa noivo3 = pessoaDAO.buscaPorId(2L);
        Pessoa noivo4 = pessoaDAO.buscaPorId(3L);
        Evento evento2 = new Evento(LocalDate.now().plusDays(30), cerimonialista2, "Igreja Nova", "Cartório Nova", noivo3, noivo4);
        this.criarEvento(evento2);
    }

    public void criarEvento(Evento evento) {
        if (totalEventos < eventos.length) {
            eventos[totalEventos++] = evento;
            System.out.println("Evento cadastrado com sucesso:\n\n" + evento);
        } else {
            System.out.println("\nCapacidade máxima de eventos atingida.");
        }
    }

    public Evento buscarPorId(long id) {
        for (Evento evento : eventos) {
            if (evento != null && evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }

    public void atualizarEvento(long id, Evento eventoAtualizado) {
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null && eventos[i].getId() == id) {
                eventos[i] = eventoAtualizado;
                eventos[i].setDataModificacao(Util.getDia());
                return;
            }
        }
    }

    public void removerEvento(long id) {
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] != null && eventos[i].getId() == id) {
                eventos[i] = null;
                reorganizarArray(i);
                System.out.println("\nEvento removido.");
                return;
            }
        }
        System.out.println("\nEvento não encontrado.");
    }

    private void reorganizarArray(int posicaoRemovida) {
        for (int i = posicaoRemovida; i < eventos.length - 1; i++) {
            eventos[i] = eventos[i + 1];
        }
        eventos[eventos.length - 1] = null;
        totalEventos--;
    }

    public void listarEventos() {
        if (totalEventos == 0) {
            System.out.println("Nenhum eventro cadastrado no sistema!");
        } else {
            for (Evento evento : eventos) {
                if (evento != null) {
                    System.out.println(evento.toString());
                }
            }
        }
    }

    public void exibirListaEventosSimples() {
        if (totalEventos == 0) {
            System.out.println("Nenhum eventro cadastrado no sistema!");
        } else {
            for (Evento evento : eventos) {
                if (evento != null) {
                    System.out.println(evento.getId() + " - " + evento.getDataEvento() + " - " + evento.getIgreja() + " - " + evento.getPessoaNoivo1().getNome() + " - " + evento.getPessoaNoivo2().getNome());
                }
            }
        }
    }
}