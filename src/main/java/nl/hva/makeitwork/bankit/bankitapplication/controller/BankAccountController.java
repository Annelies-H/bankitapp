package nl.hva.makeitwork.bankit.bankitapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Controller
@SessionAttributes("customer")
@RequestMapping (value = "account")

public class BankAccountController {

    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler (Model model){
        return "under_construction";
    }

    @GetMapping(value = "new")
    public String openAccountHandler (Model model){
        return "new_account";
    }

    @GetMapping(value = "new/private")
    public String openPrivateAccountHandler (Model model){
        return "under_construction";
    }

    @GetMapping(value = "new/business")
    public String openBusinessAccountHandler (Model model){
        return "under_construction";
    }

    @GetMapping("connect_account")
    public String connectAccountHandler (Model model){
        return "under_construction";
    }

    @GetMapping("overview")
    public String accountOverviewHandler (Model model) {
        return "product_overview";
    }
}
