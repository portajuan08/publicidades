package monkey.woodstock.controllers;

import javax.validation.Valid;

import monkey.woodstock.Util.UtilModel;
import monkey.woodstock.domain.Cheque;
import monkey.woodstock.services.BancoService;
import monkey.woodstock.services.ChequeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChequeController {

    private BancoService bancoService;
    private ChequeService chequeService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }

    @Autowired
    public void setBancoService(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @Autowired
    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
    
    @RequestMapping(value = "/cheques", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("cheques", chequeService.listAllCheques());
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "cheques";
    }

    @RequestMapping(value = "cheque/{id}")
    public String showCheque(@PathVariable Integer id, Model model) {
        Cheque cheque = chequeService.getChequeById(id);
        model.addAttribute("cheque", cheque);
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "chequeshow";
    }
    
    @RequestMapping(value = "cheque/{id}", params = {"volver"})
    public String volverCheque() {
        return "redirect:/cheques";
    }

    @RequestMapping(value = "cheque/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("cheque", chequeService.getChequeById(id));
        model.addAttribute("bancos", bancoService.listAllBancos());
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "chequeform";
    }
    
    @RequestMapping(value = "cheque/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
    	chequeService.deleteCheque(chequeService.getChequeById(id));
    	return "redirect:/cheques";
    }

    @RequestMapping(value = "cheque/new")
    public String add(Model model) {
        Cheque cheque = new Cheque();
        cheque.setYaAviso(false);
        model.addAttribute(cheque);
        model.addAttribute("bancos", bancoService.listAllBancos());
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "chequeform";
    }

    @RequestMapping(value = "cheque/new", params = {"cancel"})
    public String cancel(Cheque cheque) {
    	return "redirect:/cheques";
    }
    
    @RequestMapping(value = "cheque/new", method = RequestMethod.POST)
    public String saveCheque(@Valid Cheque cheque, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("bancos", bancoService.listAllBancos());
        	model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
            return "chequeform";
        }
        chequeService.saveCheque(cheque);
        return "redirect:/cheque/" + cheque.getId();
    }
}

