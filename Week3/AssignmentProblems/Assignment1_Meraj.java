import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;
    private List<String> transactions;

    private static int totalAccounts = 0;
    private static String bankName;

    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.transactions = new ArrayList<>();
        totalAccounts++;
    }

    public void addIncome(double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Income amount must be positive.");
        }
        currentBalance += amount;
        totalIncome += amount;
        transactions.add("Income: +" + amount + " | " + description);
    }

    public void addExpense(double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Expense amount must be positive.");
        }
        if (amount > currentBalance) {
            throw new IllegalArgumentException("Insufficient balance for this expense.");
        }
        currentBalance -= amount;
        totalExpenses += amount;
        transactions.add("Expense: -" + amount + " | " + description);
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("-------------------------------------------------");
        System.out.println("Bank Name: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
        System.out.println("Transaction History:");
        for (String t : transactions) {
            System.out.println(" - " + t);
        }
        System.out.println("-------------------------------------------------");
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "ACC" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

public class Assignment1_Meraj {
    public static void main(String[] args) {
        PersonalAccount.setBankName("OpenAI Bank");

        PersonalAccount acc1 = new PersonalAccount("Alice");
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1200, "Rent");
        acc1.addExpense(500, "Groceries");

        PersonalAccount acc2 = new PersonalAccount("Bob");
        acc2.addIncome(7000, "Freelance Work");
        acc2.addExpense(2000, "Laptop Purchase");

        PersonalAccount acc3 = new PersonalAccount("Charlie");
        acc3.addIncome(6000, "Consulting Fee");
        acc3.addExpense(1500, "Travel");
        acc3.addExpense(800, "Utilities");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());
    }
}
