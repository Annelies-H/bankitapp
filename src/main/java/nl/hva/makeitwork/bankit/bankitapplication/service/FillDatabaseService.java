
package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Industry;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class FillDatabaseService {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private PrivateAccountDAO privateAccountDAO;
    @Autowired
    private BusinessAccountDAO businessAccountDAO;
    @Autowired
    private POSterminalDAO posDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    // Gebruikt om te kijken of de database connectie werkt
    // Genereer van elke klasse een object en sla deze op in de database.
    public FillDatabaseService() {
        super();
    }

    public void fillDatabase() {
        addEmployees();
        List<Company> companies = addCompanies();
        List<Customer> customers = addCustomers();
        List<PrivateAccount> pAccounts = addPrivateAccounts(customers);
        List<BusinessAccount> bAccounts = addbusinessAccounts(companies, customers);
        addPOSterminal(bAccounts.get(0));

        // add bankaccounts to customers accountlist
        updateCustomerBusinessAccount(customers.get(0), bAccounts.get(0));
        updateCustomerBusinessAccount(customers.get(1), bAccounts.get(1));
        updateCustomerBusinessAccount(customers.get(2), bAccounts.get(1));
        updateCustomerBusinessAccount(customers.get(2), bAccounts.get(2));

        updateCustomerPrivateAccount(customers.get(0), pAccounts.get(0));
        updateCustomerPrivateAccount(customers.get(1), pAccounts.get(1));
        updateCustomerPrivateAccount(customers.get(2), pAccounts.get(2));

        // add transactions
        addTransaction(bAccounts.get(0).getIban(), pAccounts.get(0).getIban(), 70, PaymentMethod.ATM, "Geldautomaat");
        addTransaction(bAccounts.get(0).getIban(), pAccounts.get(0).getIban(), 2100, PaymentMethod.BANKTRANSFER,
                "Loon");
        addTransaction(bAccounts.get(2).getIban(), pAccounts.get(1).getIban(), 1562, PaymentMethod.BANKTRANSFER,
                "Loon");
        addTransaction(pAccounts.get(0).getIban(), bAccounts.get(1).getIban(), 999, PaymentMethod.POS,
                "Computer gekocht");
        addTransaction(pAccounts.get(1).getIban(), bAccounts.get(1).getIban(), 1099, PaymentMethod.POS,
                "Laptop gekocht");
        addTransaction(pAccounts.get(2).getIban(), bAccounts.get(0).getIban(), 3.50, PaymentMethod.POS,
                "Pijnboompit gekocht");
        addTransaction(bAccounts.get(3).getIban(), bAccounts.get(2).getIban(), 100.90, PaymentMethod.BANKTRANSFER,
                "subsidie");

        System.out.println("**** Database gevuld met testdata *****");
    }

    public void updateCustomerPrivateAccount(Customer customer, PrivateAccount privateAccount) {
        customer.getPrivateAccounts().add(privateAccount);
        customerDAO.save(customer);
    }

    public void updateCustomerBusinessAccount(Customer customer, BusinessAccount businessAccount) {
        customer.getBusinessAccounts().add(businessAccount);
        customerDAO.save(customer);
    }

    public void addTransaction(String fromIban, String toIban, double amount, PaymentMethod paymentMethod,
            String description) {

        Calendar date = Calendar.getInstance();

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setIbanFrom(fromIban);
        transaction.setDescription(description);
        transaction.setIbanTo(toIban);
        transaction.setPaymentMethod(paymentMethod);
        transactionDAO.save(transaction);

    }

    public void addPOSterminal(BusinessAccount account) {
        POSterminal pos = new POSterminal();
        posDAO.save(pos);
        POSterminal possy = new POSterminal();
        possy.setAccount(account);
        posDAO.save(possy);
    }

    public List<BusinessAccount> addbusinessAccounts(List<Company> companies, List<Customer> customers) {
        List<BusinessAccount> businessAccounts = new ArrayList<>();

        businessAccounts.add(newBusinessAccount(companies.get(0), 358, "NL03BAIT0325489621", new Customer[]{customers.get(0)}));
        businessAccounts.add(newBusinessAccount(companies.get(1), 4946464.23, "NL29BAIT0201460006", new Customer[]{customers.get(2)}));
        businessAccounts.add(newBusinessAccount(companies.get(1), 213070, "NL29BAIT0201460007", new Customer[]{customers.get(1), customers.get(2)}));
        businessAccounts.add(newBusinessAccount(companies.get(2), 3156743, "NL72BAIT0201460051", new Customer[]{customers.get(1)}));

        return businessAccounts;
    }

    public BusinessAccount newBusinessAccount(Company company, double balance, String iban, Customer[] accountHolders) {
        BusinessAccount account = new BusinessAccount();
        account.setCompany(company);
        account.setBalance(balance);
        account.setIban(iban);

        for (Customer accountHolder : accountHolders) {
            account.addAccountHolder(accountHolder);
        }

        businessAccountDAO.save(account);
        return account;
    }

    public List<PrivateAccount> addPrivateAccounts(List<Customer> customers) {
        List<PrivateAccount> privateAccounts = new ArrayList<>();
        PrivateAccount account;

        account = new PrivateAccount();
        account.setBalance(3230.65);
        account.setIban("NL53BAIT0213844212");
        account.getAccountHolders().add(customers.get(0));
        privateAccountDAO.save(account);
        privateAccounts.add(account);

        account = new PrivateAccount();
        account.setBalance(3641);
        account.setIban("NL83BAIT0201460004");
        account.getAccountHolders().add(customers.get(1));
        privateAccountDAO.save(account);
        privateAccounts.add(account);

        account = new PrivateAccount();
        account.setBalance(19476467.62);
        account.setIban("NL02BAIT0201460007");
        account.getAccountHolders().add(customers.get(2));
        privateAccountDAO.save(account);
        privateAccounts.add(account);

        return privateAccounts;
    }

    public List<Customer> addCustomers() {
        List<Customer> customers = new ArrayList<>();
        ContactDetails contact;
        String birthday;
        Customer customer;

        contact = new ContactDetails();
        contact.setEmail("LiesjeLeerd@LotjeLopen.nl");
        contact.setHouseNumber(101);
        contact.setZipcode("1234AB");
        birthday = "1933-08-21";
        customer = new Customer();
        customer.setSocialSecurityNumber(152842627);
        customer.setContactDetails(contact);
        customer.setFirstName("Liesje");
        customer.setGender("vrouw");
        customer.setLastName("Linde");
        customer.setPrefix("van der");
        customer.setUsername("Lotje01");
        customer.setPassword("Welkom2020");
        customer.setBirthday(birthday);
        customerDAO.save(customer);
        customers.add(customer);

        contact = new ContactDetails();
        contact.setEmail("Donald@duck.nl");
        contact.setHouseNumber(13);
        contact.setZipcode("1337YO");
        birthday = "1900-01-01";
        customer = new Customer();
        customer.setSocialSecurityNumber(268149971);
        customer.setContactDetails(contact);
        customer.setFirstName("Donald");
        customer.setGender("man");
        customer.setLastName("Duck");
        // customer.setPrefix("");
        customer.setUsername("Donald");
        customer.setPassword("<3katrien");
        customer.setBirthday(birthday);
        customerDAO.save(customer);
        customers.add(customer);

        contact = new ContactDetails();
        contact.setEmail("Bill@hotmail.com");
        contact.setHouseNumber(1);
        contact.setZipcode("1000MS");
        birthday = "1951-08-21";
        customer = new Customer();
        customer.setSocialSecurityNumber(291961343);
        customer.setContactDetails(contact);
        customer.setFirstName("Bill");
        customer.setGender("man");
        customer.setLastName("Gates");
        // customer.setPrefix("");
        customer.setUsername("Bill");
        customer.setPassword("iloveM$");
        customer.setBirthday(birthday);
        customerDAO.save(customer);
        customers.add(customer);

        return customers;
    }

    public void addEmployees() {

        Employee accountmanager = new Employee();
        accountmanager.setPosition(Position.ACCOUNTMANAGER);
        accountmanager.setUsername("JPMorgan");
        accountmanager.setPassword("ifyouasktheprice");
        employeeDAO.save(accountmanager);

        Employee headBusiness = new Employee();
        headBusiness.setPosition(Position.HEAD_BUSINESS);
        headBusiness.setUsername("DavidRockefeller");
        headBusiness.setPassword("iammoney");
        employeeDAO.save(headBusiness);

        Employee headPrivate = new Employee();
        headPrivate.setPosition(Position.HEAD_PRIVATE);
        headPrivate.setUsername("WimDuisenberg");
        headPrivate.setPassword("ecb");
        employeeDAO.save(headPrivate);
    }

    public List<Company> addCompanies() {
        List<Company> companies = new ArrayList<>();
        Company company;

        company = new Company();
        company.setName("Pijnboom en co");
        company.setIndustry(Industry.AGRICULTURE);
        company.setCompanyId(23484876);
        companyDAO.save(company);
        companies.add(company);

        company = new Company();
        company.setName("Microsoft");
        company.setIndustry(Industry.INDUSTRY);
        company.setCompanyId(13371337);
        companyDAO.save(company);
        companies.add(company);

        company = new Company();
        company.setName("Gemeente Duckstad");
        company.setIndustry(Industry.GOVERNMENT);
        company.setCompanyId(65487489);
        companyDAO.save(company);
        companies.add(company);

        return companies;
    }

    public boolean databaseIsEmpty() {
        Employee employee = employeeDAO.findEmployeeByUserId(1);
        Customer customer = customerDAO.findCustomerByUserId(1);
        return employee == null || customer == null;
    }

}
