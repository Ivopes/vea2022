package cz.vsb.swi.vea2022.controllers;

import cz.vsb.swi.vea2022.models.Chips;
import cz.vsb.swi.vea2022.models.ColoredPaper;
import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.models.Product;
import cz.vsb.swi.vea2022.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/product")
    public String index( Model model) {
        List<Product> products =  productService.getAll();
        model.addAttribute("products",products);
        return "productList";
    }
    @GetMapping(path="/editNewChips")
    public String newChips( Model model) {
        model.addAttribute("product", new Chips());
        return "newChips";
    }
    @GetMapping(path="/editNewPaper")
    public String newPaper( Model model) {
        model.addAttribute("product", new ColoredPaper());
        return "newPaper";
    }
    @PostMapping(path="/insertProduct")
    public String addPerson(@ModelAttribute Product newProduct, Model model) {
        productService.insert(newProduct);
        model.addAttribute("products", productService.getAll());
        return "productList";
    }
}
