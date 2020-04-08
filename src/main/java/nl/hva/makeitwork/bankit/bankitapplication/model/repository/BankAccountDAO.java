package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankAccountDAO extends CrudRepository<Bankaccount, Integer> {



}
