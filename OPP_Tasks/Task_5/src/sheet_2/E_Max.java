import java.util.Scanner;

public class E_Max {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            if (number > max) {
                max = number;
            }

        }

        System.out.println(max);

    }


}
