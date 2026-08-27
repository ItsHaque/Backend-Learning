import java.util.Scanner;

public class JavaArrays {
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        int sum =0;
        for(int i=0;i<5;++i){
            sum+=arr[i];
        }
        System.out.println("avg: " + (double)sum/5);
        Scanner sc = new Scanner(System.in);
        int[] arr2 = new int[5];
        arr2[0] = sc.nextInt();
        int min =arr2[0], max = arr2[0];
        for(int i=1; i<5; ++i){
            arr2[i] = sc.nextInt();
            if (arr2[i]<min) min = arr2[i];
            if (arr2[i]> max) max = arr2[i];
        }
        System.out.println("min: " + min + ", max: " + max);
    }
}
