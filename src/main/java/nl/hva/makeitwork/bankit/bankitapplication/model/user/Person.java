package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class Person {
    @Column(nullable=false)
    String firstName;
    String prefix;
    @Column(nullable=false)
    String lastName;
    @Column(nullable=false)
    String initials;
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    Calendar birthday;
    @Column(nullable=false)
    String gender;

    public Person() {}

    public Person(String firstName, String prefix, String lastName, String initials, Calendar birthday, String gender) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.initials = initials;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String toString() {
        String firstN = "\nvoornaam: " + firstName;
        String  pref = "\nprefix: " + prefix;
        String lastN = "\nlastname: " + lastName;
        String init = "\ninitials: " + initials;
        String bday = "\nbirthday: " + birthday.getTime();
        String gder = "\ngender: " + gender;
        return firstN + pref + lastN + init + bday + gder;
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

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
