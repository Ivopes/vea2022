package cz.vsb.swi.vea2022.utilities.converter;

import cz.vsb.swi.vea2022.models.Chips;
import cz.vsb.swi.vea2022.models.ColoredPaper;
import cz.vsb.swi.vea2022.models.Product;
import org.springframework.core.convert.converter.Converter;

public class ProductConverter implements Converter<String, Product> {

    @Override
    public Product convert(String source) {
        //return new Product(Long.parseLong(source));
        var splits = source.split(",");
        Product p = null;
        for (var s : splits) {
            if (s.startsWith("Dis=")) {
                int value = Integer.parseInt(s.substring(4));
                if (value == 1) {
                    p = new Chips();
                } else {
                    p = new ColoredPaper();
                }
            }
        }
        for (var s : splits) {
            if (s.startsWith("Id=")) {
                p.setId(Long.parseLong(s.substring(3)));
            }
        }
        return p;
    }
}
