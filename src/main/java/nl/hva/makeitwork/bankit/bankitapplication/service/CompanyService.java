package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Transaction;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.CompanyDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private BusinessAccountDAO businessAccountDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    public List<Company> getAllCompanies() {
        Iterable<Company> companyIterable = companyDAO.findAll();
        Iterator<Company> companyIterator = companyIterable.iterator();
        List<Company> companies = new ArrayList<>();
        while (companyIterator.hasNext()) {
            companies.add(companyIterator.next());
        }
        return companies;
    }

    public List<Company> getTop10CompaniesBalance() {

        List<Company> companies = getAllCompanies();

        for (Company company : companies) {
            company.setTotalBalance(bankAccountService.getTotalBalanceCompany(company));
        }

        Collections.sort(companies);

        if (companies.size() > 10) {
            companies = companies.subList(0,10);
        }

        return companies;
    }

    public List<Company> getTop10CompaniesTransactions() {
        List<BusinessAccount> businessAccounts = bankAccountService.getAllBusinessAccounts();
        List<Company> companies = getAllCompanies();
        Iterable<Transaction> transactionIterable = transactionDAO.findAll();
        Iterator<Transaction> transactionIterator = transactionIterable.iterator();
        List<Transaction> transactions = new ArrayList<>();
        while (transactionIterator.hasNext()) {
            transactions.add(transactionIterator.next());
        }

        int count = 0;

        for(Company company : companies) {
            count = 0;

            for (BusinessAccount businessAccount : businessAccounts) {
                if (businessAccount.getCompany().equals(company)) {
                    for (Transaction transaction : transactions) {
                        if (transaction.getIbanTo().equals(businessAccount.getIban()) || transaction.getIbanFrom().equals(businessAccount.getIban())) {
                            count++;
                        }
                    }
                }
            }

            company.setNumberOfTransactions(count);
        }

        Company.TransactionComparator comparator = new Company.TransactionComparator();
        companies.sort(comparator);

        if (companies.size() > 10) {
            companies = companies.subList(0,10);
        }

        return companies;

    }


}
