package cz.vsb.swi.vea2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.services.PersonService;

@RestController
@RequestMapping("api")
public class PersonRestController {
	
	@Autowired
	private PersonService personService;

	@GetMapping(path = "persons")
	public List<Person> getAllPersons() {
		return personService.getAll();
	}

}
