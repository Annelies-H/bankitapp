package nl.hva.makeitwork.bankit.bankitapplication.controller;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BankAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService service;
    @MockBean
    private CustomerService cservice;
    @MockBean
    private CompanyDAO cdao;
    @MockBean
    private BankAccountDAO bdao;


    @Test
    //Test of een simpel getrequest daadwerkelijk een ok retouneert
    void whenConnectAccountRequest_thenReturns200() throws Exception {
        mockMvc.perform(get("/account/connect_account")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

}
