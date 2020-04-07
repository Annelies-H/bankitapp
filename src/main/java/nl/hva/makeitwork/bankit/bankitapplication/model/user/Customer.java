package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends User {

    @Transient
    public static final int BSN_RANGE_MIN_VALUE = 10000000;
    @Transient
    public static final int BSN_RANGE_MAX_VALUE = 999999999;

    @Column(nullable = false, unique = true)
    private Integer socialSecurityNumber;
    @ManyToMany
    private List<PrivateAccount> privateAccounts = new ArrayList<>();
    @ManyToMany
    private List<BusinessAccount> businessAccounts = new ArrayList<>();


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

    public Customer(int socialSecurityNumber, List<PrivateAccount> privateAccounts, List<BusinessAccount> businessAccounts) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.privateAccounts = privateAccounts;
        this.businessAccounts = businessAccounts;
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

    public void addAccount(Bankaccount account) {
        if (account instanceof PrivateAccount) {
            privateAccounts.add((PrivateAccount) account);
        } else {
            businessAccounts.add((BusinessAccount) account);
        }
    }

    public void sortAccountsOnAccountnr() {
        privateAccounts.sort(Bankaccount.accountnrComperator);
        businessAccounts.sort(Bankaccount.accountnrComperator);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstName);
        if (prefix != null) {
            stringBuilder.append(" " + prefix);
        }
        stringBuilder.append(" " + lastName);

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return socialSecurityNumber.equals(customer.socialSecurityNumber) &&
                privateAccounts.equals(customer.privateAccounts) &&
                businessAccounts.equals(customer.businessAccounts) &&
                firstName.equals(customer.firstName) &&
                Objects.equals(prefix, customer.prefix) &&
                lastName.equals(customer.lastName) &&
                birthday.equals(customer.birthday) &&
                gender.equals(customer.gender) &&
                contactDetails.equals(customer.contactDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurityNumber, privateAccounts, businessAccounts, firstName, prefix, lastName, birthday, gender, contactDetails);
    }

    public Integer getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(Integer socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public List<PrivateAccount> getPrivateAccounts() {
        return privateAccounts;
    }

    public void setPrivateAccounts(List<PrivateAccount> privateAccounts) {
        this.privateAccounts = privateAccounts;
    }

    public List<BusinessAccount> getBusinessAccounts() {
        return businessAccounts;
    }

    public void setBusinessAccounts(List<BusinessAccount> businessAccounts) {
        this.businessAccounts = businessAccounts;
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




