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

    @GetMapping(value = "open_account")
    public String openAccountHandler (Model model){
        return "under_construction";
    }

    @GetMapping("connect_account")
    public String connectAccountHandler (Model model){
        return "under_construction";
    }
}
