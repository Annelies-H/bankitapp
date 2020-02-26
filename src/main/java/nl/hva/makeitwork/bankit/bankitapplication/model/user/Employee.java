package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;

@Entity
public class Employee extends User {
    @Id
    int id = 1;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    Position position;

    public Employee() {}

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
