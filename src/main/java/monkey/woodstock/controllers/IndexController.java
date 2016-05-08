package monkey.woodstock.controllers;

import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{
	
    @RequestMapping("/")
    String index(Authentication oAuth){
        return "index";
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView index(Locale locale){
		return new ModelAndView("index");
	}
}

