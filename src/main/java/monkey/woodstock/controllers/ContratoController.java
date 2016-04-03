package monkey.woodstock.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import monkey.woodstock.domain.Contrato;
import monkey.woodstock.domain.MesBonificado;
import monkey.woodstock.services.ClienteService;
import monkey.woodstock.services.ContratoService;
import monkey.woodstock.services.MesBonificadoService;

@Controller
public class ContratoController {

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
        model.addAttribute(contrato);
        model.addAttribute("clientes", clienteService.listAllClientes());
        return "contratoform";
    }
    
    @RequestMapping(value = "contrato/new", params = {"addRow"})
    public String addRow(Model model ,@Valid Contrato contrato, BindingResult bindingResult) {
        MesBonificado mesBonificado = new MesBonificado();
        Calendar calendar = GregorianCalendar.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(calendar.getTime().getYear());
		stringBuilder.append("-");
		stringBuilder.append(calendar.getTime().getMonth());
		mesBonificado.setMes(stringBuilder.toString());
        mesBonificadoService.saveMesBonificado(mesBonificado);
        contrato.getMesesBonificados().add(mesBonificado);
        model.addAttribute("clientes", clienteService.listAllClientes());
        return "contratoform";
    }
    
    @RequestMapping(value = "contrato/new", params = {"removeRow"})
    public String removeRow(@Valid Contrato contrato, HttpServletRequest req, Model model , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "contratoform";
        }
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
    	System.out.println("cliente id => " + contrato.getCliente());
        if (bindingResult.hasErrors()) {
        	model.addAttribute("clientes", clienteService.listAllClientes());
            return "contratoform";
        }
        for (MesBonificado mesBonificado : contrato.getMesesBonificados()) {
            mesBonificadoService.saveMesBonificado(mesBonificado);
        }
        try{
        	contratoService.saveContrato(contrato);
        }catch(DataIntegrityViolationException e ){
        	System.out.println("cliente id => " + contrato.getCliente());
        	ObjectError oError = new ObjectError("cliente", "El cliente ya tiene un contrato");
        	bindingResult.addError(oError);
        	model.addAttribute("clientes", clienteService.listAllClientes());
        	return "contratoform";
        }
        return "redirect:/contrato/" + contrato.getId();
    }
}

