// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateCustomerController {

  @Autowired
  CustomerDAO customerDAO;

  @PostMapping("/save")
  public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

    // save the employee
    customerDAO.save(theCustomer);

    // use a redirect to prevent duplicate submissions
    return "login";
  }
}
