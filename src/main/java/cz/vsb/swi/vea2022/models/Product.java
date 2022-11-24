package cz.vsb.swi.vea2022.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;
    @ManyToMany(mappedBy = "products")
    protected List<Order> orders = new ArrayList<>();
    protected String name;

    public Product(){}

    /*public Product(String id){
        this.id = Integer.parseInt(id);
    }*/
    public Product(long id){
        this.id = id;
    }
    public Product(String name, List<Order> orders){
        this.name = name;
        this.orders = orders;
    }
    public int getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return Integer.parseInt(val.value());
    }
    public String displayName(){
        return getName();
    }
    public void randomValues(){
        //var  p = new Product();
        var i = new Random().nextInt();
        name = "Product"+i;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void addOrders(Order order) {
        this.orders.add(order);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
