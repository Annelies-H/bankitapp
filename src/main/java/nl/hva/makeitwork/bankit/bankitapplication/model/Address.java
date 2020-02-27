package nl.hva.makeitwork.bankit.bankitapplication.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(nullable=false)
    String street;
    @Column(nullable=false)
    int number;
    String suffix;
    @Column(nullable=false)
    String zipcode;
    @Column(nullable=false)
    String city;
    @Column(nullable=false)
    String country;

    public Address() {
    }

    public Address(String street, int number, String zipcode, String city, String country) {
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public Address(String street, int number, String suffix, String zipcode, String city, String country) {
        this.street = street;
        this.number = number;
        this.suffix = suffix;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
