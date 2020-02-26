package nl.hva.makeitwork.bankit.bankitapplication.model.dao;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.POSterminal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POSterminalDAO extends CrudRepository<POSterminal, Integer> {
}
