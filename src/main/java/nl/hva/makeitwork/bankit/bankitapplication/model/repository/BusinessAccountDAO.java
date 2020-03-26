package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessAccountDAO extends CrudRepository<BusinessAccount, Integer> {

    public Optional<BusinessAccount> findByAccountID(int id);

    public Optional<BusinessAccount> findBusinessAccountByIban(String iban);
}
