package Sheet_2_OPP.question_q;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter how many numbers:");
        Integer n = scanner.nextInt();

        Long[] arr = new Long[n];

        System.out.println("Enter the numbers:");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }

        Digits digits=new Digits(n,arr);
        digits.printDigits();

    }

}
