package monkey.woodstock.PDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import javax.servlet.http.HttpServletResponse;
 
@RestController
class PdfController {
 
    private final PdfFileCreator pdfFileCreator;
 
    @Autowired
    PdfController(PdfFileCreator pdfFileCreator) {
        this.pdfFileCreator = pdfFileCreator;
    }
 
    @RequestMapping(value = "/api/pdf", method = RequestMethod.POST)
    void createPdf(@RequestBody PdfFileRequest fileRequest, HttpServletResponse response) {
        pdfFileCreator.writePdfToResponse(fileRequest, response);
    }
}