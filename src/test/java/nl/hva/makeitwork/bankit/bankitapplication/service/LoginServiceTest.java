package nl.hva.makeitwork.bankit.bankitapplication.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @TestConfiguration
    static class LoginServiceConfiguration {
        @Bean
        public LoginService loginService() {
            return new LoginService();
        }
    }

    @Autowired
    private LoginService loginService;

    @Test
    public void passwordCheckTest() {
        User user;
        boolean predicted;
        boolean actual;

        user = new Customer();
        user.setPassword("wachtwoord");
        actual = loginService.passwordCheck("wachtwoord", user);
        predicted = true;
        assertEquals(actual, predicted);

        user = new Employee();
        user.setPassword("wachtwoord");
        actual = loginService.passwordCheck("wachtwoord", user);
        predicted = true;
        assertEquals(actual, predicted);

        user.setPassword("WachtWoord");
        actual = loginService.passwordCheck("wachtwoord", user);
        predicted = false;
        assertEquals(actual, predicted);

        user.setPassword("wachtwoord");
        actual = loginService.passwordCheck("WachtWoord", user);
        predicted = false;
        assertEquals(actual, predicted);

        user.setPassword("^412jk8Gdas2");
        actual = loginService.passwordCheck("^412jk8Gdas2", user);
        predicted = true;
        assertEquals(actual, predicted);

    }

    @Test
    public void hashPasswordTest() {
        String salt = "b6579bf224d89a4e6ab208af57f7a47f35c0a60c649a8bd2117db6ba944df52718a4cad03e7d69330d350e5561ca398ff20a1d8d2308d177305860789b8b26d9";
        String password = "pietje";
        String actual = LoginService.hashPassword(password, salt);
        String predicted = "bea35ab54985cb489bda01a9f1a361e540bb873fcc65ca8bc838ca8c03ee84f77952555b74f3bcedd680a1f50553a48a73a7673efe28e21234c5d027b2ac1888";
        assertThat(actual.equals(predicted));

        salt = "zout";
        password = "wachtwoord";
        actual = LoginService.hashPassword(password, salt);
        predicted = "a253737281a02370edc0e10ffd10186f23b1c213c813466ed5471da8f0404375eb69b071440e349fa84a0575a175a68bbac3831f19872575d2321e2c6a83bd05";
        assertThat(actual.equals(predicted));

        salt = "(^J@#)&DAS@";
        password = "*61*6SAsjkasd";
        actual = LoginService.hashPassword(password, salt);
        predicted = "a8c0f18e0bd50d6d28237d0d4ff3d565d25ea12245f192c61385af39d93bea04bc738a39150cad081aaba22a2df7baa41ad9593c895f0b845f080fca9f95ef34";
        assertThat(actual.equals(predicted));
    }

}
