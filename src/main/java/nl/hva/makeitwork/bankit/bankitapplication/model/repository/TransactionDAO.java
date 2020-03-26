package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends CrudRepository<Transaction, Integer> {
    public List<Transaction> findByIbanFromOrIbanToOrderByDateDesc(String ibanFrom, String ibanTo);

    public List<Transaction> findTop10ByIbanFromOrIbanToOrderByDateDesc (String ibanFrom, String ibanTo);
}
