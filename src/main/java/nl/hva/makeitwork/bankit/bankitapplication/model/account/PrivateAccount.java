package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import java.util.ArrayList;
import java.util.List;

public class PrivateAccount extends Bankaccount {
    List<Customer> accountHolders = new ArrayList<>();

    public PrivateAccount(String iban, List<Transaction> history, double balance, List<Customer> accountHolders) {
        super(iban, history, balance);
        this.accountHolders = accountHolders;
    }

    public PrivateAccount(List<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }

    public PrivateAccount(String iban, List<Transaction> history, double balance) {
        super(iban, history, balance);
    }

    public PrivateAccount() {
    }

    public List<Customer> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }
}
