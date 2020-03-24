package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BankAccountDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private static final double INIT_BALANCE = 100;

    @Autowired
    private BankAccountDAO badao;


    /**
     * Create a new private bank account for the customer and update the database accordingly
     * @param accountHolder
     * @return new private account
     */
    public PrivateAccount newPrivateAccount(Customer accountHolder) {
        PrivateAccount newAccount = new PrivateAccount();
        return (PrivateAccount) newAccountHelper(newAccount, accountHolder);
    }

    /**
     * Create a new private bank account for the customer and update the database accordingly
     * @param accountHolder
     * @return new private account
     */
    public BusinessAccount newBusinessAccount(Customer accountHolder, Company company) {
        BusinessAccount newAccount = new BusinessAccount();
        newAccount.setCompany(company);
        return (BusinessAccount) newAccountHelper(newAccount, accountHolder);
    }

    /**
     *
     * @param newAccount
     * @param accountHolder
     * @return
     */
    private Bankaccount newAccountHelper(Bankaccount newAccount, Customer accountHolder) {
        newAccount.setBalance(INIT_BALANCE);
        newAccount.setIban("");
        newAccount.addAccountHolder(accountHolder);
        badao.save(newAccount);
        String iban = Bankaccount.constructIBAN(newAccount.getAccountID());
        newAccount.setIban(iban);
        badao.save(newAccount);;
        return newAccount;
    }

}
