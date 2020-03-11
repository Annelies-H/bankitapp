package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Controller
@SessionAttributes("customer")
@RequestMapping (value = "account")

public class BankAccountController {

    @Autowired
    private BankAccountService bas;

    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler (Model model){
        return "under_construction";
    }

    @GetMapping(value = "new")
    public String newAccountHandler (Model model){
        return "new_account";
    }

    @GetMapping(value = "new/private")
    public String newPrivateAccountHandler (Model model){
        Customer customer = (Customer) model.getAttribute("customer");
        bas.newPrivateAccount(customer);
        return "under_construction";
    }

    @GetMapping(value = "new/business")
    public String newBusinessAccountHandler (Model model){
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
