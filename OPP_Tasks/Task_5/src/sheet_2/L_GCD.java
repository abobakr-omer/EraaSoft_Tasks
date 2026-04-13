import java.util.ArrayList;
import java.util.Scanner;

public class L_GCD {

    public static void main(String[] args) {

        //L. GCD

        Scanner scanner = new Scanner(System.in);

        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int gcd = 1;


        for (int i = 1; i <= Math.min(num1, num2); i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                gcd = i;
            }
        }

        System.out.println(gcd);


        //another solution using Euclidean algorithm

//        while ( b != 0) {
//            gcd = a % b;
//            a = b;
//                b = gcd;
//        }
//
//        System.out.println(a);


    }

}
