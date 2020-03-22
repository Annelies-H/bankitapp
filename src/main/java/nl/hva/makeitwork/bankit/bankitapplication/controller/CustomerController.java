package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/customer")
@Controller
@SessionAttributes("customer")
public class CustomerController {

    @Autowired
    CustomerDAO customerDAO;

    @GetMapping("/signup")
    public String signupHandler(Model theModel) {

        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "create_customer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the employee
        customerDAO.save(theCustomer);

        // use a redirect to prevent duplicate submissions
        return "redirect:/login";
    }
}

