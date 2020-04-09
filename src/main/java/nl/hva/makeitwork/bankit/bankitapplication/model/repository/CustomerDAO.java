package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer> {

    public Optional<Customer> findByUsername(String username);

    public Customer findCustomerByUserId(int userId);

    public Optional<Customer> findBySocialSecurityNumber(Integer socialSecurityNumber);

    public List<Customer> findAll();
}
