/*Design a class named Account that contains:
• A private int data field named id for the account (default 0).
• A private double data field named balance for the account (default 0).
• A private static double data field named annualInterestRate that stores the current
interest rate (default 0). Assume all accounts have the same interest rate.
• A private Date data field named dateCreated that stores the date when the account
was created.
• A no-arg constructor that creates a default account.
• A constructor that creates an account with the specified id and initial balance.
• The accessor and mutator methods for id, balance, and annualInterestRate.
• The accessor method for dateCreated.
• A method named getMonthlyInterest() that returns the monthly interest rate.
• A method named withdraw that withdraws a specified amount from the account.
• A method named deposit that deposits a specified amount to the account.
Write a test program that creates an Account object with an account ID of 3040, a
balance of $30,000, and an annual interest rate of 3.5%. Use the withdraw method to
withdraw $3,500, use the deposit method to deposit $2,000, and print the balance ID, the
balance, the monthly interest, and the date when this account was created.*/


package accountclass;

public class AccountClass {
    public static void main(String[] args) {
        
        //Set account details, make a withdrawal and deposit, & print results
        Account account = new Account(3040, 30000);
        account.setAnnualInterestRate(3.5);
        account.withdraw(3500.0);
        account.deposit(2000.0);
        System.out.println("The Balance ID is " + account.getId());
        System.out.println("Balance is " + account.getBalance());
        System.out.println("Monthly interest is " +
        account.getMonthlyInterest());
        System.out.println("This account was created at " +
        account.getDateCreated());

    }
}
//Create account class with specified constructors, accessors, and mutators.
class Account {
    //set default for account details to 0
    private int id = 0;
    private double balance = 0.0;
    private static double annualInterestRate = 0.0;
    private java.util.Date dateCreated;

    public Account() { //no-arg constructor
    }
    
    public Account(int id, double balance) {
        this();//must come first
        this.id = id;
        this.balance = balance;
        dateCreated = new java.util.Date();
    }

    //accessors for account details
    public int getId() {
        return this.id;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public String getDateCreated() {
        return this.dateCreated.toString();
    }

    //mutators for account details
    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    //accessors for monthly interest rate and monthly interest
    public double getMonthlyInterestRate() {
        return (annualInterestRate / 100) / 12;
    }

    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    //withdraw and deposit methods
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
