// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.FillDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;

@Controller
public class CreateCustomerController {

  @Autowired
  CustomerDAO customerDAO;

  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model theModel) {

    // create model attribute to bind form data
    Customer theCustomer = new Customer();

    theModel.addAttribute("customer", theCustomer);

    return "create_customer";
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("customer") Customer theCustomer) {

    // save the employee
    customerDAO.save(theCustomer);

    // use a redirect to prevent duplicate submissions
    return "login";
  }
}
