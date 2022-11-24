package cz.vsb.swi.vea2022.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Person;

public class PersonMapper implements RowMapper<Person>{

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getLong("id"));
		person.setFirstName(rs.getString("name"));
		person.setLastName(rs.getString("surname"));
		Address address = new Address();
		address.setCity(rs.getString("city"));
		address.setStreet(rs.getString("street"));
		person.setAddress(address);
		return person;
	}

}
