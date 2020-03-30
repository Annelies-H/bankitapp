package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("customer")
@RequestMapping(value = "account")

public class BankAccountController {

    @Autowired
    private CustomerService cus;
    @Autowired
    private BankAccountService bas;
    @Autowired
    private CompanyDAO cdao;
    @Autowired
    private CustomerService cs;

    @GetMapping("selected_bankaccount")
    public String bankaccountOverviewHandler(@RequestParam int id, Model model) {
        bas.addAccountToModelByIdIncludingTenTransactions(id, model);
        return "account_overview";
    }

    @GetMapping(value = "new/private")
    public String newPrivateAccountHandler(Model model) {
        model.addAttribute("accounttype", "priverekening");
        return "confirm_new_private_account";
    }

    @PostMapping(value = "new/private/confirmed")
    public String confirmPrivateAccountHandler(Model model) {
        Customer customer = (Customer) model.getAttribute("customer");
        PrivateAccount account = bas.newPrivateAccount(customer);
        cs.addBankAccount(customer, account);
        model.addAttribute("account", account);
        model.addAttribute("accounttype", "priverekening");
        return "new_account_confirmed";
    }

    @GetMapping(value = "new/business")
    public String newBusinessAccountHandler(Model model) {
       model.addAttribute("company", new Company());
        return "new_business_account";
    }

    @PostMapping(value = "new/save_company")
    public String saveCompanyHandler(Model model, @ModelAttribute("company") Company newCompany) {
        Customer customer = (Customer) model.getAttribute("customer");
        cdao.save(newCompany);
        System.out.println("ik ben ben opgeslagen" + newCompany);
        BusinessAccount account = bas.newBusinessAccount(customer, newCompany);
        cs.addBankAccount(customer, account);
        model.addAttribute("account", account);
        model.addAttribute("accounttype", "bedrijfsrekening");
        return "new_account_confirmed";
    }

    @GetMapping("connect_account")
    public String connectAccountHandler(Model model) {
        return "add_accountholder";
    }

    @GetMapping("overview")
    public String accountOverviewHandler(Model model) {
        Customer customer = (Customer)model.getAttribute("customer");
        if (customer == null) {
            return "redirect:/intranet/dashboard";
        }
        // update customer info
        customer = cus.findCustomer(customer.getUsername());
        model.addAttribute("customer", customer);
        return "product_overview";
    }
}
