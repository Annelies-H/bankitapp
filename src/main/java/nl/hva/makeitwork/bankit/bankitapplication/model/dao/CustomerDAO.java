package nl.hva.makeitwork.bankit.bankitapplication.model.dao;


import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer> {
}
