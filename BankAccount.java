public class BankAccount {
    // Instance variables: unique to each object
    private String accountHolderName;
    private double balance;

    // Static variable: shared among all instances
    private static int numberOfAccounts = 0;

    // Constructor
    public BankAccount(String name, double initialBalance) {
        this.accountHolderName = name;
        this.balance = initialBalance;
        numberOfAccounts++;  // Increment static count whenever a new account is created
    }

    // Instance method: works on individual object data
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited to " + accountHolderName + "'s account.");
        }
    }

    // Instance method: shows balance for the particular account
    public void showBalance() {
        System.out.println(accountHolderName + "'s balance: " + balance);
    }

    // Static method: works on static data, does not have access to instance variables
    public static int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    // Main method to demonstrate
    public static void main(String[] args) {
        // Create two bank accounts (instances)
        BankAccount acc1 = new BankAccount("Alice", 1000);
        BankAccount acc2 = new BankAccount("Bob", 500);

        // Use instance methods
        acc1.deposit(200);
        acc2.deposit(300);

        acc1.showBalance();
        acc2.showBalance();

        // Use static method and variable
        System.out.println("Total Bank Accounts created: " + BankAccount.getNumberOfAccounts());
    }
}
