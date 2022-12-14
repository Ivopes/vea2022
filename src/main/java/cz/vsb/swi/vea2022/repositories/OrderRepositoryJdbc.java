package cz.vsb.swi.vea2022.repositories;

import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.utilities.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//@Repository
public class OrderRepositoryJdbc implements EntityRepository<Order> {

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

	public OrderRepositoryJdbc() {
	}

	@Override
	public List<Order> getAll() {
		return null;
		/*return jdbcTemplate.query("select * from Person", new PersonMapper());*/
	}

	@Override
	public Order findById(long id) {
	/*	return jdbcTemplate.queryForObject(
				"select * from Order where id=?",
				new PersonMapper(), id);*/
		return null;
	}
	
	@Override
	public void insert(Order order) {
		if (order.getId() == 0) {
			personInsert.execute(new BeanPropertySqlParameterSource(order));
		}
	}

	@Override
	public void delete(long id) {

	}
}
