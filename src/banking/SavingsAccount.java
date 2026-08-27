package banking;

public class SavingsAccount extends BankAccount{
    private double interestRate;
    SavingsAccount(int accountNumber, double balance, double interestRate){
        super(accountNumber,balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if(super.getBalance()-amount <500){
            System.out.println("insufficient amount");
            return;
        }
        super.withdraw(amount);
    }
}
