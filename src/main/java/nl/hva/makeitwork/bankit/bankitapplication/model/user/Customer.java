package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {
    @Column(nullable = false, unique = true)
    int socialSecurityNumber;
    @ManyToMany
    List<Bankaccount> bankaccounts = new ArrayList<>();


    public Customer() {}

    public Customer(int socialSecurityNumber, List<Bankaccount> bankaccounts) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.bankaccounts = bankaccounts;
    }

    public Customer(int id, String username, String password, Person person, ContactDetails contactDetails, int socialSecurityNumber, List<Bankaccount> bankaccounts) {
        super(id, username, password, person, contactDetails);
        this.socialSecurityNumber = socialSecurityNumber;
        this.bankaccounts = bankaccounts;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public List<Bankaccount> getBankaccounts() {
        return bankaccounts;
    }

    public void setBankaccounts(List<Bankaccount> bankaccounts) {
        this.bankaccounts = bankaccounts;
    }
}
