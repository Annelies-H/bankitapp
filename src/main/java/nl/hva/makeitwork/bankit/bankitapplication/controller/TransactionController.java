package nl.hva.makeitwork.bankit.bankitapplication.controller;


import nl.hva.makeitwork.bankit.bankitapplication.model.account.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SessionAttributes("customer")
@RequestMapping(value = "/transaction")

public class TransactionController {

    @Autowired
    TransactionDAO tdao;
    @Autowired
    private BankAccountService bas;

    @GetMapping("selected_transaction")
    public String transactionOverviewHandler(Model model) {

        return "under_construction";
    }

    @GetMapping("new_transaction")
    public String NewTransactionHandler(@RequestParam int id, Model model) {
        bas.addAccountToModelById(id, model);

        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);

        return "new_transaction";
    }

    @PostMapping("save_transaction")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction, @RequestParam int id, Model model) {
        bas.addAccountToModelById(id, model);

        Bankaccount bankaccount = (Bankaccount)model.getAttribute("account");
        assert bankaccount != null;
        transaction.setIbanFrom(bankaccount.getIban());
        transaction.setDate(Calendar.getInstance());
        transaction.setPaymentMethod(PaymentMethod.BANKTRANSFER);

        // save transaction

        tdao.save(transaction);
        bas.updateBalanceAccountWithTransaction(transaction);


        return "redirect:/transaction/new_transaction?id=" + id;


    }
}
