import java.util.Scanner;

public class G_Factorial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long r = 1;
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= arr[i]; j++) {
                r *= j;
            }
            System.out.println(r);
            r = 1;
        }

    }


}
