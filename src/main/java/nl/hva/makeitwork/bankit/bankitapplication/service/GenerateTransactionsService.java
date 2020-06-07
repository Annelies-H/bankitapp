package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PaymentMethod;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class GenerateTransactionsService {
        @Autowired
        private BusinessAccountDAO bAccountDAO;
        @Autowired
        private PrivateAccountDAO pAccountDAO;
        @Autowired
        private TransactionDAO transactionDAO;


        List<String> allIbans;
        List<PrivateAccount> allPrivateAccounts;
        List<BusinessAccount> allBusinessAccounts;

        public void createTransactionsForBusinessAccounts() {
            allBusinessAccounts = bAccountDAO.findAll();
            allPrivateAccounts = pAccountDAO.findAll();
            getAllIbans();
            for (BusinessAccount account : allBusinessAccounts) {
                generateFromTransactions(account.getIban());
                generateToTransactions(account.getIban());
            }
        }

        private void generateFromTransactions(String iban) {
            for (int i = 0; i < (int) (Math.random() * 4); i++) {
                Transaction transaction = new Transaction();
                transaction.setIbanFrom(iban);
                transaction.setIbanTo(allIbans.get(((int) (Math.random() * allIbans.size()))));
                transaction.setAmount(((int) (Math.random() * 50000)) / 100.0);
                transaction.setDescription("I like you, have some money...");
                transaction.setDate(Calendar.getInstance());
                transaction.setPaymentMethod(PaymentMethod.BANKTRANSFER);
                transactionDAO.save(transaction);
            }
        }

    private void generateToTransactions(String iban) {
        for (int i = 0; i < (int) (Math.random() * 4); i++) {
            Transaction transaction = new Transaction();
            transaction.setIbanTo(iban);
            transaction.setIbanFrom(allIbans.get(((int) (Math.random() * allIbans.size()))));
            transaction.setAmount(((int) (Math.random() * 50000)) / 100.0);
            transaction.setDescription("Here's my lunchmoney. Please don't hurt me.");
            transaction.setDate(Calendar.getInstance());
            transaction.setPaymentMethod(PaymentMethod.POS);
            transactionDAO.save(transaction);
        }
    }

        private void getAllIbans() {
            allIbans = new ArrayList<String>();
            for (BusinessAccount account : allBusinessAccounts) {
                allIbans.add(account.getIban());
            }
            for (PrivateAccount account : allPrivateAccounts) {
                allIbans.add(account.getIban());
            }
        }
}
