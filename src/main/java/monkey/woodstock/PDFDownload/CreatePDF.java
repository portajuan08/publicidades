package monkey.woodstock.PDFDownload;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import monkey.woodstock.domain.Contrato;
import monkey.woodstock.domain.FiltroBusqueda;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
public class CreatePDF {
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font HELVETIC = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD);
	
	
 
	/**
	 * @param args
	 */
	public static Document createPDF(String file, FiltroBusqueda filtroBusqueda, List<Contrato> contratos) {
 
		Document document = null;
 
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
 
			addMetaData(document);
 
			addTitlePage(document, filtroBusqueda.getMes());
 
			createTable(document,filtroBusqueda, contratos);
 
			document.close();
 
		} catch (FileNotFoundException e) {
 
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
 
	}
 
	private static void addMetaData(Document document) {
		document.addTitle("Publicidades del Mes");
		document.addSubject("Publicidades del Mes");
		document.addAuthor("Juan Porta");
		document.addCreator("Juan Porta");
	}
 
	private static void addTitlePage(Document document,String sMes)
			throws DocumentException {
 
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("Mes: " + sMes, TIME_ROMAN));
		document.add(preface);
 
	}
 
	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
 
	private static void createTable(Document document, FiltroBusqueda filtroBusqueda, List<Contrato> contratos) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(filtroBusqueda.getCantidadColumnas()); //agrego 1 por la columna nombre
		
		for (String sColumna : filtroBusqueda.getColumnas()){
			PdfPCell c1 = new PdfPCell(new Phrase(sColumna,HELVETIC));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
 
		table.setHeaderRows(1);
 
		for (Contrato oContrato : contratos) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(oContrato.getCliente().getNombre());
			if (filtroBusqueda.getTelefono()) table.addCell(oContrato.getCliente().getTelefono());
			if (filtroBusqueda.getDireccion()) table.addCell(oContrato.getCliente().getDireccion());
			if (filtroBusqueda.getVendedor()) table.addCell(oContrato.getVendedor());
			if (filtroBusqueda.getPrecio()) table.addCell(oContrato.getPrecio().toString());
			if (filtroBusqueda.getInicio()) table.addCell(oContrato.getFechaInicio());
			if (filtroBusqueda.getFin()) table.addCell(oContrato.getFechaFin());
			if (filtroBusqueda.getEsBonificado()) table.addCell(oContrato.getEsBonificado(filtroBusqueda.getMes()));
			if (filtroBusqueda.getSeVence()) table.addCell(oContrato.getSeVence(filtroBusqueda.getMes()));
		}
 
		document.add(table);
	}
 
}
