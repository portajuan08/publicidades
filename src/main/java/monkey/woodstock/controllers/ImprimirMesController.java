package monkey.woodstock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import monkey.woodstock.domain.FiltroBusqueda;
import monkey.woodstock.services.ClienteService;
import monkey.woodstock.services.ContratoService;
import monkey.woodstock.services.MesBonificadoService;

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
    
    @RequestMapping(value = "/imprimir", method = RequestMethod.GET)
    public String imprimir(Model model, FiltroBusqueda filtroBusqueda) {
    	System.out.println("imprimr me => " + filtroBusqueda);
    	model.addAttribute("filtroBusqueda",filtroBusqueda);
        model.addAttribute("contratos", contratoService.listAllContratos(filtroBusqueda));
        return "imprimirMes";
    }
}