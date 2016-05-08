package monkey.woodstock.controllers;
import java.security.Principal;

import monkey.woodstock.domain.User;
import monkey.woodstock.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
	
	
}