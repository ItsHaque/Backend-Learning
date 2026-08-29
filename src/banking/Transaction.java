package banking;

public interface Transaction {
    void execute( Account account) throws InsufficientBalanceException;
}
