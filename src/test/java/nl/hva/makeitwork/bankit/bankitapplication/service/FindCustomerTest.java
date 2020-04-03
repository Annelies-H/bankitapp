package nl.hva.makeitwork.bankit.bankitapplication.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

@SpringBootTest
public class FindCustomerTest {

    @MockBean // MockDao
    CustomerDAO customerDAO;

    @Autowired
    CustomerService customerService;

    @Test
    public void findCustomerTest() {
        Customer lotje = new Customer();
        lotje.setUsername("Lotje01");
        Optional<Customer> l = Optional.of(lotje);

        when(customerDAO.findByUsername("Lotje01")).thenReturn(l);

        Customer c = new Customer();

        c = customerService.findCustomer("Lotje01");

        assertEquals("Lotje01", c.getUsername());
        ;

    }

}
