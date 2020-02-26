package nl.hva.makeitwork.bankit.bankitapplication.model.company;

import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    @Column(nullable=false)
    String name;
    @Column(nullable=false)
    String sector;
    @Id
    int company;
    @Embedded
    @Column(nullable=false)
    ContactDetails contactDetails;

    public Company() {
    }
    public Company(String name, String sector, int company, ContactDetails contactDetails) {
        this.name = name;
        this.sector = sector;
        this.company = company;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
