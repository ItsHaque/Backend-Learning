package banking;

public class BankAccount extends Account{

    BankAccount(int accountNumber, double balance){
        super(accountNumber,balance);
    }
    public void withdraw(double amount) throws InsufficientBalanceException{
        if(amount > this.getBalance()) throw new InsufficientBalanceException("Insufficient Balance");
        super.setBalance(super.getBalance() - amount);
        System.out.println("Withdraw successful. New balance: " + super.getBalance());
    }

    @Override
    public String toString() {
        return "account number: " + super.getAccountNumber() + "\nbalance: " + super.getBalance();
    }
}

