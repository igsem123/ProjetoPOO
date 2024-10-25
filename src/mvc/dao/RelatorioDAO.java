package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.model.MuralRecados;

public interface RelatorioDAO {
	void recadosRecebidosPDF(MuralRecados[] recados, String evento, String path);
	void conviteIndividualPDF();
	void conviteIndividualFamiliaPDF();
	void pagamentosRealizadosPDF();
	void listaConvidadosPDF(ConvidadoIndividual[] convidados, String path);
	void listaConvidadosConfirmadosPDF(ConvidadoIndividual[] convidados, String path);
}