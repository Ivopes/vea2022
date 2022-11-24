package cz.vsb.swi.vea2022.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;

	@ManyToOne
	private Address address = new Address();
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Order> orders;

	public Person() {
		super();
	}
	public Person(Person p) {
		this.address = p.address;
		this.firstName = p.firstName;
		this.lastName = p.lastName;
	}
	public Person(String firstName, String lastName, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	public static Person getRandomPerson(){
		var p = new Person();
		var i = new Random().nextInt();
		p.lastName = "LastName" + i;
		p.firstName = "FirstName"+ i;
		var a = new Address();
		a.setCity("Ostrava"+i);
		a.setStreet("Krasna"+i);
		p.address = a;
		return p;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreet() {
		return address.getStreet();
	}
	public String getCity() {
		return address.getCity();
	}
}
