package nl.hva.makeitwork.bankit.bankitapplication.controller;



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



}
