package cz.vsb.swi.vea2022.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import cz.vsb.swi.vea2022.utilities.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import cz.vsb.swi.vea2022.models.Person;
import org.springframework.stereotype.Repository;

//@Repository
public class PersonRepositoryJdbc implements EntityRepository<Person> {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert personInsert;
	
	@PostConstruct
	public void init() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		personInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("Person")
				.usingGeneratedKeyColumns("id")
				.usingColumns("name", "surname", "city", "street");
		try {
			Connection con = dataSource.getConnection();
			try(Statement stm = con.createStatement()){
				stm.execute(
						"CREATE TABLE Person ("
						+ "id INTEGER NOT NULL auto_increment, "
						+ "name varchar(255), "
						+ "surname varchar(255), "
						+ "street varchar(255), "
						+ "city varchar(255), "
						+ "PRIMARY KEY (id));"
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PersonRepositoryJdbc() {
	}

	@Override
	public List<Person> getAll() {
		return jdbcTemplate.query("select * from Person", new PersonMapper());
	}

	@Override
	public Person findById(long id) {
		return jdbcTemplate.queryForObject(
				"select * from Person where id=?", 
				new PersonMapper(), id);
	}
	
	@Override
	public void insert(Person person) {
		if (person.getId() == 0) {
			personInsert.execute(new BeanPropertySqlParameterSource(person));
		} else {
			jdbcTemplate.update("update Person "
					+ "set name=?,"
					+ "set surname=?,"
					+ "set city=?,"
					+ "set street=?"
					+ "where id=?;", 
					person.getFirstName(), person.getLastName(),
					person.getAddress().getCity(), person.getAddress().getStreet(),
					person.getId());
		}
	}

	@Override
	public void delete(long id) {

	}
}
