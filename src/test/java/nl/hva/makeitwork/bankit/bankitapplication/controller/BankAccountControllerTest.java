package nl.hva.makeitwork.bankit.bankitapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Hier geef je aan welke controller je gaat testen
@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    //Mock Mvc wordt gebruikt om de http request te kunnen mocken
    private MockMvc mockMvc;
    @Autowired
    //ObjectMapper kan gebruikt worden om stringwaardes van een object te genereren die je kan meegeven in de request
    //Bijvoorbeeld .content(objectMapper.writeValueAsString(customer))) om een customer in je post request te zetten
    private ObjectMapper objectMapper;

    //Spring wilt graag dat alle service in de controller die je test gemockt worden
    //Ook als je ze niet specifiek aanroept
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
        //De mockMvc 'performed' een get request voor de gegeven url met het gegeven content type
        mockMvc.perform(get("/account/connect_account")
                .contentType("application/json"))
                //De test verwacht de isOK status
                .andExpect(status().isOk());
    }

    @Test
    //Test of de controler het juiste attribuut meegeeft aan het model
    void newPrivateAccountController() throws Exception {
        mockMvc.perform(get("/account/new/private")
                .contentType("application/json"))
                //De test verwacht de isOK status
                .andExpect(status().isOk())
                //En de test verwacht het volgende model attribuut:
                .andExpect(model().attribute("accounttype", "priverekening"));
    }

    @Test
    //Test de postmapping voor een nieuwe priverekening, maakt nieuwe rekening voor de klant
    //Hiervoor moet er een sessionattribuut customer aan de postrequest worden toegevoegd
    //Er wordt alleen getest of je juist wordt doorgestuurd naar de volgende pagina (http statys 200 OK)
    //Deze methode test niet of alles ook netjes in de db wordt opgeslagen en of de juiste attributen worden meegegeven
     void newPrivateAccountConfirmedController() throws Exception {
        Customer customer = new Customer();

        //Creeer een priveaccount en zorg dat de juiste methode in de BankAccountService wordt gemockt
        PrivateAccount privateAccount = new PrivateAccount();
        privateAccount.setIban("NL33BAIT0123456789");
        when(bservice.newPrivateAccount(customer)).thenReturn(privateAccount);

        //Test het postrequest
        mockMvc.perform(post("/account/new/private/confirmed")
                .contentType("application/json")
                //geeft een sessionattribuut mee
                .sessionAttr("customer", customer))
                .andExpect(status().isOk());
    }

}
