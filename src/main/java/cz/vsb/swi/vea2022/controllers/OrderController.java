package cz.vsb.swi.vea2022.controllers;

import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.models.Product;
import cz.vsb.swi.vea2022.services.EntityService;
import cz.vsb.swi.vea2022.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private EntityService<Order> orderService;
    @Autowired
    private EntityService<Person> personService;
    @Autowired
    private EntityService<Product> productService;

    @GetMapping(path = "/order")
    public String index( Model model) {
        List<Order> orders =  orderService.getAll();
        model.addAttribute("orders", orders);
        return "orderList";
    }
    @GetMapping(path="/editNewOrder")
    public String newOrder( Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("persons", personService.getAll());
        model.addAttribute("products", productService.getAll());
        return "newOrder";
    }
    @GetMapping(path="/deleteOrder/{id}")
    public RedirectView deleteOrder(@PathVariable long id, Model model) {
        orderService.delete(id);
        return new RedirectView("/order");
    }
    @PostMapping(path="/insertOrder")
    public RedirectView  addPerson(@ModelAttribute Order newOrder, @ModelAttribute String products/*, @ModelAttribute List<Product> products*/, Model model) {
        if (newOrder.getProducts() == null || newOrder.getProducts().isEmpty()) return new RedirectView("/editNewOrder");

        //newOrder.getProducts().forEach(p -> {p.setId(Integer.parseInt(p.getName()));});

        orderService.insert(newOrder);
        model.addAttribute("orders", orderService.getAll());
        return new RedirectView("/order");
    }
}
