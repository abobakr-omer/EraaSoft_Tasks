package Sheet1_F_To_O;

import java.util.Scanner;

public class K_MaxAndMin {
    public static void main(String[] args) {
        int num1, num2, num3, min, max;
        Scanner scanner = new Scanner(System.in);
        num1 = scanner.nextInt();
        num2 = scanner.nextInt();
        num3 = scanner.nextInt();

        min = num1;
        max = num1;

        if (num2 >= num1 && num2 >= num3) {
            max = num2;
            if (num3 > num1) {
                min = num1;
            } else {
                min = num3;
            }
        } else if (num3 >= num1 && num3 >= num2) {
            max = num3;
            if (num2 > num1) {
                min = num1;
            } else {
                min = num2;
            }
        } else if (num1 >= num2 && num1 >= num3) {
            max = num1;
            if (num2 > num3) {
                min = num3;
            } else {
                min = num2;
            }
        }

        System.out.println(min + " " + max);


    }
}
