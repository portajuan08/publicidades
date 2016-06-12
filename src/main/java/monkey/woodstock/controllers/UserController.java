package monkey.woodstock.controllers;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import monkey.woodstock.Util.UtilModel;
import monkey.woodstock.domain.CambiarContraseña;
import monkey.woodstock.domain.User;
import monkey.woodstock.services.ChequeService;
import monkey.woodstock.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class UserController {

    private UserService userService;
    private ChequeService chequeService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    public void setChequeService(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @RequestMapping("/cambiarPass")
	public String cambiasPass( HttpServletRequest request, CambiarContraseña cambiarContraseña) {
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
	}
    
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@RequestMapping("/user/showUser")
	public String show(Principal user, Authentication oAuthen, Model model) {
		User oUser = userService.getUserByName(oAuthen.getName());
        model.addAttribute("user", oUser);
        return "userform";
	}
	
    @RequestMapping(value = "/user/cambiarMail")
    public String showConfigMail(Model model, Authentication oAuth) {
		User oUser = userService.getUserByName(oAuth.getName());
        model.addAttribute("user", oUser);
        model.addAllAttributes(UtilModel.getModalsGenerales(chequeService));
        return "cambiarMailForm";
    }
    
    @RequestMapping(value = "/user/cambiarMail", method = RequestMethod.POST)
    public String configMail(User user, Model model, HttpServletRequest request)  {
    	String sMail = user.getMail();
        user = userService.getUserByName(user.getName());
        user.setMail(sMail);
        userService.saveUser(user);
        return "redirect:/";
    }
	
}