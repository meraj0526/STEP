public class SecureBankAccount {

    private final String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance >= MIN_BALANCE ? initialBalance : MIN_BALANCE;
        this.pin = 0;  // Must be set separately
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // Account Info Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot show balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // Security Methods
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == 0 || validatePin(oldPin)) {
            this.pin = newPin;
            resetFailedAttempts();
            System.out.println("PIN successfully changed.");
            return true;
        }
        System.out.println("Incorrect old PIN. PIN change failed.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked. Access denied.");
            return false;
        }
        if (enteredPin == pin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            System.out.println("Incorrect PIN.");
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (correctPin == pin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked successfully.");
            return true;
        }
        System.out.println("Incorrect PIN. Cannot unlock account.");
        return false;
    }

    // Transaction Methods
    public boolean deposit(double amount, int pin) {
        if (!validatePin(pin)) {
            return false;
        }
        if (isLocked) {
            System.out.println("Account is locked. Deposit denied.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + balance);
        return true;
    }

    public boolean withdraw(double amount, int pin) {
        if (!validatePin(pin)) {
            return false;
        }
        if (isLocked) {
            System.out.println("Account is locked. Withdrawal denied.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (balance - amount < MIN_BALANCE) {
            System.out.println("Insufficient funds for withdrawal.");
            return false;
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + ". New balance: " + balance);
        return true;
    }

    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (!validatePin(pin)) {
            return false;
        }
        if (isLocked) {
            System.out.println("Account is locked. Transfer denied.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return false;
        }
        if (balance - amount < MIN_BALANCE) {
            System.out.println("Insufficient funds for transfer.");
            return false;
        }
        if (target == null) {
            System.out.println("Invalid target account.");
            return false;
        }
        balance -= amount;
        target.balance += amount;
        System.out.println("Transferred " + amount + " to account " + target.getAccountNumber());
        System.out.println("New balance: " + balance);
        return true;
    }

    // Private helper methods
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed PIN attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        } else {
            System.out.println("Failed attempts: " + failedAttempts);
        }
    }

    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 500);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 1000);

        // Uncommenting below lines will cause compilation errors due to private access
        // acc1.balance = 1000;
        // System.out.println(acc1.pin);

        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);

        // Wrong PIN multiple times to trigger lock
        acc1.withdraw(50, 1111);
        acc1.withdraw(50, 2222);
        acc1.withdraw(50, 3333);

        // Try operations on locked account
        acc1.getBalance();
        acc1.deposit(100, 1234);
        acc1.withdraw(50, 1234);

        // Unlock the account
        acc1.unlockAccount(1234);

        // Transfer money
        acc1.transfer(acc2, 150, 1234);

        // Attempt to withdraw more than balance
        acc2.withdraw(2000, 5678);
    }
}
