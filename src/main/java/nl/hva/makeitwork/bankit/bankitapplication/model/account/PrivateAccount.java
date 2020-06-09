package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PrivateAccount extends Bankaccount {
    @ManyToMany
    @JoinTable(
            name = "customer_private_accounts",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
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

    public String showAccountHolders() {
        if (accountHolders.size() == 0) {return "";}
        String result = "";
        result += accountHolders.get(0).getFullName();
        for (int i = 1; i < accountHolders.size(); i++) {
            result = result + ", " + accountHolders.get(i).getFullName();
        }
        return result;
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
