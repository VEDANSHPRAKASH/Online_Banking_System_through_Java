import java.util.*;

class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Deposit of Rs." + amount +" is successful. New Balance: Rs." + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdrawal of Rs." +amount +" is successful. New Balance: Rs." + balance);
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }

    public void transfer(Account toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer of amount of Rs." + amount +" is successful.");
        } else {
            System.out.println("Invalid or insufficient balance for transfer.");
        }
    }

    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: Rs." + balance);
    }
}

public class SimpleBankingSystem {
    static HashMap<String, Account> accounts = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("----Normal Banking System----");
            System.out.println("1. Create Account");
            System.out.println("2. Display Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> displayAccount();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> transfer();
                case 6 -> System.out.println("Exited......");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 6);
    }

    static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        if (accounts.containsKey(accNum)) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String accHolder = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initBalance = sc.nextDouble();
        sc.nextLine();

        Account newAccount = new Account(accNum, accHolder, initBalance);
        accounts.put(accNum, newAccount);
        System.out.println("Account created successfully!");
    }

    static void displayAccount() {
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();

        Account acc = accounts.get(accNum);
        if (acc != null) {
            acc.displayInfo();
        } else {
            System.out.println("Account not found.");
        }
    }

    static void deposit() {
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    static void withdraw() {
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();
        Account acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter Amount to Withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    static void transfer() {
        System.out.print("Enter Sender Account Number: ");
        String fromAccNum = sc.nextLine();
        Account fromAcc = accounts.get(fromAccNum);

        System.out.print("Enter Receiver Account Number: ");
        String toAccNum = sc.nextLine();
        Account toAcc = accounts.get(toAccNum);

        if (fromAcc != null && toAcc != null) {
            System.out.print("Enter Amount to Transfer: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            fromAcc.transfer(toAcc, amount);
        } else {
            System.out.println("Invalid account number(s).");
        }
    }
}
