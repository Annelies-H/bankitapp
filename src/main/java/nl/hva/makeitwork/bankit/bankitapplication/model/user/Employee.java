package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import javax.persistence.*;

@Entity
public class Employee extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    public Employee() {
    }

    public Employee(int userId, String username, String password, Position position) {
        super(userId, username, password);
        this.position = position;
    }

    public Employee(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
