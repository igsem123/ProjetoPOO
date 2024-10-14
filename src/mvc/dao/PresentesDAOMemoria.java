package mvc.dao;

import mvc.model.Pessoa;
import mvc.model.Presentes;

import java.util.Arrays;

public class PresentesDAOMemoria implements PresentesDAO{
    private Presentes[] presentes;
    private int indice;

    public PresentesDAOMemoria(PessoaDAO pessoaDAO, int capacidade) {
        this.presentes = new Presentes[capacidade];
        this.indice = 0;

        Presentes faqueiro = new Presentes("Faqueiro (jogo de talheres)", 1, 150.00);
        this.adicionarPresente(faqueiro);
        Presentes aparelhoJantar = new Presentes("Aparelho de jantar", 1, 200.00);
        this.adicionarPresente(aparelhoJantar);
        Presentes jogoPanelas = new Presentes("Jogo de panelas", 1, 250.00);
        this.adicionarPresente(jogoPanelas);
        Presentes jogoFormasAssadeiras = new Presentes("Jogo de formas e assadeiras", 1, 120.00);
        this.adicionarPresente(jogoFormasAssadeiras);
        Presentes chaleira = new Presentes("Chaleira", 1, 80.00);
        this.adicionarPresente(chaleira);
        Presentes jogoCopos = new Presentes("Jogo de copos (dia a dia)", 1, 70.00);
        this.adicionarPresente(jogoCopos);
        Presentes jogoTacas = new Presentes("Jogo de taças", 1, 100.00);
        this.adicionarPresente(jogoTacas);
        Presentes buleLeiteira = new Presentes("Bule e leiteira", 1, 90.00);
        this.adicionarPresente(buleLeiteira);
        Presentes fruteira = new Presentes("Fruteira", 1, 60.00);
        this.adicionarPresente(fruteira);
        Presentes garrafaTermica = new Presentes("Garrafa térmica", 1, 50.00);
        this.adicionarPresente(garrafaTermica);
        Presentes balancaCozinha = new Presentes("Balança para cozinha", 1, 40.00);
        this.adicionarPresente(balancaCozinha);
        Presentes jogoFacas = new Presentes("Jogo de facas", 1, 150.00);
        this.adicionarPresente(jogoFacas);
        Presentes tabuaQueijosFrios = new Presentes("Tábua para queijos e frios", 1, 45.00);
        this.adicionarPresente(tabuaQueijosFrios);
        Presentes tabuaCarnes = new Presentes("Tábua para carnes", 1, 70.00);
        this.adicionarPresente(tabuaCarnes);
        Presentes baldeGelo = new Presentes("Balde de gelo", 1, 80.00);
        this.adicionarPresente(baldeGelo);
        Presentes aparelhoCafeCha = new Presentes("Aparelho de café e chá", 1, 110.00);
        this.adicionarPresente(aparelhoCafeCha);
        Presentes bomboniere = new Presentes("Bomboniere", 1, 35.00);
        this.adicionarPresente(bomboniere);
        Presentes jogoSobremesa = new Presentes("Jogo de sobremesa", 1, 90.00);
        this.adicionarPresente(jogoSobremesa);
        Presentes conjuntoBolo = new Presentes("Conjunto para bolo (prato e espátula)", 1, 60.00);
        this.adicionarPresente(conjuntoBolo);
        Presentes jarra = new Presentes("Jarra", 1, 40.00);
        this.adicionarPresente(jarra);
        Presentes petisqueira = new Presentes("Petisqueira", 1, 50.00);
        this.adicionarPresente(petisqueira);
        Presentes portaCondimentos = new Presentes("Porta-condimentos/temperos", 1, 60.00);
        this.adicionarPresente(portaCondimentos);
        Presentes portaGuardapo = new Presentes("Porta-guardapo", 1, 20.00);
        this.adicionarPresente(portaGuardapo);
        Presentes saladeira = new Presentes("Saladeira", 1, 80.00);
        this.adicionarPresente(saladeira);
        Presentes apoioCopos = new Presentes("Apoio para copos", 1, 25.00);
        this.adicionarPresente(apoioCopos);
        Presentes jogoUtensilios = new Presentes("Jogo de utensílios e espátulas", 1, 70.00);
        this.adicionarPresente(jogoUtensilios);
        Presentes jogoLugarAmericano = new Presentes("Jogo de lugar americano", 1, 50.00);
        this.adicionarPresente(jogoLugarAmericano);
        Presentes fogao = new Presentes("Fogão", 2, 1500.00);
        this.adicionarPresente(fogao);
        Presentes coifa = new Presentes("Coifa", 2, 900.00);
        this.adicionarPresente(coifa);
        Presentes geladeira = new Presentes("Geladeira", 2, 2500.00);
        this.adicionarPresente(geladeira);
        Presentes freezer = new Presentes("Freezer", 2, 2000.00);
        this.adicionarPresente(freezer);
        Presentes liquidificador = new Presentes("Liquidificador", 2, 150.00);
        this.adicionarPresente(liquidificador);
        Presentes torradeira = new Presentes("Torradeira/sanduicheira", 2, 200.00);
        this.adicionarPresente(torradeira);
        Presentes airFryer = new Presentes("Air fryer", 2, 400.00);
        this.adicionarPresente(airFryer);
        Presentes microOndas = new Presentes("Micro-ondas", 2, 350.00);
        this.adicionarPresente(microOndas);
        Presentes processadorAlimentos = new Presentes("Processador de alimentos", 2, 500.00);
        this.adicionarPresente(processadorAlimentos);
        Presentes batedeira = new Presentes("Batedeira", 2, 300.00);
        this.adicionarPresente(batedeira);
        Presentes espremedorFrutas = new Presentes("Espremedor de frutas", 2, 100.00);
        this.adicionarPresente(espremedorFrutas);
        Presentes aparelhoFondue = new Presentes("Aparelho de fondue e raclette", 2, 150.00);
        this.adicionarPresente(aparelhoFondue);
        Presentes cafeteira = new Presentes("Cafeteira", 2, 200.00);
        this.adicionarPresente(cafeteira);
        Presentes grillEletrico = new Presentes("Grill elétrico", 2, 250.00);
        this.adicionarPresente(grillEletrico);
        Presentes juicer = new Presentes("Juicer", 2, 180.00);
        this.adicionarPresente(juicer);
        Presentes maquinaPao = new Presentes("Máquina de pão", 2, 450.00);
        this.adicionarPresente(maquinaPao);
        Presentes panelaEletrica = new Presentes("Panela elétrica", 2, 220.00);
        this.adicionarPresente(panelaEletrica);
        Presentes jogoLençol = new Presentes("Jogo de lençóis", 3, 200.00);
        this.adicionarPresente(jogoLençol);
        Presentes edredom = new Presentes("Edredom", 3, 300.00);
        this.adicionarPresente(edredom);
        Presentes manta = new Presentes("Manta", 3, 150.00);
        this.adicionarPresente(manta);
        Presentes cobertor = new Presentes("Cobertor", 3, 180.00);
        this.adicionarPresente(cobertor);
        Presentes jogoToalhasBanho = new Presentes("Jogo de toalhas de banho", 3, 120.00);
        this.adicionarPresente(jogoToalhasBanho);
        Presentes toalhasMesa = new Presentes("Toalhas de mesa", 3, 100.00);
        this.adicionarPresente(toalhasMesa);
        Presentes casticaisVelas = new Presentes("Castiçais e velas", 3, 60.00);
        this.adicionarPresente(casticaisVelas);
        Presentes objetosDecoracao = new Presentes("Objetos de decoração", 3, 250.00);
        this.adicionarPresente(objetosDecoracao);
        Presentes tapetes = new Presentes("Tapetes", 3, 350.00);
        this.adicionarPresente(tapetes);
        Presentes conjuntoPiaLavabo = new Presentes("Conjunto para pia/lavabo", 3, 90.00);
        this.adicionarPresente(conjuntoPiaLavabo);
        Presentes aromatizadorAmbiente = new Presentes("Aromatizador de ambiente", 3, 80.00);
        this.adicionarPresente(aromatizadorAmbiente);
        Presentes tabuaPassar = new Presentes("Tábua de passar", 3, 120.00);
        this.adicionarPresente(tabuaPassar);
        Presentes capacho = new Presentes("Capacho", 3, 50.00);
        this.adicionarPresente(capacho);
        Presentes relogio = new Presentes("Relógio", 3, 150.00);
        this.adicionarPresente(relogio);
        Presentes vasos = new Presentes("Vasos", 3, 100.00);
        this.adicionarPresente(vasos);
        Presentes cama = new Presentes("Cama", 4, 2000.00);
        this.adicionarPresente(cama);
        Presentes colchao = new Presentes("Colchão", 4, 1800.00);
        this.adicionarPresente(colchao);
        Presentes sofa = new Presentes("Sofá", 4, 2500.00);
        this.adicionarPresente(sofa);
        Presentes tv = new Presentes("TV", 4, 3000.00);
        this.adicionarPresente(tv);
        Presentes mesaCadeiras = new Presentes("Mesa e cadeiras de jantar", 4, 2200.00);
        this.adicionarPresente(mesaCadeiras);
        Presentes rack = new Presentes("Rack", 4, 700.00);
        this.adicionarPresente(rack);
        Presentes luminaria = new Presentes("Luminária", 4, 350.00);
        this.adicionarPresente(luminaria);
        Presentes abajour = new Presentes("Abajour", 4, 200.00);
        this.adicionarPresente(abajour);
        Presentes ferroPassar = new Presentes("Ferro de passar", 4, 150.00);
        this.adicionarPresente(ferroPassar);
        Presentes lavadoraRoupas = new Presentes("Lavadora de roupas/Lava e seca", 4, 2500.00);
        this.adicionarPresente(lavadoraRoupas);
        Presentes aspiradorPo = new Presentes("Aspirador de pó", 4, 500.00);
        this.adicionarPresente(aspiradorPo);
        Presentes guardaRoupa = new Presentes("Guarda-roupa", 4, 2000.00);
        this.adicionarPresente(guardaRoupa);
        Presentes mesaCentro = new Presentes("Mesa de centro", 4, 800.00);
        this.adicionarPresente(mesaCentro);
        Presentes poltronas = new Presentes("Poltronas", 4, 1200.00);
        this.adicionarPresente(poltronas);
    }

    @Override
    public void adicionarPresente(Presentes presente) {
        if (indice < presentes.length) {
            presentes[indice] = presente;
            indice++;
            System.out.println("\nPresente adicionado com sucesso: \n\n" + presente.getNome());
        } else {
            System.out.println("\nCapacidade máxima de presentes atingida!");
        }
    }

    @Override
    public Presentes buscarPorId(long id) {
        for (Presentes presente : presentes) {
            if (presente != null && presente.getId() == id) {
                System.out.println("\n" + presente.toString());
                return presente;
            }
        }
        System.out.println("\nPresente não encontrado com ID: \n" + id);
        return null;
    }

    @Override
    public void darPresente(long id, Pessoa pessoa) {
        for (Presentes presente : presentes) {
            if (presente != null && presente.getId() == id) {
                presente.setPessoa(pessoa);
                System.out.println("\nPresente selecionado: \n\n" + presente.toString() + "\nPresente registrado aos noivos com sucesso!!");
                return;
            }
        }

        System.out.println("\nPresente não encontrado com o ID: " + id);
    }

    @Override
    public void exibeListaPresentesSimples() {
        for (Presentes presente : presentes) {
            if (presente != null) {
                System.out.println("\nID: ["+ presente.getId() +"] Nome: " + presente.getNome() + "\nValor: " + presente.getValor());

                if (presente.getPessoa() != null) {
                    System.out.println("-> Este presente já foi presenteado aos noivos por: " + presente.getPessoa().getNome());
                }
            }
        }
    }

    @Override
    public void atualizarPresente(long id, Presentes presenteAtualizado) {
        for (int i = 0; i < presentes.length; i++) {
            if (presentes[i] != null && presentes[i].getId() == id) {
                presentes[i] = presenteAtualizado;
                presentes[i].setDataModificacao();
                System.out.println("\nPresente atualizado: \n\n" + presenteAtualizado.toString());
                return;
            }
        }
        System.out.println("\nPresente não encontrado para atualizar!");
    }

    @Override
    public void removerPresente(long id) {
        for (int i = 0; i < presentes.length; i++) {
            if (presentes[i] != null && presentes[i].getId() == id) {
                presentes[i] = null;
                System.out.println("\nPresente removido com sucesso!");
                return;
            }
        }
        System.out.println("\nPresente não encontrado para remoção!");
    }
}
