package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    int TransactionID;
    @Column(nullable = false)
    String ibanFrom;
    @Column(nullable = false)
    String ibanTo;
    @Column(nullable = false)
    double ammount;
    String description;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Calendar date;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    PaymentMethod paymentMethod;

    public Transaction() {}

    public Transaction(String ibanFrom, String ibanTo, double ammount, String description, Calendar date, PaymentMethod paymentMethod) {
        this.ibanFrom = ibanFrom;
        this.ibanTo = ibanTo;
        this.ammount = ammount;
        this.description = description;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public String getIbanFrom() {
        return ibanFrom;
    }

    public void setIbanFrom(String ibanFrom) {
        this.ibanFrom = ibanFrom;
    }

    public String getIbanTo() {
        return ibanTo;
    }

    public void setIbanTo(String ibanTo) {
        this.ibanTo = ibanTo;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
