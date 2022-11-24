package cz.vsb.swi.vea2022.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.Random;

@Entity
@DiscriminatorValue("1")
public class Chips extends Product{
    private int quantity;

    public Chips(){}
    public Chips(String name, int quantity, List<Order> orders){
        super(name, orders);
        setQuantity(quantity);
    }
    @Override
    public String displayName(){
        return getQuantity()+ "x "+ getName();
    }

    @Override
    public void randomValues(){
        super.randomValues();
        quantity = new Random().nextInt(0, 100);
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
