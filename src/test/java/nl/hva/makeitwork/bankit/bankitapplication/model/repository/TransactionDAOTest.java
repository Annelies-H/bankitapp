package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.PaymentMethod;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class TransactionDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionDAO tdao;

    //
    @Test
    void findByIbanFromOrIbanToOrderByDateDesc() {
        // given
        String iban = "NL53BAIT0213844212";

        Transaction transactionOne = new Transaction("NL03BAIT0325489621", iban, 12.34,
                "transactie test", new GregorianCalendar(2019, 11, 12, 13, 14, 15),
                PaymentMethod.ATM);
        Transaction transactionTwo = new Transaction(iban, "NL03BAIT0325489621",
                12.34, "transactie test", Calendar.getInstance(), PaymentMethod.ATM);

        //Make an instance managed and persistent.
        entityManager.persist(transactionOne);
        entityManager.persist(transactionTwo);
        //Synchronize the persistence context to the underlying database.
        entityManager.flush();

        // when
        List<Transaction> found = tdao.findByIbanFromOrIbanToOrderByDateDesc(iban, iban);

        // then
        assertThat(found.get(0).getIbanTo())
                .isEqualTo(transactionTwo.getIbanTo());
        assertThat(found.get(1).getIbanTo())
                .isEqualTo(transactionOne.getIbanTo());
    }

}