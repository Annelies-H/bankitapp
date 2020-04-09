package nl.hva.makeitwork.bankit.bankitapplication.controller;



import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateBankAccountsService;
import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateCompaniesService;
import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateCustomersService;
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

  public TestDataController() {
    super();
  }

  @GetMapping("create_users")
  public String createUsersHandler() {
    generateCustomersService.createUsers();
    return "redirect:/";
  }

  @GetMapping("create_companies")
  public String createCompaniesHandler() {
    generateCompaniesService.createCompanies();
    return "redirect:/";
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



}
