package banking;

public class Main {
    public static void main(String[] args){
        SavingsAccount svac = new SavingsAccount(1234,1000,0.1);
        svac.withdraw(800);
        svac.withdraw(500);
    }
}
