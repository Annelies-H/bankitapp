package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.User;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class LoginService {

    public boolean passwordCheck(String inputPassword, User user) {
        String hashedInput = hashPassword(inputPassword, user.getSalt());
        return user.getPassword().equals(hashedInput);
    }

    public boolean passwordCheckNoHash(String inputPassword, User user) {
        return user.getPassword().equals(inputPassword);
    }

    public static String hashPassword(String password, String salt) {
        String hashedPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            String pwAndSalt = password + salt;
            byte[] digestPassword = messageDigest.digest(pwAndSalt.getBytes());

            messageDigest.update(digestPassword);

            hashedPassword = Base64.getEncoder().encodeToString(digestPassword);

        } catch(NoSuchAlgorithmException error) {
            System.out.println("Hash error" + error);
        }
        return hashedPassword;
    }

    public static String newSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

}