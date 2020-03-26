package nl.hva.makeitwork.bankit.bankitapplication.model.repository;


import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivateAccountDAO extends CrudRepository<PrivateAccount, Integer> {

    public Optional<PrivateAccount> findByAccountID(int id);
    public Optional<PrivateAccount> findPrivateAccountByIban(String iban);

}
