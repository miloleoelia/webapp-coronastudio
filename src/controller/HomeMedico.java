package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeMedico {

	@RequestMapping("/")
	public String home() {
		return "home-medico";
	}
	
}
