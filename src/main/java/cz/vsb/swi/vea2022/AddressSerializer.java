package cz.vsb.swi.vea2022;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import cz.vsb.swi.vea2022.models.Address;

public class AddressSerializer extends StdSerializer<Address> {

	public AddressSerializer() {
		super(Address.class);
	}
	
	public AddressSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	private static final long serialVersionUID = 4879478812503851060L;

	@Override
	public void serialize(Address value, JsonGenerator gen, SerializerProvider provider) throws IOException {
	       	gen.writeStartObject();
	        gen.writeStringField("address", value.getStreet() + " " + value.getCity());
	        gen.writeEndObject();
	}

}
