package nl.hva.makeitwork.bankit.bankitapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BankAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.service.BankAccountService;
import nl.hva.makeitwork.bankit.bankitapplication.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BankAccountService bservice;
    @MockBean
    private CustomerService cservice;
    @MockBean
    private CompanyDAO cdao;
    @MockBean
    private BankAccountDAO bdao;


    @Test
    //Test of een simpel getrequest daadwerkelijk een http 200 OK status code retouneert
    void whenConnectAccountRequest_thenReturns200() throws Exception {
        mockMvc.perform(get("/account/connect_account")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    //Test of de controler het juiste attribuut meegeeft aan het model
    void newPrivateAccountController() throws Exception {
        mockMvc.perform(get("/account/new/private")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("accounttype", "priverekening"));
    }

    @Test
    //Test de postmapping voor een nieuwe priverekening, maakt nieuwe rekening voor de klant
    //Hiervoor moet er een sessionattribuut customer aan de postrequest worden toegevoegd
    void newPrivateAccountConfirmedController() throws Exception {
        ContactDetails contact = new ContactDetails();
        Customer customer = new Customer("Piet", "de", "Bruin", "01-01-2001", "man", contact);
        customer.setSocialSecurityNumber(123456789);
        customer.setUsername("pietjepuk");
        customer.setPassword("password");
        mockMvc.perform(post("/account/new/private/confirmed")
                .contentType("application/json")
                .sessionAttr("customer", customer))
                .andExpect(status().isOk());
    }

}
