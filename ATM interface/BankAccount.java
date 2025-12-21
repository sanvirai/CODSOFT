/**
 * BankAccount class represents a user's bank account
 * It stores the account balance and provides methods to modify it
 */
public class BankAccount {
    private double balance;

    /**
     * Constructor to initialize the account with an initial balance
     * @param initialBalance The starting balance for the account
     */
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Get the current account balance
     * @return The current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposit money into the account
     * @param amount The amount to deposit (must be positive)
     * @return true if deposit is successful, false otherwise
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    /**
     * Withdraw money from the account
     * @param amount The amount to withdraw (must be positive and not exceed balance)
     * @return true if withdrawal is successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

