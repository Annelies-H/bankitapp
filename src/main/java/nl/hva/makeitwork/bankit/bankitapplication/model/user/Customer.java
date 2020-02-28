package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    @Transient
    public static final int MIN_DIGITS_BSN = 8;
    @Transient
    public static final int MAX_DIGITS_BSN = 9;

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

    /**
     *
     * @param bsn to be validated
     * @return whether the bsn is valid
     */
    public static boolean isValidBSN(int bsn) {
        if (intLength(bsn) == MIN_DIGITS_BSN || intLength(bsn) == MAX_DIGITS_BSN) {
            int sum = -1 * (bsn % 10);
            int rest = bsn / 10;
            for (int i = 2; i <= 9; i++) {
                sum += i * (rest % 10);
                rest /= 10;
            }
            return sum % 11 == 0;
        }
        return false;
    }

    /**
     * Calculate the number of digits in a positive number
     * @param number  positive int
     * @return the number of digits (-1 when attempting to enter a negative number)
     */
    public static int intLength(double number) {
        if (number > 0) {
            return (int) Math.log10(number) + 1;
        }
        if (number == 0) {
            return 1;
        }
        return -1;
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
