package nl.hva.makeitwork.bankit.bankitapplication.model;

public class ContactDetails {
    Address address;
    String email;
    String phoneNumber;

    public ContactDetails(Address address, String email, String phoneNumber) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public ContactDetails() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
