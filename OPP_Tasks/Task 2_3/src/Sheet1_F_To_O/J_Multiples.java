package Sheet1_F_To_O;

import java.util.Scanner;

public class J_Multiples {
    public static void main(String[] args) {
        int num1, num2;
        Scanner scanner = new Scanner(System.in);
        num1 = scanner.nextInt();
        num2 = scanner.nextInt();

        if ((num1 % num2) == 0 || (num2 % num1) == 0) {
            System.out.println("Multiples");
        } else {
            System.out.println("No Multiples");
        }
    }


}
