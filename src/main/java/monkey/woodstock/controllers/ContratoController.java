package monkey.woodstock.controllers;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import monkey.woodstock.Util.UtilTime;
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
public class ContratoController {

    private ContratoService contratoService;
    private MesBonificadoService mesBonificadoService;
    private ClienteService clienteService;
    
    @Autowired
    private ContratoValidator contratoValidator;
     
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(contratoValidator);
    }

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

    @RequestMapping(value = "/contratos", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("contratos", contratoService.listAllContratos());
        return "contratos";
    }

    @RequestMapping(value = "contrato/{id}")
    public String showContrato(@PathVariable Integer id, Model model) {
        Contrato contrato = contratoService.getContratoById(id);
        model.addAttribute("contrato", contrato);
        return "contratoshow";
    }
    
    @RequestMapping(value = "contrato/{id}", params = {"volver"})
    public String volverContrato() {
        return "redirect:/contratos";
    }

    @RequestMapping(value = "contrato/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("contrato", contratoService.getContratoById(id));
        model.addAttribute("clientes", clienteService.listAllClientes());
        return "contratoform";
    }
    
    @RequestMapping(value = "contrato/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
    	contratoService.deleteContrato(contratoService.getContratoById(id));
    	return "redirect:/contratos";
    }

    @RequestMapping(value = "contrato/new")
    public String add(Model model) {
        Contrato contrato = new Contrato();
        contrato.setFechaInicio(UtilTime.getMesAnioActual());
        contrato.setFechaFin(UtilTime.getMesAnioSiguiente());
        model.addAttribute(contrato);
        model.addAttribute("clientes", clienteService.listAllClientes());
        return "contratoform";
    }
    
    @RequestMapping(value = "contrato/new", params = {"addRow"})
    public String addRow(Model model, Contrato contrato) {
        MesBonificado mesBonificado = new MesBonificado();
        mesBonificado.setMes(UtilTime.getMesAnioActual());
        mesBonificadoService.saveMesBonificado(mesBonificado);
        contrato.getMesesBonificados().add(mesBonificado);
        model.addAttribute("clientes", clienteService.listAllClientes());
        return "contratoform";
    }
    
    @RequestMapping(value = "contrato/new", params = {"removeRow"})
    public String removeRow(Contrato contrato, HttpServletRequest req, Model model ){
        String sid = req.getParameter("removeRow");
        if (sid != null && !sid.isEmpty()) {
            Integer id = Integer.valueOf(sid);
            if (contrato.getMesesBonificados() != null && !contrato.getMesesBonificados().isEmpty()) {	
                Iterator<MesBonificado> it = contrato.getMesesBonificados().iterator();
                while (it.hasNext()) {
                    MesBonificado mes = it.next();
                    if (mes.getId().equals(id)) {
                        it.remove();
                    }
                }
            }
        }
        model.addAttribute("clientes", clienteService.listAllClientes());
        return "contratoform";
    }

    @RequestMapping(value = "contrato/new", params = {"cancel"})
    public String cancel(Contrato contrato) {
    	return "redirect:/contratos";
    }
    
    @RequestMapping(value = "contrato/new", method = RequestMethod.POST)
    public String saveContrato(@Valid Contrato contrato, BindingResult bindingResult, Model model)  {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("clientes", clienteService.listAllClientes());
            return "contratoform";
        }
        for (MesBonificado mesBonificado : contrato.getMesesBonificados()) {
            mesBonificadoService.saveMesBonificado(mesBonificado);
        }
        contratoService.saveContrato(contrato);
        return "redirect:/contrato/" + contrato.getId();
    }
}

