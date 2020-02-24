package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import java.util.Date;

public class Transaction {
    Bankaccount ibanFrom;
    Bankaccount ibanTo;
    double ammount;
    String description;
    Date date;
    PaymentMethod paymentMethod;

    public Transaction(Bankaccount ibanFrom, Bankaccount ibanTo, double ammount, String description, Date date, PaymentMethod paymentMethod) {
        this.ibanFrom = ibanFrom;
        this.ibanTo = ibanTo;
        this.ammount = ammount;
        this.description = description;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public Bankaccount getIbanFrom() {
        return ibanFrom;
    }

    public void setIbanFrom(Bankaccount ibanFrom) {
        this.ibanFrom = ibanFrom;
    }

    public Bankaccount getIbanTo() {
        return ibanTo;
    }

    public void setIbanTo(Bankaccount ibanTo) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
