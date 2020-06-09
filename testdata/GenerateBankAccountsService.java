package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class GenerateBankAccountsService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private BusinessAccountDAO bAccountDAO;
    @Autowired
    private PrivateAccountDAO pAccountDAO;
    @Autowired
    private CustomerService customerService;

    List<Customer> allCustomers;
    List<Company> allCompanies;


    public void createPrivateAccounts() {
        allCustomers = customerDAO.findAll();
        createLowBalancePrivateAccounts();
        createMidBalancePrivateAccounts();
        createHighBalancePrivateAccounts();
    }

    private void createLowBalancePrivateAccounts() {
        for (int i = 350; i < 2000; i++) {
            Customer customer = allCustomers.get(i);
            PrivateAccount account = new PrivateAccount();
            double randomBalance = ((int) (Math.random() * 150000)) / 100.0;
            account.setBalance(randomBalance);
            account.setIban("");
            account.addAccountHolder(customer);
            pAccountDAO.save(account);
            String iban = Bankaccount.constructIBAN(account.getAccountID());
            account.setIban(iban);
            pAccountDAO.save(account);
            customerService.addBankAccount(customer, account);
        }
    }

    private void createMidBalancePrivateAccounts() {
        for (int i = 1500; i < 3800; i++) {
            Customer customer = allCustomers.get(i);
            PrivateAccount account = new PrivateAccount();
            double randomBalance = ((int) (Math.random() * 500000)) / 100.0;
            account.setBalance(randomBalance);
            account.setIban("");
            account.addAccountHolder(customer);
            pAccountDAO.save(account);
            String iban = Bankaccount.constructIBAN(account.getAccountID());
            account.setIban(iban);
            pAccountDAO.save(account);
            customerService.addBankAccount(customer, account);
        }
    }

    private void createHighBalancePrivateAccounts() {
        for (int i = 3500; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            PrivateAccount account = new PrivateAccount();
            double randomBalance = ((int) (Math.random() * 10000000)) / 100.0 + 10000;
            account.setBalance(randomBalance);
            account.setIban("");
            account.addAccountHolder(customer);
            pAccountDAO.save(account);
            String iban = Bankaccount.constructIBAN(account.getAccountID());
            account.setIban(iban);
            pAccountDAO.save(account);
            customerService.addBankAccount(customer, account);
        }
    }

    public void createBusinessAccounts() {
        allCustomers = customerDAO.findAll();
        allCompanies = companyDAO.findAll();
        System.out.println(allCustomers.size());
        System.out.println(allCompanies.size());
        createOnForEachCompany();
        createHighBalanceBusinessAccounts();
        createLowBalanceBusinessAccounts();
    }

    private void createOnForEachCompany() {
        for (int i = 0; i < allCompanies.size(); i++) {
            Customer customer = allCustomers.get(i);
            BusinessAccount account = new BusinessAccount();
            account.setCompany(allCompanies.get(i));
            double randomBalance = ((int) (Math.random() * 1500000) )/ 100.0;
            account.setBalance(randomBalance);
            account.setIban("");
            account.addAccountHolder(customer);
            bAccountDAO.save(account);
            String iban = Bankaccount.constructIBAN(account.getAccountID());
            account.setIban(iban);
            bAccountDAO.save(account);
            customerService.addBankAccount(customer, account);
        }
    }

    private void createHighBalanceBusinessAccounts() {
        for (int i = 200; i < 275; i++) {
            int randomCustomer = ((int) (Math.random() * allCustomers.size()));
            Customer customer = allCustomers.get(randomCustomer);
            BusinessAccount account = new BusinessAccount();
            account.setCompany(allCompanies.get(i));
            double randomBalance = ((int) (Math.random() * 15000000) )/ 100.0 + 10000;
            System.out.println(randomBalance);
            account.setBalance(randomBalance);
            account.setIban("");
            account.addAccountHolder(customer);
            bAccountDAO.save(account);
            String iban = Bankaccount.constructIBAN(account.getAccountID());
            account.setIban(iban);
            bAccountDAO.save(account);
            customerService.addBankAccount(customer, account);
        }
    }

    private void createLowBalanceBusinessAccounts() {
        for (int i = 0; i < 75; i++) {
            int randomCustomer = ((int) (Math.random() * allCustomers.size()));
            Customer customer = allCustomers.get(randomCustomer);
            BusinessAccount account = new BusinessAccount();
            account.setCompany(allCompanies.get(i));
            double randomBalance = ((int) (Math.random() * 200000) )/ 100.0;
            account.setBalance(randomBalance);
            account.setIban("");
            account.addAccountHolder(customer);
            bAccountDAO.save(account);
            String iban = Bankaccount.constructIBAN(account.getAccountID());
            account.setIban(iban);
            bAccountDAO.save(account);
            customerService.addBankAccount(customer, account);
        }
    }


}
