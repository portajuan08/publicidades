package monkey.woodstock.controllers;

import monkey.woodstock.Util.UtilModel;
import monkey.woodstock.services.ChequeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController{
	
    private ChequeService chequeService;

    @Autowired
    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
	
	
    @RequestMapping("/")
    String index(Authentication oAuth){
        return "index";
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
	public String index(Model model){
    	model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
		return "index";
	}
}

