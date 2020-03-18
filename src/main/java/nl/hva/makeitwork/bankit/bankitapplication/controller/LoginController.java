package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;
import nl.hva.makeitwork.bankit.bankitapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("customer")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    LoginService loginService;

    public LoginController() {super();}

    @PostMapping("customer_login")
    public String loginHandler(
            @RequestParam(name = "user_name") String username,
            @RequestParam(name = "user_password") String password,
            Model model) {
        Customer customer = customerService.findCustomer(username);
        if ( customer != null && loginService.passwordCheck(password, customer) ) {
            model.addAttribute("customer", customer);
            return "product_overview";
        } else {
            return "login_failed";
        }

        /*String nextPage = "login_failed";    //changed: always redirect unless correct combination username/password
        Customer customer = customerService.findCustomer(username);
        if (customer != null) {
            if (customer.getPassword().equals(password)) {
                model.addAttribute("customer", customer);
                nextPage = "product_overview";
            }
        }
        return nextPage;*/
    }

}