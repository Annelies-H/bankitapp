package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PrivateAccount extends Bankaccount {
    @ManyToMany
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

    public void addAccountHolder(Customer customer) {
        accountHolders.add(customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateAccount)) return false;
        if (!super.equals(o)) return false;
        PrivateAccount that = (PrivateAccount) o;
        return accountHolders.equals(that.accountHolders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountHolders);
    }

    public List<Customer> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }
}
