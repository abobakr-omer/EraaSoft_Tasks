import java.util.Scanner;

public class U_SomeSums {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int sum = 0, d = 0, sum2 = 0;

        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp > 0) {
                d = temp % 10;
                sum += d;
                temp /= 10;
            }
            if (sum >= a && sum <= b) {
                sum2 += i;
            }
            sum = 0;
        }

        System.out.println(sum2);


    }


}
