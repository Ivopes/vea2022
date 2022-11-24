package cz.vsb.swi.vea2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.services.PersonService;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping(path = "/person")
	public String index( Model model) {
		model.addAttribute("greeting", "Cau");
		model.addAttribute("persons", personService.getAll());
		return "personList";
	}

	@GetMapping(path="/editPerson/{id}")
	public String editPerson(@PathVariable int id, Model model) {
		model.addAttribute("person", personService.findById(id));
		return "newPerson"; 
	}
	@GetMapping(path="/editNewPerson")
	public String newPerson( Model model) {
		model.addAttribute("person", new Person());
		return "newPerson"; 
	}
	@GetMapping(path="/deletePerson/{id}")
	public RedirectView deletePerson(@PathVariable long id, Model model) {
		personService.delete(id);
		return new RedirectView("/person");
	}
	@PostMapping(path="/insertPerson")
	public RedirectView addPerson(@ModelAttribute Person newPerson, Model model) {
		personService.insert(newPerson);
		model.addAttribute("persons", personService.getAll());
		return new RedirectView("/person");
	}
}
