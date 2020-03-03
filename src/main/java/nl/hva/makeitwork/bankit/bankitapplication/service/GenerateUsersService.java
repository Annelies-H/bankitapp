package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.Address;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.dao.CustomerDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class GenerateUsersService {

    @Autowired
    private CustomerDAO customerDAO;

    public GenerateUsersService() {super();}

    public void createUsers() {
        List<Customer> customers = readFile();
        List<Integer> bsns = createBSNs(customers.size());
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            customer.setSocialSecurityNumber(bsns.get(i));
            customerDAO.save(customer);
        }
    }

    private List<Customer> readFile() {
        List<Customer> customers = new ArrayList<>();
        String filePath = "documentation/FakeNames.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                Address address = new Address(row[7], 1, "", row[9], row[8], "Nederland");
                ContactDetails contact = new ContactDetails(address, row[11], row[14]);
                Calendar birthday = Calendar.getInstance();
                birthday.set(1800, 01,01);
                Person person = new Person(row[4], "", row[6], row[5], birthday, row[1]);
                Customer customer = new Customer();
                customer.setContactDetails(contact);
                customer.setPerson(person);
                customer.setUsername(row[12]);
                customer.setPassword(row[13]);
//                Address address = new Address("de Lange Lindelaan", 101, "1245ZZ", "Utrecht", "Nederland");
//                ContactDetails contact = new ContactDetails(address, "LiesjeLeerd@LotjeLopen", "1234567890");
//                Calendar birthday = Calendar.getInstance();
//                birthday.set(1933, 11,30);
//                Person person = new Person("Liesje", "van", "Jansen", "LL", birthday, "v");
//                Customer customer = new Customer();
//                customer.setSocialSecurityNumber(123456789);
//                customer.setContactDetails(contact);
//                customer.setPerson(person);
//                customer.setUsername("KBoer01");
//                customer.setPassword("W3lk0m2o2o");
                customers.add(customer);
            }
        }
        catch(FileNotFoundException FnF) {
            System.out.println("file not found");
        }
        customers.remove(0);
        return customers;
    }

    public List<Integer> createBSNs(int n) {
        Set<Integer> bsns = new HashSet<>();
        while (bsns.size() < n) {
            bsns.add(createBSN());
        }
        return new ArrayList<>(bsns);
    }

    public int createBSN() {
        Random r = new Random();
        int nr = 1;
        Customer c = new Customer();
        while (!c.isValidBSN(nr)) {
            nr = r.ints(1, (int) Math.pow(10, c.MIN_DIGITS_BSN - 1),
                    (int) Math.pow(10, c.MAX_DIGITS_BSN)).findFirst().getAsInt();
        }
        return nr;
    }

}
