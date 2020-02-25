package nl.hva.makeitwork.bankit.bankitapplication.model.dao;

import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDAO extends CrudRepository<Company, Integer> {
}
