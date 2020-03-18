package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class BusinessAccount extends Bankaccount {
    @ManyToOne
    Company company;
    @Transient
    List<Customer> accountHolders = new ArrayList<>();


    public BusinessAccount(String iban, List<Transaction> history, double balance) {
        super(iban, history, balance);
    }

    public BusinessAccount() {
    }

    public void addAccountHolder(Customer accountHolder) {
        accountHolders.add(accountHolder);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Customer> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }
}
