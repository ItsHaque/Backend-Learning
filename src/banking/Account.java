package banking;

abstract class Account {
    private int accountNumber;
    private double balance;
    public Account(int accountNumber, double balance){
        this.setAccountNumber(accountNumber);
        this.setBalance(balance);
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }
    public double getBalance(){
        return this.balance;
    }

    protected void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
    protected void setBalance(double amount){
        this.balance = amount;
    }

    public void deposit(double amount){
        this.setBalance(this.getBalance() + amount);
        System.out.println("Deposit successful. New Balance: " + this.balance);
    }
    abstract void withdraw(double amount) throws InsufficientBalanceException;
}
