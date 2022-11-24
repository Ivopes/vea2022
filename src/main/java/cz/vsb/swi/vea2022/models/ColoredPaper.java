package cz.vsb.swi.vea2022.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Random;

@Entity
@DiscriminatorValue("2")
public class ColoredPaper extends Product{
    private String color;

    public ColoredPaper(){}
    @Override
    public String displayName(){
        return getName() + "  Color:" + getColor();
    }
    @Override
    public void randomValues(){
        super.randomValues();
        if (new Random().nextInt(0, 2) == 0)
            setColor("red");
        else
            setColor("blue");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
