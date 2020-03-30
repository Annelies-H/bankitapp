package nl.hva.makeitwork.bankit.bankitapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Industry;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Met de @WebMvcTest geef je aan welke controller je gaat testen
@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    @Autowired
    //Mock Mvc wordt gebruikt om de http request te kunnen mocken
    private MockMvc mockMvc;
    @Autowired
    //ObjectMapper kan gebruikt worden om stringwaardes van een object te genereren die je kan meegeven in de request
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
        //Creer het session attribuut
        Customer customer = new Customer();

        //Creeer een priveaccount en zorg dat de juiste methode in de BankAccountService wordt gemockt
        PrivateAccount privateAccount = new PrivateAccount();
        privateAccount.setIban("NL33BAIT0123456789");
        //Onderstaande mock methode gebruikt de equals methodes van de klasses die als parameters worden megegeven
        //Of als je (zoals in dit geval) verwijst naar het zelfde object in het geheugen
        //Heb je geen equals methode of heb je geen zin om een geheel object op te tuigen dan moet je de paramter mocken
        //In plaats van een object geef je als parameter Mockito.any(VulHierJeKlasseNaamIn.class) mee
        when(bservice.newPrivateAccount(customer)).thenReturn(privateAccount);

        //Test het postrequest
        mockMvc.perform(post("/account/new/private/confirmed")
                .contentType("application/json")
                //geeft een sessionattribuut mee
                .sessionAttr("customer", customer))
                .andExpect(status().isOk());
    }

    @Test
    public void saveCompanyController() throws Exception {
        //Creer het session attribuut
        Customer customer = new Customer();

        //Creer een bankaccount en daarmee de bankrekening
        //zorg dat deze in de controller gegenereerd wordt met de mockservice
        Company newCompany = new Company(12345678, "testbedrijf", Industry.INDUSTRY);
        System.out.println(newCompany);
        BusinessAccount account = new BusinessAccount();
        account.setCompany(newCompany);
        account.setIban("NL33BAIT0123456789");
        System.out.println(account.getIban());
        //Onderstaande methode gebruikt de equals methodes van de klasses die als paramters worden megegeven
        //Heb je geen equals methode of heb je geen zin om een geheel object op te tuigen dan moet je de paramter mocken
        //In plaats van een object geef je als parameter Mockito.any(VulHierJeKlasseNaamIn.class) mee, zie volgende regel
        //when(bservice.newBusinessAccount(Mockito.any(Customer.class), Mockito.any(Company.class))).thenReturn(account);
        when(bservice.newBusinessAccount(customer, newCompany)).thenReturn(account);

        mockMvc.perform(post("/account/new/save_company")
                .contentType("application/x-www-form-urlencoded")
                //voeg een session attribute toe:
                .sessionAttr("customer", customer)
                //voeg de parameters uit het formulier toe aan de request
                //weet je niet zeker welke er zijn en hoe ze er uit zien
                //run de applicatie en kijk bij de netwerktools in de browser naar het postrequest
                .param("companyId", "12345678")
                .param("name", "testbedrijf")
                .param("industry", "INDUSTRY"))
                //controleer de code van de response
                .andExpect(status().isOk());
    }

    @Test
    public void getProductOverviewAsCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setGender("man");
        customer.setFirstName("Donald");
        customer.setLastName("Duck");

        //mock de findCustomer methode die wordt aangeroepen in de controler
        when(cservice.findCustomer(customer.getUsername())).thenReturn(customer);

        mockMvc.perform(get("/account/overview")
                .contentType("application/json")
                //voeg de customer toe als session attribute:
                .sessionAttr("customer", customer))
                //controleer de http code van de response:
                .andExpect(status().isOk())
                //controleer of de juiste view wordt getoond:
                .andExpect(view().name("product_overview"));
    }

    @Test
    public void getProductOverviewAsEmployee() throws Exception {
        mockMvc.perform(get("/account/overview")
                .contentType("application/json"))
                //Als er geen customer session attribuut is wordt de gebruiker geredirect
                //In plaats van een 200 verwacht je nu een 302 http code (temporary redirect):
                .andExpect(status().is3xxRedirection())
                //Hij returned nu niet direct een view maar een redirct:
                .andExpect(view().name("redirect:/intranet/dashboard"))
                //Je kan ook direct testen dat er een redirect url is en wat deze behoort te zijn:
                .andExpect(redirectedUrl("/intranet/dashboard"));
    }

}
