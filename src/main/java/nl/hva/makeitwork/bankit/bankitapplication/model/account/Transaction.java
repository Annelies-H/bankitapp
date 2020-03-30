package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

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
    double amount;
    String description;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Calendar date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PaymentMethod paymentMethod;

    public Transaction() {
    }

    public Transaction(String ibanFrom, String ibanTo, double amount, String description, Calendar date, PaymentMethod paymentMethod) {
        this.ibanFrom = ibanFrom;
        this.ibanTo = ibanTo;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return TransactionID == that.TransactionID &&
                Double.compare(that.amount, amount) == 0 &&
                ibanFrom.equals(that.ibanFrom) &&
                ibanTo.equals(that.ibanTo) &&
                description.equals(that.description) &&
                date.equals(that.date) &&
                paymentMethod == that.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TransactionID, ibanFrom, ibanTo, amount, description, date, paymentMethod);
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double ammount) {
        this.amount = ammount;
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
