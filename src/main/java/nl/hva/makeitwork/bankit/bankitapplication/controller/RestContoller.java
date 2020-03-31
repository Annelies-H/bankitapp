package nl.hva.makeitwork.bankit.bankitapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;

@RequestMapping(value = "/api")
@RestController
public class RestContoller {// RESTapi

    CustomerService customerService;

    public RestContoller(CustomerService customerService) {
        this.customerService = customerService; // Constructor injectie ipv AUTO-WIRED
    }

    @GetMapping("ssn_check/{ssn}") // API die checkt of een BSN al bestaat in de Database. By default returned
                                   // restController data in JSON formaat.
    public Boolean ssnCheckHandler(@PathVariable Integer ssn) {
        return customerService.doesSocialSecurityNumberExists(ssn); // maakt gebruikt van de Customerservices en
                                                                    // returned ALLEEN een boolean, dus niet de hele
                                                                    // body/costumer data. Vervolgens kun je dit met
                                                                    // AJAX/FETCH in het submit formulier opvangen.
    }

}