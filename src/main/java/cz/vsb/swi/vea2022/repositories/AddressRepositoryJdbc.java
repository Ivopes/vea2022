package cz.vsb.swi.vea2022.repositories;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.utilities.mappers.AddressMapper;
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
public class AddressRepositoryJdbc implements EntityRepository<Address> {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert addressInsert;

	@PostConstruct
	public void init() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		addressInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("Address")
				.usingGeneratedKeyColumns("id")
				.usingColumns("city", "street");
		try {
			Connection con = dataSource.getConnection();
			try(Statement stm = con.createStatement()){
				stm.execute(
						"CREATE TABLE Address ("
						+ "id INTEGER NOT NULL auto_increment, "
						+ "street varchar(255), "
						+ "city varchar(255), "
						+ "PRIMARY KEY (id));"
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AddressRepositoryJdbc() {
	}

	@Override
	public List<Address> getAll() {
		return jdbcTemplate.query("select * from Address", new AddressMapper());
	}

	@Override
	public Address findById(long id) {
		return jdbcTemplate.queryForObject(
				"select * from Address where id=?",
				new AddressMapper(), id);
	}
	
	@Override
	public void insert(Address address) {
		if (address.getId() == 0) {
			addressInsert.execute(new BeanPropertySqlParameterSource(address));
		} else {
			jdbcTemplate.update("update Address "
					+ "set city=?,"
					+ "set street=?"
					+ "where id=?;",
					address.getCity(), address.getStreet(),
					address.getId());
		}
	}

	@Override
	public void delete(long id) {

	}
}
