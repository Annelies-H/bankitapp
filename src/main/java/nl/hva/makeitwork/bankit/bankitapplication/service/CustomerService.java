package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public CustomerService() {
        super();
    }

    public Customer findCustomer(String name) {
        Optional<Customer> customerOption = customerDAO.findByUsername(name);
        return customerOption.orElse(null);
    }

}
