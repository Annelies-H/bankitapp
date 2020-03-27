package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionDAO tdao;

    public TransactionService() {
        super();
    }

    public List<Transaction> findTransactionsByIban(String iban) {
        List<Transaction> transactions = tdao.findByIbanFromOrIbanToOrderByDateDesc(iban, iban);
        if (transactions.isEmpty()) {
            return null;
        } else {
            return transactions;
        }

    }
    public List<Transaction> findTenTransactionsByIban(String iban) {
        List<Transaction> transactions = tdao.findTop10ByIbanFromOrIbanToOrderByDateDesc(iban, iban);
        if (transactions.isEmpty()) {
            return null;
        } else {
            return transactions;
        }

    }

    public void addTransactionsToBankaccountService(Bankaccount bankaccount) {
        String iban = bankaccount.getIban();
        List<Transaction> transactions = findTransactionsByIban(iban);
        if (transactions != null) {
            for (Transaction transaction : transactions
            ) {
                bankaccount.addTransactionHistory(transaction);
            }
        }
    }

        public void addTenTransactionsToBankaccountService(Bankaccount bankaccount) {
            String iban = bankaccount.getIban();
            List<Transaction> transactions = findTenTransactionsByIban(iban);
            if (transactions != null) {
                for (Transaction transaction : transactions
                ) {
                    bankaccount.addTransactionHistory(transaction);
                }
            }

    }




}
