package banking;

public class WithdrawTransaction implements Transaction{
    private double amount;
    WithdrawTransaction(double amount){
        this.amount = amount;
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException{
        account.withdraw(this.amount);
    }
}
