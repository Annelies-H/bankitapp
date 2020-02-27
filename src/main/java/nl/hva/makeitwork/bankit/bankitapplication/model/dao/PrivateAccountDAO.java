package nl.hva.makeitwork.bankit.bankitapplication.model.dao;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateAccountDAO extends CrudRepository<PrivateAccount,Integer> {
}
