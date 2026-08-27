import java.util.Scanner;
public class UserInput {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter two numbers and any of the operators below: \n +, -, *, /");
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.nextLine();
        char op = sc.nextLine().charAt(0);
        if(op == '+'){
            System.out.println(a+b);
        }else if(op == '-'){
            System.out.println(a-b);
        }else if(op == '*'){
            System.out.println(a*b);
        }else if(op == '/'){
            if(b ==0) System.out.println("Cannot divide by Zero");
            else System.out.println(a/b);
        }else System.out.println("Invalid operator");
    }
}
