package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessAccountDAO extends CrudRepository<BusinessAccount, Integer> {

    public Optional<BusinessAccount> findByAccountID(int id);
    public Optional<BusinessAccount> findBusinessAccountByIban(String iban);
    public List<BusinessAccount> findAllByCompany(Company company);
}
