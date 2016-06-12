package monkey.woodstock.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import monkey.woodstock.PDFDownload.CreatePDF;
import monkey.woodstock.Util.UtilModel;
import monkey.woodstock.Util.UtilTime;
import monkey.woodstock.domain.FiltroBusqueda;
import monkey.woodstock.services.ChequeService;
import monkey.woodstock.services.ContratoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImprimirMesController {

    private ContratoService contratoService;
    private ChequeService chequeService;
    
    @Autowired
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }
    
    @Autowired
    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @RequestMapping(value = "/imprimirMes", method = RequestMethod.GET)
    public String list(Model model, FiltroBusqueda filtroBusqueda) {
    	model.addAttribute("filtroBusqueda",filtroBusqueda);
        model.addAttribute("contratos", contratoService.listAllContratos(filtroBusqueda));
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "imprimirMes";
    }
 
    @RequestMapping(value = "/imprimirMes", params = {"imprimir"})
	public void downloadPDF(HttpServletRequest request, HttpServletResponse response, FiltroBusqueda filtroBusqueda) throws IOException {
 
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();
	    
	    String fileName = "report" + UtilTime.fechaActual() + ".pdf";
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
 
	    try {
 
	        CreatePDF.createPDF(temperotyFilePath+"\\"+fileName, filtroBusqueda, contratoService.listAllContratos(filtroBusqueda));
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos = convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
 
	}
	
	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
 
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
}