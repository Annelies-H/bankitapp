package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class BusinessAccount extends Bankaccount {
    @ManyToOne
    Company company;
    @ManyToMany
    List<Customer> accountHolders = new ArrayList<>();

    public BusinessAccount(String iban, List<Transaction> history, double balance) {
        super(iban, history, balance);
    }

    public BusinessAccount() {
    }

    public void addAccountHolder(Customer accountHolder) {
        accountHolders.add(accountHolder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessAccount)) return false;
        if (!super.equals(o)) return false;
        BusinessAccount that = (BusinessAccount) o;
        return company.equals(that.company) &&
                accountHolders.equals(that.accountHolders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, accountHolders);
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
