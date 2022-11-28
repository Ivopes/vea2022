package cz.vsb.swi.vea2022.controllers;

import java.util.List;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Product;
import cz.vsb.swi.vea2022.services.AddressService;
import cz.vsb.swi.vea2022.services.OrderService;
import cz.vsb.swi.vea2022.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.services.PersonService;

@RestController
public class RestApiController {

	@Autowired
	private PersonService personService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@GetMapping("/api/person")
	public List<Person> getAllPerson(){
		return personService.getAll();
	}
	@GetMapping("/api/person/{id}")
	public Person getPerson(@PathVariable long id){
		return personService.findById(id);
	}
	@PostMapping("/api/person")
	public Person postPerson(@RequestBody Person person) {
		personService.insert(person);
		return person;
	}
	@DeleteMapping("/api/person/{id}")
	public void postPerson(@PathVariable long id) {
		personService.delete(id);
	}
	@GetMapping("/api/address")
	public List<Address> getAllAddress(){
		return addressService.getAll();
	}
	@GetMapping("/api/address/{id}")
	public Address getAllAddress(@PathVariable long id){
		return addressService.findById(id);
	}
	@GetMapping("/api/order")
	public List<Order> getAllOrder(){
		return orderService.getAll();
	}
	@GetMapping("/api/order/{id}")
	public Order getAllOrder(@PathVariable long id){
		return orderService.findById(id);
	}
	@GetMapping("/api/product")
	public List<Product> getAllProduct(){
		return productService.getAll();
	}
}
