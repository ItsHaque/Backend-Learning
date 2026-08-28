package banking;

public class Main {
    public static void main(String[] args){
//        SavingsAccount svac = new SavingsAccount(1234,1000,0.1);
//        svac.withdraw(800);
//        svac.withdraw(500);
        Transaction[] trn = {new DepositTransaction(1000),new DepositTransaction(1000), new WithdrawTransaction(500)};
        BankAccount account = new BankAccount(1234,500);
        for(int i =0;i< trn.length;++i){
            trn[i].execute(account);
        }
    }
}
