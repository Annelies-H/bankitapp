package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    @Transient
    public static final int BSN_RANGE_MIN_VALUE = 10000000;
    @Transient
    public static final int BSN_RANGE_MAX_VALUE = 999999999;

    @Column(nullable = false, unique = true)
    private Integer socialSecurityNumber;
    @ManyToMany
    private List<Bankaccount> bankaccounts = new ArrayList<>();


    @Column(nullable = false)
    private String firstName;
    private String prefix;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String birthday;
    @Column(nullable = false)
    private String gender;
    @Embedded
    @Column(nullable = false)
    private ContactDetails contactDetails;


    public Customer() {
    }

    public Customer(int socialSecurityNumber, List<Bankaccount> bankaccounts) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.bankaccounts = bankaccounts;
    }

    public Customer(String firstName, String prefix, String lastName, String birthday, String gender, ContactDetails contactDetails) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.contactDetails = contactDetails;
    }

    /**
     * A valid BSN is of the correct length (8 or 9 digits) and adheres to the 'elfproef' (a type of check digit).
     * This means that the weighed sum of all the digits is a multiple of 11.
     * The last digits as a weight of -1, the 2nd last a weight of 2, the 3rd last a weight of 3 etc.
     * Only the last digit has a negative weight.
     *
     * @param bsn to be validated
     * @return whether the bsn is valid
     */
    public static boolean isValidBSN(int bsn) {
        if (bsn >= BSN_RANGE_MIN_VALUE && bsn <= BSN_RANGE_MAX_VALUE) {
            int sum = -1 * (bsn % 10);
            int remainder = bsn / 10;
            for (int i = 2; i <= 9; i++) {
                sum += i * (remainder % 10);
                remainder /= 10;
            }
            return sum % 11 == 0;
        }
        return false;
    }

    public Integer getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(Integer socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public List<Bankaccount> getBankaccounts() {
        return bankaccounts;
    }

    public void setBankaccounts(List<Bankaccount> bankaccounts) {
        this.bankaccounts = bankaccounts;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }


    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}




