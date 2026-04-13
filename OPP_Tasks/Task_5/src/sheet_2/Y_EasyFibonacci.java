import java.util.ArrayList;
import java.util.Scanner;

public class Y_EasyFibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int a = 0;
        int b = 1;

        if (n == 1) {
            System.out.print(a);
            return;
        }

        if (n == 2) {
            System.out.print(a + " " + b);
            return;
        }

        System.out.print(a + " " + b);

        for (int i = 3; i <= n; i++) {
            int c = a + b;
            System.out.print(" " + c);
            a = b;
            b = c;
        }


    }


}
