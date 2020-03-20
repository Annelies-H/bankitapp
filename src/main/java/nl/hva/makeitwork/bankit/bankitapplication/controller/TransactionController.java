package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Controller
@SessionAttributes("customer")
@RequestMapping (value = "/transaction")

public class TransactionController {

    @GetMapping("selected_transaction")
    public String transactionOverviewHandler (Model model ){

        return "under_construction";
    }

    @GetMapping("new_transaction")
    public String NewTransactionHandler (Model model ){

        return "under_construction";
    }
}
