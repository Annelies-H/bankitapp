// Created by huub
// Creation date 2020-02-23

package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.Address;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.company.Company;
import nl.hva.makeitwork.bankit.bankitapplication.model.dao.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//Service bevat methodes die je in de controller gebruikt, je kan ze ook in de controller stoppen
//maar op deze manier kun je ze makkeijker hergebruiken tussen controllers
public class FillDatabaseService {

  @Autowired
  private CompanyDAO companyDAO;
  //spring zorgt dat er een clubmemberdao object komt

  public FillDatabaseService() {
    super();
  }

  public void fillDatabase() {
    Address address = new Address("straat", 101, "b", "1234AB", "Hilversum", "Nederland");
    ContactDetails contact = new ContactDetails(address, "info@bankit.nl", "0612345678");
    Company company = new Company("BankIT", "Financiele dienstverlening", 12345, contact );
    companyDAO.save(company);
    System.out.println("**** Hier wordt de db gevuld");
  }




}
