package nl.hva.makeitwork.bankit.bankitapplication.model;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CustomerTest {

    @Test
    public void bsnCheck() {
        Customer c = new Customer();
        assertTrue(c.isValidBSN(70627666));
        assertTrue(c.isValidBSN(111222333));
        assertTrue(c.isValidBSN(123456782));
        assertFalse(c.isValidBSN(0));
        assertFalse(c.isValidBSN(-70627666));
        assertFalse(c.isValidBSN(61));
    }
}
