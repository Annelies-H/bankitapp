// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateCustomerController {

  @Autowired
  CustomerDAO customerDAO;

  public CreateCustomerController () {
    super();
  }
  @GetMapping("create_customer")
  public String createFormAccountHandler (){
    return "create_customer";
  }

  @PostMapping("save_customer")
  public String createCustomerHandler(@ModelAttribute Customer customer) {
    customerDAO.save(customer);
    return "login";
  }
}
