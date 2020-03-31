package nl.hva.makeitwork.bankit.bankitapplication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/api")
@RestController
public class RestContoller {// RESTapi

    CustomerService customerService;

    public RestContoller(CustomerService customerService) {
        this.customerService = customerService; // Constructor injectie ipv AUTO-WIRED
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @GetMapping(value = "ssn_check/{ssn}")
    public boolean ssnCheckHandler(@PathVariable Integer ssn) {
        return customerService.doesSocialSecurityNumberExists(ssn);
    }

}