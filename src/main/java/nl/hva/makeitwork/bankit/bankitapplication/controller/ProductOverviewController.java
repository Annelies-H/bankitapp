package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("customer")
public class ProductOverviewController {
    @Autowired
    private CustomerService customerService;

 public ProductOverviewController(){super();}

    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler (Model model){
     return "under_construction";
    }

    @GetMapping("open_account")
    public String openAccountHandler (Model model){
        return "under_construction";
    }

    @GetMapping("connect_account")
    public String connectAccountHandler (Model model){
        return "under_construction";
    }
}
