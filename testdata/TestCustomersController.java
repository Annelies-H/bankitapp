

package nl.hva.makeitwork.bankit.bankitapplication.controller;



import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCustomersController {

  @Autowired
  private GenerateCustomersService generateCustomersService;

  public TestCustomersController() {
    super();
  }

  @GetMapping("create_users")
  public String createUsersHandler() {
    generateCustomersService.createUsers();
    return "menu";
  }



}
