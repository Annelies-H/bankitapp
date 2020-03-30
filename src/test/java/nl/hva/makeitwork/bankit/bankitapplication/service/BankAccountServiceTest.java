package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BankAccountDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

public class BankAccountServiceTest {

    @TestConfiguration
    //To check the Service class, we need to have an instance of Service class created and available as a @Bean
    // so that we can @Autowire it in our test class.
    static class BankAccountServiceConfiguration {
        @Bean
        public BankAccountService bankAccountService() {
            return new BankAccountService();
        }
    }

    @Autowired
    //Door de testconfiguratie juist op te stellen kan de BankaccountService geAutoWired worden
    BankAccountService bas;

    @MockBean
    //De repository laag willen we nu niet testen dus die mocken we
    private BankAccountDAO badao;


    @Test
    public void newAccountHelperTest() {

    }


}
