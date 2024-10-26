package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.model.Evento;
import mvc.model.MuralRecados;
import mvc.model.Pagamento;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.itextpdf.text.pdf.BaseFont;

public class RelatorioDAOMemoria implements RelatorioDAO {

    // Criação das Fontes de Texto
    private static final String FONT = "fonts/CaviarDreams.ttf";
    private static final String FONT_BOLD = "fonts/CaviarDreams_Bold.ttf";
    private static final String FONT_ITALIC = "fonts/CaviarDreams_Italic.ttf";
    private static final String FONT_BOLD_ITALIC = "fonts/CaviarDreams_BoldItalic.ttf";

    // Criação das Fontes de Título
    private static final String FONT_TITLE = "fonts/Ephesis-Regular.ttf";

    // Carregar a fonte personalizada
    BaseFont CaviarDreams;
    BaseFont CaviarDreamsBold;
    BaseFont CaviarDreamsItalic;
    BaseFont CaviarDreamsBoldItalic;
    BaseFont bfTitle;

    public RelatorioDAOMemoria() {
        try { // Tenta carregar as fontes de texto e título personalizadas
            CaviarDreams = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            CaviarDreamsBold = BaseFont.createFont(FONT_BOLD, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            CaviarDreamsItalic = BaseFont.createFont(FONT_ITALIC, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            CaviarDreamsBoldItalic = BaseFont.createFont(FONT_BOLD_ITALIC, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            bfTitle = BaseFont.createFont(FONT_TITLE, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void recadosRecebidosPDF(MuralRecados[] recados, String evento, String path) {
        try {
            // Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Adiciona a imagem de fundo
            Image background = Image.getInstance("images/moldura_reports_recados_segundo_tipo.png");
            background.setAbsolutePosition(0, 0);
            background.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            document.add(background);

            // Configuração de fontes
            Font fontNormal = new Font(CaviarDreams, 10, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontMaior = new Font(CaviarDreams, 12, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontHeader = new Font(CaviarDreamsBold, 10, Font.BOLD, new BaseColor(128, 0, 128));
            Font fontNormalItalic = new Font(CaviarDreamsItalic, 10, Font.ITALIC, new BaseColor(128, 0, 128));
            Font fontBoldItalic = new Font(CaviarDreamsBoldItalic, 10, Font.BOLDITALIC, new BaseColor(128, 0, 128));
            Font fontTitle = new Font(bfTitle, 30, Font.ITALIC, new BaseColor(128, 0, 128));

            // Adicionando título
            Paragraph title = new Paragraph("Relatório - Recados Recebidos do Evento: ", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(18f);
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
            table.setWidthPercentage(90); // Largura total da página
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

            System.out.println("\nPDF gerado com sucesso no caminho: " + path);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void conviteIndividualPDF(ConvidadoIndividual convidado, Evento evento, String path) {
	    try {
	        // Cria o documento A4 na horizontal (paisagem)
	        Document document = new Document(PageSize.A4.rotate());
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
	        document.open();  // Documento é aberto aqui
	        
	        // Adiciona a imagem de fundo
	        Image background = Image.getInstance("images/moldura_reports_recados_segundo_tipo.png");
	        
	        // Escala a imagem para cobrir a página
	        background.scaleAbsolute(PageSize.A4.getHeight(), PageSize.A4.getWidth());  // Corrige para A4 horizontal
	        
	        // Posiciona a imagem no canto inferior esquerdo
	        background.setAbsolutePosition(0, 0);  // Garantir que ela comece do canto inferior esquerdo
	        
	        // Adiciona a imagem ao documento
	        document.add(background);

	        // Configuração de cores
	        BaseColor corRoxoClaro = new BaseColor(209, 196, 233); // #D1C4E9
	        BaseColor corRoxoEscuro = new BaseColor(74, 20, 140);  // #4A148C
	        BaseColor corRoxoIntermediario = new BaseColor(106, 27, 154); // #6A1B9A

	        // Configuração de fontes
            Font fontNormal = new Font(CaviarDreams, 10, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontMaior = new Font(CaviarDreams, 12, Font.NORMAL, BaseColor.BLACK);
            Font fontHeader = new Font(CaviarDreamsBold, 10, Font.BOLD, new BaseColor(128, 0, 128));
            Font fontNormalItalic = new Font(CaviarDreamsItalic, 10, Font.ITALIC, new BaseColor(128, 0, 128));
            Font fontBoldItalic = new Font(CaviarDreamsBoldItalic, 10, Font.BOLDITALIC, new BaseColor(128, 0, 128));
            Font fontTitle = new Font(bfTitle, 30, Font.ITALIC, new BaseColor(128, 0, 128));
            Font fontSubtitle = new Font(bfTitle, 25, Font.ITALIC, new BaseColor(128, 0, 128));

	        // Adicionando espaçamento antes do conteúdo
	        document.add(new Paragraph("\n\n\n"));

	        // Adicionando o título (nomes dos noivos)
	        Paragraph title = new Paragraph(evento.getPessoaNoivo1().getNome() + " & " + evento.getPessoaNoivo2().getNome(), fontTitle);
	        title.setAlignment(Element.ALIGN_CENTER);
	        document.add(title);
	        
	        document.add(new Paragraph("\n\n\n")); // Espaçamento

	        // Adicionando uma frase convite
	        Paragraph invitationText = new Paragraph("Têm a honra de convidá-lo para celebrar este momento especial", fontMaior);
	        invitationText.setAlignment(Element.ALIGN_CENTER);
	        document.add(invitationText);

	        document.add(new Paragraph("\n\n\n")); // Espaçamento

	        // Adicionando a data do casamento
	        Paragraph weddingDate = new Paragraph("Data: " + evento.getDataEvento(), fontNormal);
	        weddingDate.setAlignment(Element.ALIGN_CENTER);
	        document.add(weddingDate);

	        document.add(new Paragraph("\n")); // Espaçamento
	        
	        // Adicionando o local do casamento (igreja ou cartório)
	        String local = evento.getIgreja() != null ? evento.getIgreja() : evento.getCartorio();
	        Paragraph weddingLocation = new Paragraph("Local: " + local, fontNormal);
	        weddingLocation.setAlignment(Element.ALIGN_CENTER);
	        document.add(weddingLocation);

	        document.add(new Paragraph("\n\n\n")); // Espaçamento

	        // Adicionando um texto específico para o convidado
	        Paragraph guestText = new Paragraph(convidado.getPessoa().getNome(), fontSubtitle);
	        guestText.setAlignment(Element.ALIGN_CENTER);
	        document.add(guestText);

	        // Linha decorativa
	        /*document.add(new Paragraph("\n"));
	        PdfPTable lineTable = new PdfPTable(1);
	        PdfPCell lineCell = new PdfPCell();
	        lineCell.setBorderColor(corRoxoEscuro);
	        lineCell.setBorderWidth(2f);
	        lineCell.setFixedHeight(2f);
	        lineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        lineCell.setBorder(Rectangle.BOTTOM);
	        lineTable.addCell(lineCell);
	        document.add(lineTable);*/

	        document.add(new Paragraph("\n\n\n\n")); // Espaçamento
	        
	        // Mensagem final
	        Paragraph finalText = new Paragraph("Esperamos celebrar com você este momento tão especial.", fontMaior);
	        finalText.setAlignment(Element.ALIGN_CENTER);
	        document.add(finalText);

	        // Fechar o documento
	        document.close();

	        System.out.println("Convite PDF gerado com sucesso no caminho: " + path);

	    } catch (DocumentException | IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void conviteIndividualFamiliaPDF(ConvidadoFamilia convidado, Evento evento, String path) {
		
	}

	@Override
	public void pagamentosRealizadosPDF(Pagamento[] pagamentos, String path) {
		try {
			// Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            
            // Adiciona o event listener para o fundo
            /*BackgroundImage background = new BackgroundImage("images/moldura_reports_recados_segundo_tipo.png");
            writer.setPageEvent(background);*/

            document.open();

            // Configuração de fontes
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 16);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fontMaior = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);

            // Adicionando título
            Paragraph title = new Paragraph("Relatório - Pagamentos Recebidos do Evento: ", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(18f);
            document.add(title);

            // Adicionando data de geração do relatório
            LocalDateTime dataAtual = LocalDateTime.now();
            Paragraph date = new Paragraph("Data de Geração: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fontNormal);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n"));  // Espaçamento

            // Criando uma tabela para os pagamentos
            PdfPTable table = new PdfPTable(6); // 6 colunas
            table.setWidthPercentage(90); // Largura total da página
            table.setSpacingBefore(10f); // Espaço antes da tabela

            // Definindo a largura das colunas
            table.setWidths(new float[]{10f, 25f, 25f, 15f, 10f, 15f});

            // Cabeçalhos da tabela
            table.addCell(createCell("ID", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Pessoa", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Fornecedor", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Descrição", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Valor", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));
            table.addCell(createCell("Data Pagamento", fontHeader, BaseColor.LIGHT_GRAY, Element.ALIGN_CENTER));

            // Adicionando os pagamentos na tabela
            for (Pagamento pagamento : pagamentos) {
                table.addCell(createCell(String.valueOf(pagamento.getId()), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
                table.addCell(createCell(pagamento.getPessoa().getNome(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                table.addCell(createCell(pagamento.getFornecedor().getNome(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                table.addCell(createCell(pagamento.getDescricao(), fontNormal, BaseColor.WHITE, Element.ALIGN_LEFT));
                table.addCell(createCell(String.format("%.2f", pagamento.getValor()), fontNormal, BaseColor.WHITE, Element.ALIGN_RIGHT));
                table.addCell(createCell(pagamento.getDataPagamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), fontNormal, BaseColor.WHITE, Element.ALIGN_CENTER));
            }

            // Adiciona a tabela ao documento
            document.add(table);
            document.close(); // Fecha o documento

            System.out.println("\nPDF gerado com sucesso no caminho: " + path);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
	}

    @Override
    public void listaConvidadosPDF(ConvidadoIndividual[] convidados, String path) {
        try {
            // Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Adiciona a imagem de fundo
            Image background = Image.getInstance("images/moldura_reports_lista_convidados.png");
            background.setAbsolutePosition(0, 0);
            background.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            document.add(background);

            // Configuração de fontes
            Font fontNormal = new Font(CaviarDreams, 10, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontMaior = new Font(CaviarDreams, 12, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontHeader = new Font(CaviarDreamsBold, 10, Font.BOLD, new BaseColor(128, 0, 128));
            Font fontNormalItalic = new Font(CaviarDreamsItalic, 10, Font.ITALIC, new BaseColor(128, 0, 128));
            Font fontBoldItalic = new Font(CaviarDreamsBoldItalic, 10, Font.BOLDITALIC, new BaseColor(128, 0, 128));
            Font fontTitle = new Font(bfTitle, 30, Font.ITALIC, new BaseColor(128, 0, 128));

            // Adicionando título
            Paragraph title = new Paragraph("Lista de Convidados", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(35f);
            document.add(title);

            // Adicionando data de geração do relatório
            LocalDateTime dataAtual = LocalDateTime.now();
            Paragraph date = new Paragraph("Data: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fontNormal);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n"));  // Espaçamento

            // Criando uma tabela para os convidados
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(90); // Largura total da página
            table.setSpacingBefore(10f); // Espaço antes da tabela

            // Definindo a largura das colunas
            table.setWidths(new float[]{10f, 25f, 20f, 15f, 20f});

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

            System.out.println("\nPDF gerado com sucesso no caminho: " + path);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listaConvidadosConfirmadosPDF(ConvidadoIndividual[] convidados, String path) {
        try {
            // Cria o documento A4
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Adiciona a imagem de fundo
            Image background = Image.getInstance("images/moldura_reports_lista_convidados.png");
            background.setAbsolutePosition(0, 0);
            background.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            document.add(background);

            // Configuração de fontes
            Font fontNormal = new Font(CaviarDreams, 10, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontMaior = new Font(CaviarDreams, 12, Font.NORMAL, new BaseColor(162, 25, 255));
            Font fontHeader = new Font(CaviarDreamsBold, 10, Font.BOLD, new BaseColor(128, 0, 128));
            Font fontNormalItalic = new Font(CaviarDreamsItalic, 10, Font.ITALIC, new BaseColor(128, 0, 128));
            Font fontBoldItalic = new Font(CaviarDreamsBoldItalic, 10, Font.BOLDITALIC, new BaseColor(128, 0, 128));
            Font fontTitle = new Font(bfTitle, 30, Font.ITALIC, new BaseColor(128, 0, 128));

            // Adicionando título
            Paragraph title = new Paragraph("Lista de Convidados Confirmados", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(35f);
            document.add(title);

            // Adicionando data de geração do relatório
            LocalDateTime dataAtual = LocalDateTime.now();
            Paragraph date = new Paragraph("Data: " + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), fontNormal);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            document.add(new Paragraph("\n"));  // Espaçamento

            // Criando uma tabela para os convidados
            PdfPTable table = new PdfPTable(5); // 5 colunas
            table.setWidthPercentage(90); // Largura total da página
            table.setSpacingBefore(10f); // Espaço antes da tabela

            // Definindo a largura das colunas
            table.setWidths(new float[]{5f, 25f, 15f, 10f, 20f});

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

            System.out.println("\nPDF gerado com sucesso no caminho: " + path);

        } catch (DocumentException | IOException e) {
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
    
    class BackgroundImage extends PdfPageEventHelper {
        private Image background;

        public BackgroundImage(String imagePath) throws IOException, DocumentException {
            background = Image.getInstance(imagePath);
            background.setAbsolutePosition(0, 0);
            background.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte canvas = writer.getDirectContentUnder();
                canvas.addImage(background); // Adiciona a imagem de fundo
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}
