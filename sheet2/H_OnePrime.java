import java.util.Scanner;

public class H_OnePrime {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = scanner.nextInt();


        if (n == 2) {
            System.out.println("YES");
            return;
        }

        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");


    }


}
