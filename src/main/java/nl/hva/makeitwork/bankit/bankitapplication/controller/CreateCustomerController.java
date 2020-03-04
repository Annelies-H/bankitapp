// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateCustomerController {

  @Autowired
  private FillDatabaseService fillDatabaseService;

  public CreateCustomerController() {
    super();
  }

  @GetMapping("/create_customer")
  public String createCustomerHandler() {
    fillDatabaseService.fillDatabase();
    return "index";
  }
}
