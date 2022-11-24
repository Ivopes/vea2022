package cz.vsb.swi.vea2022.utilities.mappers;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements RowMapper<Address>{

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address address = new Address();
		address.setCity(rs.getString("city"));
		address.setStreet(rs.getString("street"));
		return address;
	}
}
