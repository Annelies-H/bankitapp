package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenerateCompaniesServiceTest {

    @Autowired
    GenerateCompaniesService generateCompaniesService;

    @Test
    void parseCompanyTest() {
        String[] row = {"57551640","A Associates", "HEALTH"};
        Company company = generateCompaniesService.parseCompany(row);
        int actual = company.getCompanyId();
        int expected = 57551640;
        assertEquals(actual, expected);
    }
}