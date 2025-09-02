class BankAccount {
    // Private instance variables
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Static variables
    private static int totalAccounts = 0;
    private static int accountCounter = 0;

    // Constructor
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    // Instance methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Static methods
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter);
    }

    // Display method
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("-----------------------------");
    }
}

// Main class
public class BankAccountManagement {
    public static void main(String[] args) {
        // Array of BankAccount objects
        BankAccount[] accounts = new BankAccount[3];

        // Creating accounts
        accounts[0] = new BankAccount("Alice", 1000);
        accounts[1] = new BankAccount("Bob", 500);
        accounts[2] = new BankAccount("Charlie", 2000);

        // Display account info
        System.out.println("=== Account Details ===");
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        // Performing transactions
        System.out.println("=== Transactions ===");
        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].withdraw(2500); // Insufficient funds

        // Checking balances
        System.out.println("=== Balance Check ===");
        for (BankAccount acc : accounts) {
            acc.checkBalance();
        }

        // Static vs Instance demonstration
        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
    }
}
