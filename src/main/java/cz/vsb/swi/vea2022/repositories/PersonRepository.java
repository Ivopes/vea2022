package cz.vsb.swi.vea2022.repositories;

import java.util.List;

import cz.vsb.swi.vea2022.models.Person;

public interface PersonRepository {

	List<Person> getAll();

	Person findById(long id);

	void insert(Person person);

}