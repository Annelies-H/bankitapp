package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;
import nl.hva.makeitwork.bankit.bankitapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@SessionAttributes("customer")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    LoginService loginService;

    public LoginController() {
        super();
    }

    @PostMapping("customer_login")
    public String loginHandler(
            @RequestParam(name = "user_name") String username,
            @RequestParam(name = "user_password") String password,
            Model model) {
        Customer customer = customerService.findCustomer(username);
        if (customer != null && loginService.passwordCheck(password, customer)) {
            model.addAttribute("customer", customer);
            return "product_overview";
        } else {
            return "login_failed";
        }

    }

    @GetMapping("logout")
    public String logoutHandler(Model model, WebRequest webRequest, SessionStatus sessionStatus) {
        Customer customer = (Customer)model.getAttribute("customer");
        if (customer != null) {
            sessionStatus.setComplete();
            webRequest.removeAttribute("customer", WebRequest.SCOPE_REQUEST);
        }
        return "redirect:/";
    }

}