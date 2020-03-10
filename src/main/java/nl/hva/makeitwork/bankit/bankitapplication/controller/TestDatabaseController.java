

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
//import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestDatabaseController {

  @Autowired
  private FillDatabaseService fillDatabaseService;
/*
  @Autowired
  private GenerateUsersService generateUsersService;
*/

  public TestDatabaseController() {
    super();
  }

  @GetMapping("fill_database")
  public String filldatabaseHandler() {
    fillDatabaseService.fillDatabase();
    return "menu";
  }

/*  @GetMapping("create_users")
  public String createUsersHandler() {
    generateUsersService.createUsers();
    return "menu";
  }*/



}
