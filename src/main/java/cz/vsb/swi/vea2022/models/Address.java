package cz.vsb.swi.vea2022.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cz.vsb.swi.vea2022.AddressSerializer;

@Entity
//@JsonSerialize(using = AddressSerializer.class)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String street;
	private String city;
	
	@OneToMany(mappedBy = "address")
	private List<Person> persons;

	public Address() {
		super();
	}

	public Address(String street, String city) {
		super();
		this.street = street;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonIgnore
	public List<Person> getPersons() {
		return persons;
	}
	public List<Long> getPersonsId() {
		return persons.stream().map(p -> p.getId()).toList();
	}
	public void setPersonsId(long[] ids) {
		persons = new ArrayList<>();
		for (int i =0; i < ids.length; i++) {
			var p = new Person();
			p.setId(ids[i]);
			persons.add(p);
		}
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
