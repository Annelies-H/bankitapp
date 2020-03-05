package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

import javax.persistence.*;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue
    int id;
    @Column(nullable=false, unique = true)
    String username;
    @Column(nullable=false)
    String password;
    @Embedded
    Person person;
    @Column(nullable=false)
    ContactDetails contactDetails;

    public User() {
    }

    public User(int id, String username, String password, Person person, ContactDetails contactDetails) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.person = person;
        this.contactDetails = contactDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
