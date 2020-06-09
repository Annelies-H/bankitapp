package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Industry;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class GenerateCompaniesService {
    static final String FILE_PATH = "testdata/test_companies.csv";
    static final int KVK_INDEX = 1;
    static final int COMPANY_NAME_INDEX = 2;
    static final int INDUSTRY_INDEX = 3;

    @Autowired
    private CompanyDAO companyDAO;

    public GenerateCompaniesService(CompanyDAO companyDAO) {super(); }


    public void createCompanies() {
        List<Company> companies = readFile();
        for (Company company : companies) {
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
                companies.add(company);
            }
        }
        catch(FileNotFoundException FnF) {
            System.out.println("file not found");
        }
        return companies;
    }

    public Company parseCompany(String[] row) {
        Company company = new Company();

        int number = Integer.parseInt(row[KVK_INDEX - 1]);
        company.setCompanyId(number);
        company.setName(row[COMPANY_NAME_INDEX - 1]);
        company.setIndustry(Industry.valueOf(row[INDUSTRY_INDEX - 1]));

        return company;
    }


}
