package banking;

public class DepositTransaction implements Transaction{
    private double amount;
    DepositTransaction(double amount){
        this.amount = amount;
    }

    @Override
    public void execute(Account account) {
        account.deposit(this.amount);
    }
}
