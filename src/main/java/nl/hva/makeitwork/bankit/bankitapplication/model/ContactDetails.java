package nl.hva.makeitwork.bankit.bankitapplication.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ContactDetails {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String zipcode;
    @Column(nullable = false)
    private Integer houseNumber;
    private String suffix;


    public ContactDetails() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}