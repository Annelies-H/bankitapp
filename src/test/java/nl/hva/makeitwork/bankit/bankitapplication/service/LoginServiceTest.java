package nl.hva.makeitwork.bankit.bankitapplication.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

    //@Autowired
    private LoginService loginService = new LoginService();

    @Test
    public void passwordCheckTest() {
        User user;
        boolean actual;

        user = new Customer();
        user.setPassword("wachtwoord");
        actual = loginService.passwordCheck("wachtwoord", user);
        assertTrue(actual);

        user = new Employee();
        user.setPassword("wachtwoord");
        actual = loginService.passwordCheck("wachtwoord", user);
        assertTrue(actual);

        user.setPassword("WachtWoord");
        actual = loginService.passwordCheck("wachtwoord", user);
        assertFalse(actual);

        user.setPassword("wachtwoord");
        actual = loginService.passwordCheck("WachtWoord", user);
        assertFalse(actual);

        user.setPassword("^412jk8Gdas2");
        actual = loginService.passwordCheck("^412jk8Gdas2", user);
        assertTrue(actual);


    }

    @Test
    public void hashPasswordTest() {
        String salt = "MrqWwaQPHwEDlk2TFuX1Vg==";
        String password = "pietje";
        String actual = LoginService.hashPassword(password, salt);
        String predicted = "EdHcJ/D8r82TZZPTo2Kkd/LqLf8pTRZAtythQqxzt2L4x28VmaeZ8I4bgSv/5kaVPlKN/SrJsb/gZDaQYPCQwA==";
        assertEquals(actual, predicted);

        salt = "zout";
        password = "wachtwoord";
        actual = LoginService.hashPassword(password, salt);
        predicted = "olNzcoGgI3DtwOEP/RAYbyOxwhPIE0Zu1UcdqPBAQ3XrabBxRA40n6hKBXWhdaaLusODHxmHJXXSMh4saoO9BQ==";
        assertEquals(actual, predicted);

        salt = "(^J@#)&DAS@";
        password = "*61*6SAsjkasd";
        actual = LoginService.hashPassword(password, salt);
        predicted = "OkTtOQqk7LAkkmcCeEGysv8F52UDtK1kJCOaYlfWU4G8OfZW/7CaKuYNWv9AAnPYDmw+G/6QYM+84zE55FK62Q==";
        assertEquals(actual, predicted);
    }



}
