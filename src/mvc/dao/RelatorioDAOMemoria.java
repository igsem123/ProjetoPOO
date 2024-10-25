package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.model.MuralRecados;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelatorioDAOMemoria implements RelatorioDAO {

	@Override
    public void recadosRecebidosPDF(MuralRecados[] recados, String evento, String path) {
        try {
            // Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Configuração de fontes
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fontMaior = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // Adicionando título
            Paragraph title = new Paragraph("Relatório - Recados Recebidos do Evento: ", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // Adicionando subtítulo
            Paragraph subtitle = new Paragraph(evento, fontMaior);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            // Adicionando data
            LocalDateTime dataAtual = LocalDateTime.now();
            Paragraph date = new Paragraph("Data de Geração: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fontNormal);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n"));  // Espaçamento

            // Criando uma tabela para os recados
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(100); // Largura total da página
            table.setSpacingBefore(10f); // Espaço antes da tabela

            // Definindo a largura das colunas
            table.setWidths(new float[]{10f, 25f, 25f, 20f, 20f});

            // Cabeçalhos da tabela
            table.addCell(createCell("ID", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Quem Comentou", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Comentário", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Data Criação", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Data Modificação", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));

            // Adiciona os recados na tabela
            for (MuralRecados recado : recados) {
                // Adicionando dados à tabela
                table.addCell(createCell(String.valueOf(recado.getId()), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                
                // Verifica se há pessoa ou nomePessoa associado ao recado
                String quemComentou = recado.getPessoa() != null ? recado.getPessoa().getNome() : recado.getNomePessoa();
                table.addCell(createCell(quemComentou != null ? quemComentou : "Não Definido", fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                
                // Adiciona o comentário
                table.addCell(createCell(recado.getComentario(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                
                // Adiciona a data de criação e modificação
                table.addCell(createCell(recado.getDataCriacao(), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                table.addCell(createCell(recado.getDataModificacao(), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
            }

            document.add(table); // Adiciona a tabela ao documento
            document.close(); // Fecha o documento

            System.out.println("PDF gerado com sucesso no caminho: " + path);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para criar células com formatação
    private PdfPCell createCell(String content, Font font, BaseColor bgColor, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBackgroundColor(bgColor);
        cell.setHorizontalAlignment(alignment);
        cell.setPadding(5f); // Margem interna
        return cell;
    }

	@Override
	public void conviteIndividualPDF() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void conviteIndividualFamiliaPDF() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pagamentosRealizadosPDF() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listaConvidadosPDF(ConvidadoIndividual[] convidados, String path) {
		try {
            // Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Configuração de fontes
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fontMaior = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // Adicionando título
            Paragraph title = new Paragraph("Lista de Convidados", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // Adicionando data de geração do relatório
            LocalDateTime dataAtual = LocalDateTime.now();
            Paragraph date = new Paragraph("Data: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fontNormal);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n"));  // Espaçamento

            // Criando uma tabela para os convidados
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(100); // Largura total da página
            table.setSpacingBefore(10f); // Espaço antes da tabela

            // Definindo a largura das colunas
            table.setWidths(new float[]{10f, 25f, 25f, 20f, 20f});

            // Cabeçalhos da tabela
            table.addCell(createCell("ID", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Nome", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Parentesco", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Família", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Confirmação", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));

            // Adiciona os convidados na tabela
            for (ConvidadoIndividual convidado : convidados) {
                if (convidado != null) {
                    // Adicionando dados dos convidados à tabela
                    table.addCell(createCell(String.valueOf(convidado.getId()), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                    table.addCell(createCell(convidado.getPessoa().getNome(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                    table.addCell(createCell(convidado.getParentesco(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                    table.addCell(createCell(convidado.getFamilia().getNomeFamilia(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                    table.addCell(createCell(convidado.getConfirmacao(), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                }
            }

            document.add(table); // Adiciona a tabela ao documento
            document.close(); // Fecha o documento

            System.out.println("PDF gerado com sucesso no caminho: " + path);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void listaConvidadosConfirmadosPDF(ConvidadoIndividual[] convidados, String path) {
		try {
            // Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Configuração de fontes
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fontMaior = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // Adicionando título
            Paragraph title = new Paragraph("Lista de Convidados Confirmados", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // Adicionando data de geração do relatório
            LocalDateTime dataAtual = LocalDateTime.now();
            Paragraph date = new Paragraph("Data: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fontNormal);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n"));  // Espaçamento

            // Criando uma tabela para os convidados
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(100); // Largura total da página
            table.setSpacingBefore(10f); // Espaço antes da tabela

            // Definindo a largura das colunas
            table.setWidths(new float[]{10f, 25f, 25f, 20f, 20f});

            // Cabeçalhos da tabela
            table.addCell(createCell("ID", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Nome", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Parentesco", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Família", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Confirmação", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));

            // Adiciona os convidados na tabela
            for (ConvidadoIndividual convidado : convidados) {
                if (convidado != null) {
                    // Adicionando dados dos convidados à tabela
                    table.addCell(createCell(String.valueOf(convidado.getId()), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                    table.addCell(createCell(convidado.getPessoa().getNome(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                    table.addCell(createCell(convidado.getParentesco(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                    table.addCell(createCell(convidado.getFamilia().getNomeFamilia(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                    table.addCell(createCell(convidado.getConfirmacao(), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                }
            }

            document.add(table); // Adiciona a tabela ao documento
            document.close(); // Fecha o documento

            System.out.println("PDF gerado com sucesso no caminho: " + path);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
	}

}
