import java.util.Scanner;

public class R_SequenceOfNumbersAndSum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n <= 0 || m <= 0) {
                return;
            }

            int min = Math.min(n, m);
            int max = Math.max(n, m);

            int sum = 0;


            for (int i = min; i <= max; i++) {
                System.out.print(i + " ");
                sum += i;
            }
            System.out.println("sum =" + sum);

        }
    }

}
