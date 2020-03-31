package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import org.dom4j.rule.NullAction;
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

    public void addBankAccount(Customer customer, Bankaccount account) {
        customer.addAccount(account);
        customerDAO.save(customer);
    }

    public Boolean doesSocialSecurityNumberExists(Integer ssn) { // returns boolean als BSN in database bestaat.
        return customerDAO.findBySocialSecurityNumber(ssn).isPresent();
    }

}
