package cz.vsb.swi.vea2022.services;

import java.util.List;

import cz.vsb.swi.vea2022.repositories.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.repositories.PersonRepositoryJpa;

@Service
public class PersonService implements EntityService<Person>{

	@Autowired
	private EntityRepository<Person> personRepository;

	public List<Person> getAll() {
		return personRepository.getAll();
	}

	public void insert(Person person) {
		personRepository.insert(person);
	}

	public Person findById(long id) {
		return personRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		personRepository.delete(id);
	}
}
