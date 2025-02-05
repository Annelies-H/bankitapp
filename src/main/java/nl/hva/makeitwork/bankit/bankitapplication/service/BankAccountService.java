package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.*;

@Service
public class BankAccountService {
    public static final double INIT_BALANCE = 100;

    @Autowired
    private BusinessAccountDAO bdao;
    @Autowired
    private PrivateAccountDAO pdao;
    @Autowired
    private CompanyDAO cdao;
    @Autowired
    private TransactionService tService;
    @Autowired
    private BankAccountDAO badao;
    @Autowired
    private CompanyService companyService;



    /**
     * Create a new private bank account for the customer and update the database accordingly
     *
     * @param accountHolder
     * @return new private account
     */
    public PrivateAccount newPrivateAccount(Customer accountHolder) {
        PrivateAccount newAccount = (PrivateAccount) newAccountHelper(new PrivateAccount(), accountHolder);
        String iban = Bankaccount.constructIBAN(newAccount.getAccountID());
        newAccount.setIban(iban);
        badao.save(newAccount);
        return newAccount;
    }

    /**
     * Create a new business bank account for the customer and update the database accordingly
     *
     * @param
     * @return new business account
     */
    public BusinessAccount newBusinessAccount(Customer customer, Company company) {
        BusinessAccount newAccount = (BusinessAccount) newAccountHelper(new BusinessAccount(), customer);
        cdao.save(company);
        newAccount.setCompany(company);
        String iban = Bankaccount.constructIBANBiz(newAccount.getAccountID());
        newAccount.setIban(iban);
        return newAccount;
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
        badao.save(newAccount);
        return newAccount;
    }

    public PrivateAccount findPrivateAccountByID(int id) {
        Optional<PrivateAccount> selectedPrivateAccount = pdao.findByAccountID(id);
        return selectedPrivateAccount.orElse(null);
    }

    public PrivateAccount findPrivateAccountByIbanWithoutTransactionHistory(String iban) {
        Optional<PrivateAccount> selectedPrivateAccount = pdao.findPrivateAccountByIban(iban);
        return selectedPrivateAccount.orElse(null);
    }

    public BusinessAccount findBusinessAccountByID(int id) {
        Optional<BusinessAccount> selectedBusinessAccount = bdao.findByAccountID(id);
          return selectedBusinessAccount.orElse(null);
    }

    public BusinessAccount findBusinessAccountByIbanWithoutTransactionHistory(String iban) {
        Optional<BusinessAccount> selectedBusinessAccount = bdao.findBusinessAccountByIban(iban);

        return selectedBusinessAccount.orElse(null);
    }

    public void addAccountToModelByIdIncludingTransactions(int id, Model model) {
        PrivateAccount pAccount = findPrivateAccountByID(id);
        BusinessAccount bAccount = findBusinessAccountByID(id);

        if (pAccount != null) {
            tService.addTransactionsToBankaccountService((Bankaccount) pAccount);
            model.addAttribute("account", pAccount);
            model.addAttribute("company", "null");
        } else {
            tService.addTransactionsToBankaccountService((Bankaccount)bAccount);
            model.addAttribute("account", bAccount);
        }
    }

    public void addAccountToModelByIdIncludingTenTransactions(int id, Model model) {
        PrivateAccount pAccount = findPrivateAccountByID(id);
        BusinessAccount bAccount = findBusinessAccountByID(id);

        if (pAccount != null) {
            tService.addTenTransactionsToBankaccountService((Bankaccount) pAccount);
            model.addAttribute("account", pAccount);
            model.addAttribute("company", "null");
        } else {
            tService.addTenTransactionsToBankaccountService((Bankaccount)bAccount);
            model.addAttribute("account", bAccount);
        }
    }

    public void addAccountToModelByIdWithoutTransactions(int id, Model model) {
        PrivateAccount pAccount = findPrivateAccountByID(id);
        BusinessAccount bAccount = findBusinessAccountByID(id);

        if (pAccount != null) {
            model.addAttribute("account", pAccount);
            model.addAttribute("company", "null");
        } else {
            model.addAttribute("account", bAccount);
        }
    }

    public void updateBalanceAccountWithTransaction(Transaction transaction) {
       PrivateAccount pAccountTo = findPrivateAccountByIbanWithoutTransactionHistory(transaction.getIbanTo());
        BusinessAccount bAccountTo = findBusinessAccountByIbanWithoutTransactionHistory(transaction.getIbanTo());
        PrivateAccount pAccountFrom = findPrivateAccountByIbanWithoutTransactionHistory(transaction.getIbanFrom());
        BusinessAccount bAccountFrom = findBusinessAccountByIbanWithoutTransactionHistory(transaction.getIbanFrom());

        Double amount = transaction.getAmount();

        if (pAccountTo != null) {
            pAccountTo.updateBalanceAfterTransaction(amount);
            pdao.save(pAccountTo);
        } else if (bAccountTo != null){
            bAccountTo.updateBalanceAfterTransaction(amount);
            bdao.save(bAccountTo);
        }

        if (pAccountFrom != null) {
            pAccountFrom.updateBalanceAfterTransaction(amount * -1.0);
            pdao.save(pAccountFrom);
        } else if (bAccountFrom != null){
            bAccountFrom.updateBalanceAfterTransaction(amount * -1.0);
            bdao.save(bAccountFrom);
        }
    }

    public List<PrivateAccount> getTop10Private() {
        Iterable<PrivateAccount> iterableAccounts = pdao.findAll();
        Iterator<PrivateAccount> iterator = iterableAccounts.iterator();
        List<PrivateAccount> privateAccounts = new ArrayList<>();
        while (iterator.hasNext()) {
            privateAccounts.add(iterator.next());
        }

        Collections.sort(privateAccounts);
        if (privateAccounts.size() > 10) {
            privateAccounts = privateAccounts.subList(0,10);
        }

        return privateAccounts;
    }

    public double getTotalBalanceCompany(Company company) {
        List<BusinessAccount> companyAccounts = bdao.findAllByCompany(company);
        double balance = 0.0;
        for (BusinessAccount companyAccount : companyAccounts) {
            balance += companyAccount.getBalance();
        }
        return balance;
    }

    public List<BusinessAccount> getAllBusinessAccounts() {
        Iterable<BusinessAccount> accountIterable = bdao.findAll();
        Iterator<BusinessAccount> accountIterator = accountIterable.iterator();
        List<BusinessAccount> businessAccounts = new ArrayList<>();
        while (accountIterator.hasNext()) {
            businessAccounts.add(accountIterator.next());
        }

        for (BusinessAccount businessAccount : businessAccounts) {
            tService.addTransactionsToBankaccountService(businessAccount);
        }

        return businessAccounts;
    }

    public boolean doIbanCheck(String iban) { // returns boolean als iban in database bestaat.
        return bdao.findBusinessAccountByIban(iban).isPresent() || pdao.findPrivateAccountByIban(iban).isPresent();
    }

}
