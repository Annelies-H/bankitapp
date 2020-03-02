package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.Address;
import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GenerateUsersService {

    public GenerateUsersService() {}

    public static void createUsers() {
        List<Customer> customers = readFile();
        Integer[] createBSNS = createBSNs(customers.size());
    }

    private static List<Customer> readFile() {
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
                Person person = new Person(row[4], "", row[6], row[5], birthday, row[2]);
                Customer customer = new Customer();
                customer.setContactDetails(contact);
                customer.setPerson(person);
                customer.setUsername(row[12]);
                customer.setPassword(row[13]);
            }
        }
        catch(FileNotFoundException FnF) {
            System.out.println("file not found");
        }
        return customers;
    }

    public static Integer[] createBSNs(int n) {
        Set<Integer> bsns = new HashSet<>();
        while (bsns.size() < n) {
            bsns.add(createBSN());
        }
        return (Integer[]) bsns.toArray();
    }

    public static int createBSN() {
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
