package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;



    public List<Company> getTop10Businesses() {

        // Make a list with companies
        Iterable<Company> iterable = companyDAO.findAll();
        Iterator<Company> iterator = iterable.iterator();
        List<Company> companies = new ArrayList<>();
        while (iterator.hasNext()) {
            companies.add(iterator.next());
        }


        return companies;
    }

}
