package cz.vsb.swi.vea2022.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.repositories.PersonRepositoryJpa;

@Service
public class PersonService implements EntityService<Person>{

	@Autowired
	private PersonRepositoryJpa personRepository;

	public List<Person> getAll() {
		return personRepository.getAll();
	}

	public void insert(Person person) {
		personRepository.insert(person);
	}

	public Person findById(long id) {
		return personRepository.findById(id);
	}

	public void testIdentityMap1() {
		personRepository.testIdentityMap1();
	}

	public void testIdentityMap2() {
		personRepository.testIdentityMap2();
	}

	public void testUnitOfWork1() {
		personRepository.testUnitOfWork1();
	}
	public void testUnitOfWork2() {
		personRepository.testUnitOfWork2();
	}

	public void testUnitOfWork3() {
		personRepository.testUnitOfWork3();
	}

	public void testUnitOfWork4() {
		personRepository.testUnitOfWork4();
	}
	
	

	
}
