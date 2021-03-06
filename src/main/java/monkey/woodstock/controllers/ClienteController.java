package monkey.woodstock.controllers;

import javax.validation.Valid;

import monkey.woodstock.Util.UtilModel;
import monkey.woodstock.domain.Cliente;
import monkey.woodstock.domain.Contrato;
import monkey.woodstock.services.ChequeService;
import monkey.woodstock.services.ClienteService;
import monkey.woodstock.services.ContratoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {

    private ClienteService clienteService;
    private ContratoService contratoService;
    private ChequeService chequeService;
    
    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Autowired
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }
    
    @Autowired
    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
    
    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("clientes", clienteService.listAllClientes());
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "clientes";
    }

    @RequestMapping(value = "cliente/{id}")
    public String showCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "clienteshow";
    }
    
    @RequestMapping(value = "cliente/{id}", params = {"volver"})
    public String volverCliente() {
        return "redirect:/clientes";
    }

    @RequestMapping(value = "cliente/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.getClienteById(id));
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "clienteform";
    }
    
    @RequestMapping(value = "cliente/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
    	Contrato contrato = contratoService.getContratoByCliente(id);
    	if (contrato != null)
    		contratoService.deleteContrato(contrato);
    	clienteService.deleteCliente(clienteService.getClienteById(id));
    	return "redirect:/clientes";
    }

    @RequestMapping(value = "cliente/new")
    public String add(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute(cliente);
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "clienteform";
    }

    @RequestMapping(value = "cliente/new", params = {"cancel"})
    public String cancel(Cliente cliente) {
    	return "redirect:/clientes";
    }
    
    @RequestMapping(value = "cliente/new", method = RequestMethod.POST)
    public String saveCliente(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
            return "clienteform";
        }
        clienteService.saveCliente(cliente);
        return "redirect:/cliente/" + cliente.getId();
    }
}

