package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import java.util.Date;

public class Person {
    String firstName;
    String prefix;
    String lastName;
    String initials;
    Date birthday;
    String gender;

    public Person(String firstName, String prefix, String lastName, String initials, Date birthday, String gender) {
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.initials = initials;
        this.birthday = birthday;
        this.gender = gender;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
