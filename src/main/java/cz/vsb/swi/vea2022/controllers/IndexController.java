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

	@GetMapping(path = "im1")
	public String testIdentityMap1() {
		personService.testIdentityMap1();
		return "index";
	}

	@GetMapping(path = "im2")
	public String testIdentityMap2() {
		personService.testIdentityMap2();
		return "index";
	}

	@GetMapping(path = "uow1")
	public RedirectView testUnitOfWork1() {
		personService.testUnitOfWork1();
		return new RedirectView("list");
	}

	@GetMapping(path = "uow2")
	public RedirectView testUnitOfWork2() {
		personService.testUnitOfWork2();
		return new RedirectView("list");
	}

	@GetMapping(path = "uow3")
	public RedirectView testUnitOfWork3() {
		personService.testUnitOfWork3();
		return new RedirectView("list");
	}

	@GetMapping(path = "uow4")
	public RedirectView testUnitOfWork4() {
		personService.testUnitOfWork4();
		return new RedirectView("list");
	}

	
}
