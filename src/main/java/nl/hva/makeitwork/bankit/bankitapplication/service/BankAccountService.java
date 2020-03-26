package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BusinessAccountDAO bdao;
    @Autowired
    private PrivateAccountDAO pdao;
    @Autowired
    private CustomerDAO cdao;
    @Autowired
    private TransactionService tService;


    /**
     * Create a new private bank account for the customer and update the database accordingly
     *
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

    public PrivateAccount findPrivateAccountByID(int id) {
        Optional<PrivateAccount> selectedPrivateAccount = pdao.findByAccountID(id);
        if (selectedPrivateAccount.isPresent()) {
            String iban = selectedPrivateAccount.get().getIban();
            List<Transaction> transactions = tService.findTransactionsByIban(iban);
            if (transactions != null) {
                for (Transaction transaction : transactions
                ) {
                    selectedPrivateAccount.get().addTransactionHistory(transaction);
                }
            }
        }
        return selectedPrivateAccount.orElse(null);
    }

    public PrivateAccount findPrivateAccountByIbanWithoutTransactionHistory(String iban) {
        Optional<PrivateAccount> selectedPrivateAccount = pdao.findPrivateAccountByIban(iban);

        return selectedPrivateAccount.orElse(null);
    }

    /**
     * Create a new private bank account for the customer and update the database accordingly
     *
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

    public BusinessAccount findBusinessAccountByID(int id) {
        Optional<BusinessAccount> selectedBusinessAccount = bdao.findByAccountID(id);
        if (selectedBusinessAccount.isPresent()) {
            String iban = selectedBusinessAccount.get().getIban();
            List<Transaction> transactions = tService.findTransactionsByIban(iban);
            if (transactions != null) {
                for (Transaction transaction : transactions
                ) {
                    selectedBusinessAccount.get().addTransactionHistory(transaction);
                }
            }
        }
        return selectedBusinessAccount.orElse(null);
    }

    public BusinessAccount findBusinessAccountByIbanWithoutTransactionHistory(String iban) {
        Optional<BusinessAccount> selectedBusinessAccount = bdao.findBusinessAccountByIban(iban);

        return selectedBusinessAccount.orElse(null);
    }

    public void addAccountToModelById(int id, Model model) {
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
