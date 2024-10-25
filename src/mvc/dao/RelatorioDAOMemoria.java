package mvc.dao;

import mvc.model.ConvidadoFamilia;
import mvc.model.ConvidadoIndividual;
import mvc.model.MuralRecados;
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

}