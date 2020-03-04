package nl.hva.makeitwork.bankit.bankitapplication.model.repository;


import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
