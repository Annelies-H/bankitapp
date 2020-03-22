package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("customer")
@RequestMapping(value = "account")

public class BankAccountController {

    @Autowired
    private BankAccountService bas;
    @Autowired
    private BusinessAccountDAO bdao;
    @Autowired
    private PrivateAccountDAO pdao;
    @Autowired
    private CompanyDAO cdao;
    @Autowired
    private TransactionDAO tdao;

    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler(@RequestParam int id, Model model) {
        PrivateAccount pAccount = bas.findPrivateAccountByID(id);
        BusinessAccount bAccount = bas.findBusinessAccountByID(id);

        if (pAccount != null) {
            model.addAttribute("account", pAccount);
            model.addAttribute("company", "null");
        } else {
            model.addAttribute("account", bAccount);
        }

        return "account_overview";
    }

    @GetMapping(value = "new")
    public String newAccountHandler(Model model) {
        return "new_account";
    }

    @PostMapping(value = "new/private")
    public String newPrivateAccountHandler(Model model) {
        Customer customer = (Customer) model.getAttribute("customer");
        PrivateAccount account = bas.newPrivateAccount(customer);
        model.addAttribute("account", account);
        model.addAttribute("accounttype", "priverekening");
        return "confirm_new_account";
    }

    @GetMapping(value = "new/business")
    public String newBusinessAccountHandler(Model model) {
        Company company = new Company();
        model.addAttribute("company", new Company());
        return "new_business_account";
    }

    @PostMapping(value = "new/save_company")
    public String saveCompanyHandler(Model model, @ModelAttribute("company") Company newCompany, @ModelAttribute("customer") Customer customer) {
        cdao.save(newCompany);
        BusinessAccount account = bas.newBusinessAccount(customer, newCompany);
        model.addAttribute("account", account);
        model.addAttribute("accounttype", "bedrijfsrekening");
        return "confirm_new_account";
    }

    @GetMapping("connect_account")
    public String connectAccountHandler(Model model) {
        return "add_accountholder";
    }

    @GetMapping("overview")
    public String accountOverviewHandler(Model model) {
        return "product_overview";
    }
}
