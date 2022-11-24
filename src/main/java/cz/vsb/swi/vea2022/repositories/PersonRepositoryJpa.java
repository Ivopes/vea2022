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
public class PersonRepositoryJpa implements PersonRepository {

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
	public Person findById(long id) {
		return em.find(Person.class, id);
	}

	@Override
	@Transactional
	public void insert(Person person) {
		if (person.getId() == 0) {
			var addresses = em.createQuery("select a from Address a where a.city like :cityName and a.street like :streetName", Address.class)
							.setParameter("cityName", person.getCity())
							.setParameter("streetName", person.getStreet())
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

	@Transactional
	public void testIdentityMap1() {
		Person p = new Person("a", "b", null);
		p.setId(1);
		Person p2 = em.merge(p);
		Person db = em.find(Person.class, 1l);
		System.out.println("db=" + db + " p=" + p + " p2=" + p2);
	}

	@Transactional
	public void testIdentityMap2() {
		Person old = em.find(Person.class, 1l);
		Person p = new Person("a", "b", null);
		p.setId(1);
		Person p2 = em.merge(p);
		System.out.println("old=" + old + " p=" + p + " p2=" + p2);
	}

	@Transactional
	public void testUnitOfWork1() {
		Person old = em.find(Person.class, 1l);
		old.setFirstName("11-" + old.getFirstName());
	}

	@Transactional
	public void testUnitOfWork2() {
		Person old = em.find(Person.class, 1l);
		old.hidenChange("22-" + old.getFirstName());
	}

	@Transactional
	public void testUnitOfWork3() {
		Person old = em.find(Person.class, 1l);
		old.hidenChange2("33-" + old.getFirstName()+"-".repeat(1000));
	}
	@Transactional
	public void testUnitOfWork4() {
		Person old = em.find(Person.class, 1l);
		old.hidenChange3("44-" + old.getFirstName());
	}
}