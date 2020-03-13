package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("login")
    public String showlogonHandler() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupHandler(Model theModel) {

        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "create_customer";
    }

    @GetMapping("about_us")
    public String aboutusHandler() {
        return "under_construction";
    }
}
