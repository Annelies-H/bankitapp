package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;

public class POSterminal {
    int POSid;
    BusinessAccount account;

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
