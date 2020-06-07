package nl.hva.makeitwork.bankit.bankitapplication.controller;



import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateBankAccountsService;
import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateCompaniesService;
import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateCustomersService;
import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestDataController {

  @Autowired
  private GenerateCustomersService generateCustomersService;

  @Autowired
  private GenerateCompaniesService generateCompaniesService;
  @Autowired
  private GenerateBankAccountsService generateBankAccountsService;
  @Autowired
  private GenerateTransactionsService generateTransactionsService;

  public TestDataController() {
    super();
  }

  @GetMapping("create_users")
  public String createUsersHandler() {
    generateCustomersService.createUsers();
    return "under_construction";
  }

  @GetMapping("create_companies")
  public String createCompaniesHandler() {
    generateCompaniesService.createCompanies();
    return "under_construction";
  }

  @GetMapping("create_businessAccounts")
  public String createBusinessAccounts() {
    generateBankAccountsService.createBusinessAccounts();
    return "under_construction";
  }

  @GetMapping("create_privateAccounts")
  public String createPrivateAccounts() {
    generateBankAccountsService.createPrivateAccounts();
    return "under_construction";
  }

  @GetMapping("create_business_transactions")
  public String createBusinessTransactions() {
    generateTransactionsService.createTransactionsForBusinessAccounts();
    return "under_construction";
  }


}
