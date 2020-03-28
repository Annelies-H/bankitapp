package nl.hva.makeitwork.bankit.bankitapplication.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.Test;
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
    public void hashPasswordTest() {
        String salt = "b6579bf224d89a4e6ab208af57f7a47f35c0a60c649a8bd2117db6ba944df52718a4cad03e7d69330d350e5561ca398ff20a1d8d2308d177305860789b8b26d9";
        String password = "pietje";
        String predicted = LoginService.hashPassword(password, salt);
        String actual = "bea35ab54985cb489bda01a9f1a361e540bb873fcc65ca8bc838ca8c03ee84f77952555b74f3bcedd680a1f50553a48a73a7673efe28e21234c5d027b2ac1888";
        assertThat(actual = predicted);

        salt = "zout";
        password = "wachtwoord";
        predicted = LoginService.hashPassword(password, salt);
        actual = "a253737281a02370edc0e10ffd10186f23b1c213c813466ed5471da8f0404375eb69b071440e349fa84a0575a175a68bbac3831f19872575d2321e2c6a83bd05";
        assertThat(actual = predicted);
    }



}
