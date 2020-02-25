// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestDatabaseController {

  @Autowired
  // autowired: roep tegen spring maak jij maar een nieuwe instantie van fildatabaseservice voor elke instantie van
  // democontroller. Je hoeft dus niet zelf met new huppelepup bezig te gaan
  // Dit kan alleen met klasses die een @Component zijn (@Controller, @Service, @Repository, @Config)
  private FillDatabaseService fillDatabaseService;

  public TestDatabaseController() {
    super();
  }

  @GetMapping("fill_database")  //als er een 'fill database' request komt doe dan:
  public String filldatabaseHandler() {
    fillDatabaseService.fillDatabase(); //filldatabase methode wordt aangeroepen
    return "menu"; //vervolgschem wordt teruggegooid
  }


}
