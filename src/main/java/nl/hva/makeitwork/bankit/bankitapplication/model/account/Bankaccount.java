package nl.hva.makeitwork.bankit.bankitapplication.model.account;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@MappedSuperclass
public abstract class Bankaccount implements Comparable<Bankaccount>{
    @Id
    @GeneratedValue
    int accountID = 0;
    @Column(nullable = false, unique = true)
    String iban;
    @Transient
    List<Transaction> history = new ArrayList<>();
    @Column(nullable = false)
    double balance;


    public Bankaccount(String iban, List<Transaction> history, double balance) {
        this.iban = iban;
        this.history = history;
        this.balance = balance;
    }

    public Bankaccount() {
    }

    public void addTransactionHistory(Transaction transaction) {
        if (transaction.getIbanFrom().equals(this.iban)) {
            transaction.setAmount(transaction.getAmount() * -1);
            history.add(transaction);
        } else if (transaction.getIbanTo().equals(this.iban)) {
            history.add(transaction);
        }
    }

    public void updateBalanceAfterTransaction (Double amount){
        this.balance += amount;
    }

    public static String num(int accountID) {
        int result = 1460000 + accountID; // voorbeeld
        String num = "020" + String.valueOf(result); // voorbeeld
        return num;
    }

    public static String numBiz(int accountID) {
        int result = 40200000 + accountID;
        String num = "06" + String.valueOf(result);
        return num;
    }

    // construct numerical IBAN, modulo 97 and subtract this form 98 to generate the check digits (NLxx)
    // https://nl.wikipedia.org/wiki/International_Bank_Account_Number#Landspecifieke_regels
    public static int generateCheckDigits(String accountnumber) {
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
            result = (result * 10 + (int) num.charAt(i) - '0') % 97;
        return 98 - result;
    }

    public static String constructIBAN(int accountID) {
        String preIban = num(accountID);
        return buildIBAN(preIban);
    }

    public static String constructIBANBiz(int accountID) {
        String preIban = numBiz(accountID);
        return buildIBAN(preIban);
    }

    private static String buildIBAN(String preIban) {
        String result;
        int checkD = generateCheckDigits(preIban);
        if (checkD > 9) {
            result = "NL" + checkD + "BAIT" + preIban;
        } else {
            result = "NL0" + checkD + "BAIT" + preIban;
        } // ensure the 01 .. 09
        return result;
    }

    // validate Iban based on dutch accountnumbers (format NL99BANK0123456789)
    public static boolean validateIBAN(String iban) {
        // deconstruct iban to numerical for validating (btw -55 is found on inetrnet Ascii A = 65...)
        // country part
        String c1 = String.valueOf(iban.charAt(0) - 55);
        String c2 = String.valueOf(iban.charAt(1) - 55);
        String c3 = String.valueOf(iban.charAt(2));
        String c4 = String.valueOf(iban.charAt(3));
        // bank part
        String b1 = String.valueOf(iban.charAt(4) - 55);
        String b2 = String.valueOf(iban.charAt(5) - 55);
        String b3 = String.valueOf(iban.charAt(6) - 55);
        String b4 = String.valueOf(iban.charAt(7) - 55);
        // construct numerical parts for NL and BANK
        String ibanCountry = c1 + c2 + c3 + c4;
        String ibanBank = b1 + b2 + b3 + b4;
        // construct numerical iban for checking
        String num = ibanBank + iban.substring(8) + ibanCountry;
        // modulo 97 should generate 1 for a valid (dutch) iban
        int result = 0;
        // One by one process all digits of 'num'
        for (int i = 0; i < num.length(); i++)
        result = (result * 10 + (int) num.charAt(i) - '0') % 97;
        return result == 1;
    }

    @Override
    public String toString() {
        return "Bankaccount{" +
                "accountID=" + accountID +
                ", iban='" + iban + '\'' +
                ", history=" + history +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bankaccount)) return false;
        Bankaccount that = (Bankaccount) o;
        return accountID == that.accountID &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(iban, that.iban) &&
                Objects.equals(history, that.history);
    }

    @Override
    public int compareTo(Bankaccount o) {
        if (this.getBalance() > o.getBalance() ) {
            return -1;
        } else if (this.getBalance() < o.getBalance() ) {
            return 1;
        }
        return 0;
    }

    /**
     * Comperator object die de iban als vergelijker gebruikt
     */
    public static Comparator<Bankaccount> accountnrComperator = new Comparator<Bankaccount>() {
        @Override
        public int compare(Bankaccount b1, Bankaccount b2) {
            String accountNr1 = b1.iban.substring(8);
            String accountNr2 = b2.iban.substring(8);
            return accountNr1.compareTo(accountNr2);
        }
    };


    @Override
    public int hashCode() {
        return Objects.hash(accountID, iban, history, balance);
    }

    public abstract void addAccountHolder(Customer customer);

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int id) {
        this.accountID = id;
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
