package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionDAO tdao;

    public TransactionService() {super();
    }

    public List<Transaction> findTransactionByIban (String iban){
        List<Transaction> transactions = tdao.findByIbanFromOrIbanToOrderByDate(iban,iban);
        if (transactions.isEmpty()){
            return null;
        } else{
        return transactions;}
    }

}
