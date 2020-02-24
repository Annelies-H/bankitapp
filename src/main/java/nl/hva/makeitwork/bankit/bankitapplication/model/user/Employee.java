package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

public class Employee extends User {
    Position position;

    public Employee(Position position) {
        this.position = position;
    }

    public Employee(int id, String username, String password, Person person, ContactDetails contactDetails, Position position) {
        super(id, username, password, person, contactDetails);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
