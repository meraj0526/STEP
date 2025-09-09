import java.util.Random;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    public BankAccount() {
        this("Unknown", 0.0);
    }

    public BankAccount(String accountHolder) {
        this(accountHolder, generateRandomAccountNumber(), 0.0);
    }

    public BankAccount(String accountHolder, double balance) {
        this(accountHolder, generateRandomAccountNumber(), balance);
    }

    public BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void displayAccount() {
        System.out.println("----- Bank Account -----");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
        System.out.println("------------------------\n");
    }

    private static int generateRandomAccountNumber() {
        return 100000 + new Random().nextInt(900000);
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount("Alice");
        BankAccount a3 = new BankAccount("Bob", 1500.0);

        a1.deposit(500);
        a2.deposit(1000);
        a2.withdraw(300);
        a3.withdraw(200);

        a1.displayAccount();
        a2.displayAccount();
        a3.displayAccount();
    }
}
