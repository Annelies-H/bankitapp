// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import nl.hva.makeitwork.bankit.bankitapplication.service.GenerateUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestDatabaseController {

  @Autowired
  private FillDatabaseService fillDatabaseService;
  @Autowired
  private GenerateUsersService generateUsersService;

  public TestDatabaseController() {
    super();
  }

  @GetMapping("fill_database")  //als er een 'fill database' request komt doe dan:
  public String filldatabaseHandler() {
    fillDatabaseService.fillDatabase(); //filldatabase methode wordt aangeroepen
    return "menu"; //vervolgschem wordt teruggegooid
  }
  @GetMapping("login")  //als er een 'fill database' request komt doe dan:
  public String naarInlogHandler() {
    return "login"; //vervolgschem wordt teruggegooid
  }

  @GetMapping("create_users")
  public String createUsersHandler() {
    generateUsersService.createUsers();
    return "menu";
  }



}
