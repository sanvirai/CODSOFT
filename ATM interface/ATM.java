import java.util.Scanner;

/**
 * ATM class represents an ATM machine
 * It provides a console-based interface for banking operations
 */
public class ATM {
    private BankAccount account;
    private Scanner scanner;
    private String accountNumber; // Store account number
    private String pin; // Store PIN
    private boolean pinCreated; // Track if PIN has been created

    /**
     * Constructor to initialize the ATM with a bank account
     * @param account The bank account to connect to
     */
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
        this.pinCreated = false; // PIN not created initially
        this.accountNumber = null;
        this.pin = null;
    }

    /**
     * Display the main menu options
     */
    public void displayMenu() {
        System.out.println("\n========== ATM MENU ==========");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.println("==============================");
        System.out.print("Please select an option: ");
    }

    /**
     * Handle withdrawal operation
     */
    public void withdraw() {
        System.out.print("\nEnter amount to withdraw: $");
        
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            
            if (amount <= 0) {
                System.out.println("❌ Error: Withdrawal amount must be positive!");
                return;
            }
            
            if (account.withdraw(amount)) {
                System.out.println("✅ Successfully withdrew $" + String.format("%.2f", amount));
                System.out.println("Current balance: $" + String.format("%.2f", account.getBalance()));
            } else {
                if (amount > account.getBalance()) {
                    System.out.println("❌ Error: Insufficient funds!");
                    System.out.println("Available balance: $" + String.format("%.2f", account.getBalance()));
                } else {
                    System.out.println("❌ Error: Invalid withdrawal amount!");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input! Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    /**
     * Handle deposit operation
     */
    public void deposit() {
        System.out.print("\nEnter amount to deposit: $");
        
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            
            if (amount <= 0) {
                System.out.println("❌ Error: Deposit amount must be positive!");
                return;
            }
            
            if (account.deposit(amount)) {
                System.out.println("✅ Successfully deposited $" + String.format("%.2f", amount));
                System.out.println("Current balance: $" + String.format("%.2f", account.getBalance()));
            } else {
                System.out.println("❌ Error: Invalid deposit amount!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Invalid input! Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    /**
     * Display the current account balance
     */
    public void checkBalance() {
        System.out.println("\n========== ACCOUNT BALANCE ==========");
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("======================================");
    }

    /**
     * Get account number from user
     */
    public void getAccountNumber() {
        System.out.println("\n========== ACCOUNT NUMBER ==========");
        System.out.print("Enter your account number: ");
        accountNumber = scanner.nextLine().trim();
        System.out.println("Account number registered: " + accountNumber);
    }

    /**
     * Create a new PIN for the account
     * Validates that PIN is 4 digits
     */
    public void createPIN() {
        System.out.println("\n========== CREATE PIN ==========");
        System.out.println("This is your first time accessing the ATM.");
        System.out.println("Please create a 4-digit PIN for your account.");
        
        while (true) {
            System.out.print("Enter a 4-digit PIN: ");
            String newPIN = scanner.nextLine().trim();
            
            // Validate PIN is 4 digits
            if (newPIN.length() == 4 && newPIN.matches("\\d+")) {
                pin = newPIN;
                pinCreated = true;
                System.out.println("✅ PIN created successfully!");
                System.out.println("======================================");
                return;
            } else {
                System.out.println("❌ Error: PIN must be exactly 4 digits (numbers only). Please try again.");
            }
        }
    }

    /**
     * Authenticate user by verifying account number and PIN
     * Allows maximum 3 attempts
     * @return true if authentication successful, false otherwise
     */
    public boolean login() {
        System.out.println("\n========== LOGIN ==========");
        final int MAX_ATTEMPTS = 3;
        int attempts = 0;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter account number: ");
            String enteredAccountNumber = scanner.nextLine().trim();
            
            System.out.print("Enter your 4-digit PIN: ");
            String enteredPIN = scanner.nextLine().trim();
            
            // Verify account number and PIN
            if (enteredAccountNumber.equals(accountNumber) && enteredPIN.equals(pin)) {
                System.out.println("✅ Login successful! Access granted.");
                System.out.println("======================================");
                return true;
            } else {
                attempts++;
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                
                if (remainingAttempts > 0) {
                    System.out.println("❌ Error: Incorrect account number or PIN!");
                    System.out.println("Remaining attempts: " + remainingAttempts);
                } else {
                    System.out.println("❌ Error: Maximum login attempts reached. Access denied.");
                    System.out.println("======================================");
                }
            }
        }
        
        return false;
    }

    /**
     * Run the ATM interface - main loop
     */
    public void run() {
        System.out.println("Welcome to the ATM!");
        
        // Step 1: Get account number
        getAccountNumber();
        
        // Step 2: Create PIN if this is the first time
        if (!pinCreated) {
            createPIN();
        }
        
        // Step 3: Login with account number and PIN
        if (!login()) {
            System.out.println("\nAccess denied. Exiting...");
            scanner.close();
            return;
        }
        
        // Step 4: Display initial balance and show ATM menu
        System.out.println("\nWelcome, Account: " + accountNumber);
        System.out.println("Initial Balance: $" + String.format("%.2f", account.getBalance()));
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                
                switch (choice) {
                    case 1:
                        withdraw();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        System.out.println("\nThank you for using the ATM. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("❌ Error: Invalid option! Please select 1-4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Invalid input! Please enter a number between 1-4.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        
        scanner.close();
    }

    /**
     * Main method to start the ATM program
     */
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000.00
        BankAccount account = new BankAccount(1000.00);
        
        // Create an ATM instance connected to the account
        ATM atm = new ATM(account);
        
        // Start the ATM interface
        atm.run();
    }
}

