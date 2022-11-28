package cz.vsb.swi.vea2022.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Product> products;
    @ManyToOne
    private Person person = new Person();

    public Order(){}

    public Order(Person person, List<Product> products) {
        this.person = person;
        this.products = products;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @JsonIgnore
    public List<Product> getProducts() {
        return products;
    }
    public List<Long> getProductsId() {
        return products.stream().map(p -> p.getId()).toList();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
