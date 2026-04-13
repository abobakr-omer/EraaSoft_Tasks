package Sheet1_F_To_O;


import java.util.Scanner;

public class F_DigitsSummation {
    public static void main(String[] args) {

        long num1, num2;
        Scanner scanner = new Scanner(System.in);

        num1 = scanner.nextLong() % 10;
        num2 = scanner.nextLong() % 10;

        System.out.println(num1 + num2);


    }
}
