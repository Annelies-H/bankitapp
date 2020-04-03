package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BankAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BankAccountServiceTest {

    @Autowired
    BankAccountService bservice;

    @MockBean
    BankAccountDAO bdao;

    @Test
    public void newPrivateAccountTest() {
        Customer c = new Customer();
        PrivateAccount expected = new PrivateAccount();
        expected.setBalance(bservice.INIT_BALANCE);
        expected.setIban("");
        expected.addAccountHolder(c);
        when(bdao.save(expected)).thenReturn(expected);
        PrivateAccount actual = bservice.newPrivateAccount(c);
        expected.setIban("NL94BAIT0201460000");
        assertEquals(expected, actual);
    }

}
