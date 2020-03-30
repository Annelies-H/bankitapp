package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class POSterminal {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "POS_sequence"),
                    @Parameter(name = "initial_value", value = "10000000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    ) //Een 8 cijferig ID voor de pinautomaat, mag pas na koppeling doorgegeven worden aan de automaat.
    int POSid;
    @ManyToOne
    BusinessAccount account;

    public POSterminal() {
    }

    public POSterminal(int POSid, BusinessAccount account) {
        this.POSid = POSid;
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof POSterminal)) return false;
        POSterminal that = (POSterminal) o;
        return POSid == that.POSid &&
                account.equals(that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(POSid, account);
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
