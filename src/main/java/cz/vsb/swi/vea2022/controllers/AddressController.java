package cz.vsb.swi.vea2022.controllers;

import cz.vsb.swi.vea2022.models.Address;
import cz.vsb.swi.vea2022.models.Order;
import cz.vsb.swi.vea2022.models.Person;
import cz.vsb.swi.vea2022.models.Product;
import cz.vsb.swi.vea2022.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private EntityService<Address> addressService;
    @Autowired
    private EntityService<Person> personService;

    @GetMapping(path = "/address")
    public String index( Model model) {
        List<Address> address =  addressService.getAll();
        model.addAttribute("address", address);
        return "addressList";
    }
}
