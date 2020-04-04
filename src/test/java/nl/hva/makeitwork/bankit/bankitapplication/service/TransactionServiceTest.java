package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.PaymentMethod;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService tService;

    @MockBean
    private TransactionDAO mockTDAO;

    @Test
    void findTransactionsByIban () {

        String iban = "NL53BAIT0213844212";
        String ibanNoTransaction = "NL29BAIT0201460006";

        Transaction transactionOne = new Transaction("NL03BAIT0325489621", iban, 12.34,
                "transactie test", new GregorianCalendar(2019, 11, 12, 13, 14, 15),
                PaymentMethod.ATM);
        Transaction transactionTwo = new Transaction(iban, "NL03BAIT0325489621",
                12.34, "transactie test", Calendar.getInstance(), PaymentMethod.ATM);

        List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.add(transactionTwo);
        transactionsList.add(transactionOne);

        List<Transaction> emptyList = new ArrayList<>();

        Mockito.when(mockTDAO.findByIbanFromOrIbanToOrderByDateDesc(iban,iban)).thenReturn(transactionsList);
        Mockito.when(mockTDAO.findByIbanFromOrIbanToOrderByDateDesc(ibanNoTransaction,ibanNoTransaction)).thenReturn(emptyList);

        List<Transaction> found = tService.findTransactionsByIban(iban);
        List<Transaction> notFound = tService.findTransactionsByIban(ibanNoTransaction);

        assertThat(found.get(0).getIbanTo())
                .isEqualTo(transactionTwo.getIbanTo());
        assertThat(found.get(1).getIbanTo())
                .isEqualTo(transactionOne.getIbanTo());
        assertThat(notFound)
                .isEqualTo(null);

    }
}