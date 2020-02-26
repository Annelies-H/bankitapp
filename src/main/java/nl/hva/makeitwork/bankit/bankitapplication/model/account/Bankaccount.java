package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import java.util.ArrayList;
import java.util.List;

public abstract class Bankaccount {
    String iban;
    List<Transaction> history = new ArrayList<>();
    double balance;

    public Bankaccount(String iban, List<Transaction> history, double balance) {
        this.iban = iban;
        this.history = history;
        this.balance = balance;
    }

    public Bankaccount() {
    }

    // construct numerical IBAN, modulo 97 and subtract this form 98 to generate the check digits (NLxx)
    // https://nl.wikipedia.org/wiki/International_Bank_Account_Number#Landspecifieke_regels
     public static int generateCheckDigits(String accountnumber){
        // construct numerical IBAN, where part1 is BAIT and part3 is NL00
        // part 1 and 3 should be final Strings
        // final String IBAN_BANK = "11101829";
        // final String IBAN_COUNTRY = "232100";
        // String num = IBAN_BANK + accountnumber + IBAN_COUNTRY;
        String num = "11101829" + accountnumber + "232100";
        // Initialize result
        int result = 0;
        // One by one process all digits of 'num'
        for (int i = 0; i < num.length(); i++)
            result = (result * 10 + (int)num.charAt(i)- '0') % 97;
        return 98 - result ;
    }
    // validate Iban based on dutch accountnumbers (format NL99BANK0123456789)
     public static boolean validateIBAN (String iban){
        // deconstruct iban to numerical for validating (btw -55 is found on inetrnet Ascii A = 65...)
        // country part
        String c1 = String.valueOf(iban.charAt(0)-55);
        String c2 = String.valueOf(iban.charAt(1)-55);
        String c3 = String.valueOf(iban.charAt(2));
        String c4 = String.valueOf(iban.charAt(3));
        // bank part
        String b1 = String.valueOf(iban.charAt(4)-55);
        String b2 = String.valueOf(iban.charAt(5)-55);
        String b3 = String.valueOf(iban.charAt(6)-55);
        String b4 = String.valueOf(iban.charAt(7)-55);
        // construct numerical parts for NL and BANK
        String ibanCountry = c1+c2+c3+c4;
        String ibanBank = b1+b2+b3+b4;
        // construct numerical iban for checking
        String num = ibanBank + iban.substring(8) + ibanCountry;
        // modulo 97 should generate 1 for a valid (dutch) iban
        int result = 0;
        // One by one process all digits of 'num'
        for (int i = 0; i < num.length(); i++)
            result = (result * 10 + (int)num.charAt(i)- '0') % 97;

        return result == 1;
    }



    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void setHistory(List<Transaction> history) {
        this.history = history;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
