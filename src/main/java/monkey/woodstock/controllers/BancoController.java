package monkey.woodstock.controllers;

import java.util.List;

import javax.validation.Valid;

import monkey.woodstock.domain.Banco;
import monkey.woodstock.domain.Cheque;
import monkey.woodstock.services.BancoService;
import monkey.woodstock.services.ChequeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BancoController {

    private BancoService bancoService;
    private ChequeService chequeService;

    @Autowired
    public void setBancoService(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @Autowired
    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
    
    @RequestMapping(value = "/bancos", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("bancos", bancoService.listAllBancos());
        return "bancos";
    }

    @RequestMapping(value = "banco/{id}")
    public String showBanco(@PathVariable Integer id, Model model) {
        Banco banco = bancoService.getBancoById(id);
        model.addAttribute("banco", banco);
        return "bancoshow";
    }
    
    @RequestMapping(value = "banco/{id}", params = {"volver"})
    public String volverBanco() {
        return "redirect:/bancos";
    }

    @RequestMapping(value = "banco/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("banco", bancoService.getBancoById(id));
        return "bancoform";
    }
    
    @RequestMapping(value = "banco/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
    	List<Cheque> cheques = chequeService.getChequesByBanco(id);
    	if (cheques != null && cheques.isEmpty())
    		for (Cheque cheque : cheques)
    			chequeService.deleteCheque(cheque);
    	bancoService.deleteBanco(bancoService.getBancoById(id));
    	return "redirect:/bancos";
    }

    @RequestMapping(value = "banco/new")
    public String add(Model model) {
        Banco banco = new Banco();
        model.addAttribute(banco);
        return "bancoform";
    }

    @RequestMapping(value = "banco/new", params = {"cancel"})
    public String cancel(Banco bacno) {
    	return "redirect:/bancos";
    }
    
    @RequestMapping(value = "banco/new", method = RequestMethod.POST)
    public String saveBanco(@Valid Banco banco, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "bancoform";
        }
        bancoService.saveBanco(banco);
        return "redirect:/banco/" + banco.getId();
    }
}

