package cz.vsb.swi.vea2022.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.services.PersonService;

@RestController
public class RestApiController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/rest/persons")
	public List<Person> getAll(){
		return personService.getAll();
	}
}
