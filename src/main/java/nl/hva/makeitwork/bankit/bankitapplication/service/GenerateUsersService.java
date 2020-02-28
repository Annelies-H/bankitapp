package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import java.util.Random;

public class GenerateUsersService {

    public GenerateUsersService() {}


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
