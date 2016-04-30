package monkey.woodstock.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import monkey.woodstock.PDF.PdfFileRequest;
import monkey.woodstock.domain.FiltroBusqueda;
import monkey.woodstock.services.ContratoService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class ImprimirMesController {

    private ContratoService contratoService;

    @Autowired
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @RequestMapping(value = "/imprimirMes", method = RequestMethod.GET)
    public String list(Model model, FiltroBusqueda filtroBusqueda) {
    	model.addAttribute("filtroBusqueda",filtroBusqueda);
        model.addAttribute("contratos", contratoService.listAllContratos(filtroBusqueda));
        return "imprimirMes";
    }
    
    @RequestMapping(value = "/imprimir")
    public String listv4(Model model, FiltroBusqueda filtroBusqueda) {
    	model.addAttribute("filtroBusqueda",filtroBusqueda);
        model.addAttribute("contratos", contratoService.listAllContratos(filtroBusqueda));
        System.out.println("contrato cant " + contratoService.listAllContratos(filtroBusqueda).size());
        return "imprimirPdf";
    }
    
    private final RestTemplate restTemplate;
    
    @Autowired
    ImprimirMesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
 
    @RequestMapping(value = "/imprimirMes", params = {"imprimir"})
    void createPdfFromGoogle(HttpServletResponse response, FiltroBusqueda filtroBusqueda) {
        PdfFileRequest fileRequest = new PdfFileRequest();
        fileRequest.setFileName("google.pdf");
        
        System.out.println("filtro => " + filtroBusqueda);
        System.out.println("url => " + filtroBusqueda.getUrl());
        
        fileRequest.setSourceHtmlUrl("http://localhost:8080/imprimir?" + filtroBusqueda.getUrl());
        
        
 
        byte[] pdfFile = restTemplate.postForObject("http://localhost:8080/api/pdf", 
                fileRequest, 
                byte[].class
        );
        writePdfFileToResponse(pdfFile, "google.pdf", response);
    }
 
    private void writePdfFileToResponse(byte[] pdfFile, 
                                        String fileName, 
                                        HttpServletResponse response) {
        try (InputStream in = new ByteArrayInputStream(pdfFile)) {
            OutputStream out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
 
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        }
        catch (IOException ex) {
            throw new RuntimeException("Error occurred when creating PDF file", ex);
        }
    }
}