package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends CrudRepository<Transaction, Integer> {
    public List<Transaction> findByIbanFromOrIbanToOrderByDate (String ibanFrom, String ibanTo);
}
