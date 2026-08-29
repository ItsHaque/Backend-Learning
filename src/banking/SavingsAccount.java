package banking;

public class SavingsAccount extends Account{
    private double interestRate;
    SavingsAccount(int accountNumber, double balance, double interestRate){
        super(accountNumber,balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException{
        if(super.getBalance()-amount <500) throw new InsufficientBalanceException("Insufficient Balance (Minimum fund requirement violation)");
        super.setBalance(super.getBalance() - amount);
        System.out.println("Withdraw successful. New Balance: " + super.getBalance());
    }
}
