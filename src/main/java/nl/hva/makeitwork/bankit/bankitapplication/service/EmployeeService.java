package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Industry;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.EmployeeDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    CompanyDAO companyDAO;
    @Autowired
    BusinessAccountDAO businessAccountDAO;

    public EmployeeService() {
        super();
    }

    public Employee findEmployee(String username) {
        Optional<Employee> employeeOptional = employeeDAO.findEmployeeByUsername(username);
        return employeeOptional.orElse(null);
    }

    public Map<String, Double> getAverageBalanceForAllIndustries() {
        Map<String, Double> result = new HashMap<>();
        List<Industry> industries = Arrays.asList(Industry.values());
        for (Industry industry : industries) {
            Double balance = averageBalanceForIndustry(industry);
            result.put(industry.getAS_STRING(), balance);
        }
        return result;
    }

    public Double averageBalanceForIndustry(Industry industry) {
        List<Company> companies = companyDAO.findAllByIndustry(industry);
        List<BusinessAccount> accounts = new ArrayList<>();
        for (Company company : companies) {
            List<BusinessAccount> companyAccounts = businessAccountDAO.findAllByCompany(company);
            accounts.addAll(companyAccounts);
        }
        double totalBalance = 0.0;
        for (BusinessAccount account : accounts) {
            totalBalance += account.getBalance();
        }
        if (accounts.size() == 0) {
            return 0.0;
        }
        return totalBalance / accounts.size();
    }

}