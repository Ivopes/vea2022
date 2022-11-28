package cz.vsb.swi.vea2022.repositories;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Person;

@Repository
public class PersonRepositoryJpa implements EntityRepository<Person> {

	@PersistenceContext
	private EntityManager em;
	private static boolean loaded = false;

	@Override
	@Transactional
	public List<Person> getAll() {
		if (!loaded) {
			var p = Person.getRandomPerson();
			insert(p);
			p = Person.getRandomPerson();
			insert(p);
			p = Person.getRandomPerson();
			insert(p);
			p = Person.getRandomPerson();
			insert(p);
			loaded = true;
		}
		return em.createQuery("select p from Person p", Person.class).getResultList();
	}

	@Override
	@Transactional
	public Person findById(long id) {
		return em.find(Person.class, id);
	}

	@Override
	@Transactional
	public void insert(Person person) {
		if (person.getId() == 0) {
			var addresses = em.createQuery("select a from Address a where (a.city like :cityName and a.street like :streetName) or a.id = :addressId", Address.class)
							.setParameter("cityName", person.getAddress().getCity())
							.setParameter("streetName", person.getAddress().getStreet())
							.setParameter("addressId", person.getAddress().getId())
							.getResultList();
			if(addresses.size() == 1) person.setAddress(addresses.get(0));

			em.persist(person.getAddress());
			em.persist(person);
		} else {
			Address a = em.merge(person.getAddress());
			person.setAddress(a);
			em.merge(person);
			//person.setLastName(person.getLastName() + "aaaa");
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		var o = findById(id);
		em.remove(o);
	}

}
