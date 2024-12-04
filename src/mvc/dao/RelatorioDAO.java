package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.model.Evento;
import mvc.model.MuralRecados;
import mvc.model.Pagamento;

import java.util.ArrayList;

public interface RelatorioDAO {
	void recadosRecebidosPDF(MuralRecados[] recados, String evento, String path);
	void conviteIndividualPDF(ConvidadoIndividual convidado, Evento evento, String path);
	void conviteIndividualFamiliaPDF(ConvidadoFamilia convidado, Evento evento, String path);
	void pagamentosRealizadosPDF(ArrayList <Pagamento> pagamentos, String path);
	void listaConvidadosPDF(ArrayList <ConvidadoIndividual> convidados, String path);
	void listaConvidadosConfirmadosPDF(ArrayList <ConvidadoIndividual> convidados, String path);
}