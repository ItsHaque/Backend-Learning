package banking;

public class BankAccount {
    private int accountNumber;
    private double balance;

    BankAccount(int accountNumber, double balance){
        this.accountNumber =accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }
    public double getBalance(){
        return this.balance;
    }
    public void deposit(double amount){
        this.balance += amount;
        System.out.println("New balance: " + this.getBalance());
        return;
    }
    public void withdraw(double amount){
        if(amount > this.getBalance()){
            System.out.println("Insufficient Balance");
            return;
        }
        this.balance -= amount;
        System.out.println("New balance: " + this.getBalance());
        return;
    }

    @Override
    public String toString() {
        return "account number: " + this.getAccountNumber() + "\nbalance: " + this.getBalance();
    }
}

