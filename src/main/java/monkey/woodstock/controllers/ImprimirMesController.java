package monkey.woodstock.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import monkey.woodstock.domain.Contrato;
import monkey.woodstock.domain.MesBonificado;
import monkey.woodstock.services.ClienteService;
import monkey.woodstock.services.ContratoService;
import monkey.woodstock.services.MesBonificadoService;
import monkey.woodstock.validator.ContratoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImprimirMesController {

    private ContratoService contratoService;
    private MesBonificadoService mesBonificadoService;
    private ClienteService clienteService;

    @Autowired
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Autowired
    public void setMesBonificadoService(MesBonificadoService mesBonificadoService) {
        this.mesBonificadoService = mesBonificadoService;
    }

    @RequestMapping(value = "/imprimirMes", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("contratos", contratoService.listAllContratos());
        return "imprimirMes";
    }
}