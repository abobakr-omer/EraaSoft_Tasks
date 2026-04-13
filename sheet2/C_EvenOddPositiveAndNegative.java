import java.util.Scanner;

public class C_EvenOddPositiveAndNegative {

    public static void main(String[] args) {
        int n, cEven = 0, cOdd = 0, cPositive = 0, cNegative = 0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }


        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                cEven++;
            }
            if (arr[i] % 2 != 0) {
                cOdd++;
            }
            if (arr[i] < 0) {
                cNegative++;
            }
            if (arr[i] > 0) {
                cPositive++;
            }
        }
        System.out.println("Even: " + cEven);
        System.out.println("Odd: " + cOdd);
        System.out.println("Positive: " + cPositive);
        System.out.println("Negative: " + cNegative);


    }

}
