package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("customer")
@RequestMapping (value = "account")

public class BankAccountController {

    @Autowired
    private BankAccountService bas;
    @Autowired
    private CompanyDAO cdao;

    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler (Model model){
        return "under_construction";
    }

    @GetMapping(value = "new")
    public String newAccountHandler (Model model){
        return "new_account";
    }

    @PostMapping(value = "new/private")
    public String newPrivateAccountHandler (Model model){
        Customer customer = (Customer) model.getAttribute("customer");
        PrivateAccount account = bas.newPrivateAccount(customer);
        model.addAttribute("account", account);
        model.addAttribute("accounttype", "priverekening");
        return "confirm_new_account";
    }

    @GetMapping(value = "new/business")
    public String newBusinessAccountHandler (Model model){
        Company company = new Company();
        model.addAttribute("company", new Company());
        return "new_business_account";
    }

    @PostMapping(value = "/save_company")
    public String saveCompanyHandler(@ModelAttribute("company") Company newCompany) {
        //cdao.save(newCompany);
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
