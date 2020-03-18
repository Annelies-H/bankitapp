
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
    addEmployees();
    Customer customer = addCustomer();
    PrivateAccount pAccount = addPrivateAccount(customer);
    BusinessAccount bAccount = addbusinessAccount(company);
    addPOSterminal(bAccount);
    addTransaction(bAccount, pAccount);
    updateCustomer(customer, pAccount, bAccount);
    System.out.println("**** Database gevuld met testdata voor elke tabel *****");
  }

  public void updateCustomer(Customer customer, PrivateAccount privateAccount, BusinessAccount businessAccount) {
    customer.getPrivateAccounts().add(privateAccount);
    customer.getBusinessAccounts().add(businessAccount);
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
    account.setIban("NL03BAIT0325489621");
    businessAccountDAO.save(account);
    return account;
  }

  public PrivateAccount addPrivateAccount(Customer customer) {
    PrivateAccount account = new PrivateAccount();
    account.setBalance(1230.65);
    account.setIban("NL53BAIT0213588648");
    account.getAccountHolders().add(customer);
    privateAccountDAO.save(account);
    return account;
  }

  public Customer addCustomer() {
    ContactDetails contact = new ContactDetails();
    contact.setEmail("LiesjeLeerd@LotjeLopen.nl");
    contact.setHouseNumber(101);
    contact.setZipcode("1234AB");
    String birthday = "1933-08-21";
    Customer customer = new Customer();
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
    return customer;
  }

  public void addEmployees() {
    Employee employee = new Employee();
    employee.setPosition(Position.ACCOUNTMANAGER);
    employee.setUsername("KBoer01");
    employee.setPassword("W3lk0m2o2o");
    employeeDAO.save(employee);

    Employee accountmanager = new Employee();
    accountmanager.setPosition(Position.ACCOUNTMANAGER);
    accountmanager.setUsername("Piet");
    accountmanager.setPassword("wwpiet");
    employeeDAO.save(accountmanager);

    Employee headBusiness = new Employee();
    headBusiness.setPosition(Position.HEAD_BUSINESS);
    headBusiness.setUsername("Kees");
    headBusiness.setPassword("wwkees");
    employeeDAO.save(headBusiness);

    Employee headPrivate = new Employee();
    headPrivate.setPosition(Position.HEAD_PRIVATE);
    headPrivate.setUsername("Jan");
    headPrivate.setPassword("wwjan");
    employeeDAO.save(headPrivate);
  }

  public Company addCompany() {
    Company company = new Company();
    company.setName("Pijnboom en co");
    company.setIndustry(Industry.AGRICULTURE);
    company.setCompanyId(23484876);
    companyDAO.save(company);
    return company;
  }




}
