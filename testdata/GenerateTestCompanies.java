package nl.hva.makeitwork.bankit.bankitapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class GenerateTestCompanies {
    static final String FILE_PATH = "documentation/test_companies.csv";
    static final int KVK_INDEX = 1;
    static final int COMPANY_NAME_INDEX = 2;
    static final int INDUSTRY_INDEX = 3;

    @Autowired
    private CompanyDAO companyDAO;

    public GenerateTestCompanies(CompanyDAO companyDAO) {super(); }


    public void createCompanies() {
        List<Company> companies = readFile();
        for (int i = 0; i < companies.size(); i++) {
            companyDAO.save(company);
        }
    }
    private List<Company> readFile() {
        List<Company> companies = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                Company company = parseCompany(row);
                company.add(company);
            }
        }
        catch(FileNotFoundException FnF) {
            System.out.println("file not found");
        }
        return companies;
    }

    private Company parseCompany(String[] row) {
        Company company = new Company();
        company.setCompanyID(row[KVK_INDEX]);
        company.setName(row[COMPANY_NAME_INDEX]);
        company.setIndustry(row[INDUSTRY_INDEX])
        return company;
    }


}
