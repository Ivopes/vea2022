package cz.vsb.swi.vea2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import cz.vsb.swi.vea2022.services.PersonService;

@Controller
public class IndexController {

	@Autowired
	private PersonService personService;
	
	@GetMapping(path = "/")
	public String index( Model model) {
		return "index";
	}
}
