package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BusinessAccountDAO bdao;
    @Autowired
    private PrivateAccountDAO pdao;
    @Autowired
    private CustomerDAO cdao;

    /**
     * Create a new private bank account for the customer and update the database accordingly
     * @param accountHolder
     * @return new private account
     */
    public PrivateAccount newPrivateAccount(Customer accountHolder) {
        PrivateAccount newAccount = new PrivateAccount();
        newAccount.setBalance(100);
        newAccount.setIban("");
        newAccount.addAccountHolder(accountHolder);
        pdao.save(newAccount);
        String iban = Bankaccount.constructIBAN(newAccount.getAccountID());
        newAccount.setIban(iban);
        pdao.save(newAccount);
        accountHolder.addAccount(newAccount);
        cdao.save(accountHolder);
        return newAccount;
    }

    /**
     * Create a new private bank account for the customer and update the database accordingly
     * @param accountHolder
     * @return new private account
     */
    public BusinessAccount newBusinessAccount(Customer accountHolder, Company company) {
        BusinessAccount newAccount = new BusinessAccount();
        newAccount.setBalance(100);
        newAccount.setIban("");
        newAccount.addAccountHolder(accountHolder);
        newAccount.setCompany(company);
        bdao.save(newAccount);
        String iban = Bankaccount.constructIBAN(newAccount.getAccountID());
        newAccount.setIban(iban);
        bdao.save(newAccount);
        accountHolder.addAccount(newAccount);
        cdao.save(accountHolder);
        return newAccount;
    }

}
