package nl.hva.makeitwork.bankit.bankitapplication.model.user;

import nl.hva.makeitwork.bankit.bankitapplication.service.LoginService;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String salt;

    public User() {
    }

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                salt.equals(user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, salt);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        setSalt();
        this.password = LoginService.hashPassword(password, salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        this.salt = LoginService.newSalt();
    }
}
