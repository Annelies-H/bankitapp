package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = "/transaction_api")
@RestController
public class RestTransactionContoller {// RESTapi

    BankAccountService bankAccountService;

    public RestTransactionContoller(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService; // Constructor injectie ipv AUTO-WIRED
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @GetMapping(value = "iban_check/{iban}")
    public boolean ibanCheckHandler(@PathVariable String iban) {
        return bankAccountService.doIbanCheck(iban);
    }

}