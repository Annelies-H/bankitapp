package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class AddAccountholderRequest {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    String iban;
    @Column(nullable = false)
    private int bsn;
    @Column(nullable = false)
    private int secretCode;

    public AddAccountholderRequest() {}

    public AddAccountholderRequest(String iban, int bsn, int secretCode) {
        this.iban = iban;
        this.bsn = bsn;
        this.secretCode = secretCode;
    }

    @Override
    public String toString() {
        return "AddAccountholderRequest{" +
                "account=" + iban +
                ", accountholder=" + bsn +
                ", secretCode=" + secretCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddAccountholderRequest)) return false;
        AddAccountholderRequest that = (AddAccountholderRequest) o;
        return secretCode == that.secretCode &&
                Objects.equals(iban, that.iban) &&
                Objects.equals(bsn, that.bsn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, bsn, secretCode);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getBsn() {
        return bsn;
    }

    public void setBsn(int accountholder) {
        this.bsn = accountholder;
    }

    public int getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(int secretCode) {
        this.secretCode = secretCode;
    }
}
