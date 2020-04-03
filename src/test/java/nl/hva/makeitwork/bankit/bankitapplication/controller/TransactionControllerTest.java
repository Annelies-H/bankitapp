package nl.hva.makeitwork.bankit.bankitapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionDAO tDAO;

    @MockBean
    private BankAccountService bService;

    @Test
    void transactionOverviewHandler() throws Exception {
        mockMvc.perform(get("/transaction/selected_transaction")
                .contentType("application"))
                .andExpect(status().isOk())
                .andExpect(view().name("under_construction"));
    }


    @Test
    void testNewTransactionOverviewHandler() throws Exception {

        mockMvc.perform(get("/transaction/new_transaction").queryParam("id", "1")
                .contentType("application")
                // handmatig account toevoegen aan model, wordt normaal door void methode gedaan
                .flashAttr("account", mock(Bankaccount.class)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("transaction"))
                .andExpect(view().name("new_transaction"));
    }

    @Test
    void saveTransaction() throws Exception {

        mockMvc.perform(post("/transaction/save_transaction").queryParam("id", "1")
                .contentType("application")
                // handmatig account toevoegen aan model, wordt normaal door void methode gedaan
                .flashAttr("account", mock(Bankaccount.class)))
                .andExpect(status().is3xxRedirection())
              /*  .andExpect(model().attributeExists("transaction"))*/
                .andExpect(redirectedUrl("/account/selected_bankaccount?id=1"));

    }
}