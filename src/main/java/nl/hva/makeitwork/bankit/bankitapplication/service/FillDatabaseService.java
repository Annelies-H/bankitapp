
package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.Address;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.dao.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Person;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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


  //Gebruikt om te kijken of de database connectie werkt
  //Genereer van elke klasse een object en sla deze op in de database.
  public FillDatabaseService() {
    super();
  }

  public void fillDatabase() {
    Company company = addCompany();
    addEmployee();
    Customer customer = addCustomer();
    PrivateAccount pAccount = addPrivateAccount(customer);
    BusinessAccount bAccount = addbusinessAccount(company);
    addPOSterminal(bAccount);
    addTransaction(bAccount, pAccount);
    updateCustomer(customer, pAccount, bAccount);
    System.out.println("**** Hier wordt de db gevuld");
  }

  public void updateCustomer(Customer customer, PrivateAccount privateAccount, BusinessAccount businessAccount) {
    customer.getBankaccounts().add(privateAccount);
    customer.getBankaccounts().add(businessAccount);
    customer.setSocialSecurityNumber(12345678);
    customerDAO.save(customer);
  }

  public void addTransaction(Bankaccount from, Bankaccount to) {
    Calendar date = Calendar.getInstance();
    Transaction transaction = new Transaction();
    transaction.setAmmount(3.15);
    transaction.setDate(date);
    transaction.setIbanFrom(from);
    transaction.setIbanTo(to);
    transaction.setPaymentMethod(PaymentMethod.ATM);
    transactionDAO.save(transaction);
  }

  public void addPOSterminal(BusinessAccount account) {
    POSterminal pos = new POSterminal();
    posDAO.save(pos);
    POSterminal possy = new POSterminal();
    possy.setAccount(account);
    posDAO.save(possy);
  }

  public BusinessAccount addbusinessAccount(Company company) {
    BusinessAccount account = new BusinessAccount();
    account.setCompany(company);
    account.setBalance(358);
    account.setIban("NL65BAIT12345678");
    businessAccountDAO.save(account);
    return account;
  }

  public PrivateAccount addPrivateAccount(Customer customer) {
    PrivateAccount account = new PrivateAccount();
    account.setBalance(1230.65);
    account.setIban("NL33BAIT123456789");
    account.getAccountHolders().add(customer);
    privateAccountDAO.save(account);
    return account;
  }

  public Customer addCustomer() {
    Address address = new Address("de Lange Lindelaan", 101, "1245ZZ", "Utrecht", "Nederland");
    ContactDetails contact = new ContactDetails(address, "LiesjeLeerd@LotjeLopen", "1234567890");
    Calendar birthday = Calendar.getInstance();
    birthday.set(1933, 11,30);
    Person person = new Person("Liesje", "van", "Jansen", "LL", birthday, "v");
    Customer customer = new Customer();
    customer.setSocialSecurityNumber(123456789);
    customer.setContactDetails(contact);
    customer.setPerson(person);
    customer.setUsername("KBoer01");
    customer.setPassword("W3lk0m2o2o");
    customerDAO.save(customer);
    return customer;
  }

  public void addEmployee() {
    Address address = new Address("Steegje", 43, "5678PB", "Berlijn", "Engeland");
    ContactDetails contact = new ContactDetails(address, "bladiebla@hotmail.com", "0609876543");
    Calendar birthday = Calendar.getInstance();
    birthday.set(1986, 11,04);
    Person person = new Person("Kees", "de", "Boer", "C", birthday, "v");
    Employee employee = new Employee();
    employee.setId(23);
    employee.setPosition(Position.ACCOUNTMANAGER);
    employee.setContactDetails(contact);
    employee.setPerson(person);
    employee.setUsername("KBoer01");
    employee.setPassword("W3lk0m2o2o");
    employeeDAO.save(employee);
  }

  public Company addCompany() {
    Address address = new Address("straat", 101, "b", "1234AB", "Hilversum", "Nederland");
    ContactDetails contact = new ContactDetails(address, "info@bankit.nl", "0612345678");
    Company company = new Company("BankIT", "Financiele dienstverlening", 12345, contact );
    companyDAO.save(company);
    return company;
  }




}
