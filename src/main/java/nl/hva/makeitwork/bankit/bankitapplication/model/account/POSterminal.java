package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class POSterminal {
    @Id
    @GeneratedValue
    int POSid;
    @ManyToOne
    BusinessAccount account;

    public POSterminal() {}

    public POSterminal(int POSid, BusinessAccount account) {
        this.POSid = POSid;
        this.account = account;
    }

    public int getPOSid() {
        return POSid;
    }

    public void setPOSid(int POSid) {
        this.POSid = POSid;
    }

    public BusinessAccount getAccount() {
        return account;
    }

    public void setAccount(BusinessAccount account) {
        this.account = account;
    }
}
