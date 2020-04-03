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


import java.util.Optional;

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



    /**
     * Create a new private bank account for the customer and update the database accordingly
     *
     * @param accountHolder
     * @return new private account
     */
    public PrivateAccount newPrivateAccount(Customer accountHolder) {
        PrivateAccount newAccount = new PrivateAccount();
        return (PrivateAccount) newAccountHelper(newAccount, accountHolder);
    }

    /**
     * Create a new business bank account for the customer and update the database accordingly
     *
     * @param
     * @return new business account
     */
    public BusinessAccount newBusinessAccount(Customer customer, Company company) {
        cdao.save(company);
        BusinessAccount newAccount = new BusinessAccount();
        newAccount.setCompany(company);
        return (BusinessAccount) newAccountHelper(newAccount, customer);
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
}
