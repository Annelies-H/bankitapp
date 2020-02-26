package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Bankaccount {
    @Id
    String iban;
    @Transient
    List<Transaction> history = new ArrayList<>();
    @Column(nullable = false)
    double balance;

    public Bankaccount(String iban, List<Transaction> history, double balance) {
        this.iban = iban;
        this.history = history;
        this.balance = balance;
    }

    public Bankaccount() {
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void setHistory(List<Transaction> history) {
        this.history = history;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
