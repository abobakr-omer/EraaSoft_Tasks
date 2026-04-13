package sheet1;

import java.util.Scanner;

public class W_MathematicalExpression {

    public static void main(String[] args) {


        int n1, n2, n3, r;
        char sign, equal;
        Scanner scanner = new Scanner(System.in);

        n1 = scanner.nextInt();
        sign = scanner.next().charAt(0);
        n2 = scanner.nextInt();
        equal = scanner.next().charAt(0);
        n3 = scanner.nextInt();


        switch (sign) {
            case '+':
                r = n1 + n2;
                if (n1 + n2 == n3) {
                    System.out.println("Yes");
                } else {
                    System.out.println(r);
                }
                break;
            case '-':
                r = n1 - n2;
                if (n1 - n2 == n3) {
                    System.out.println("Yes");
                } else {
                    System.out.println(r);
                }
                break;
            case '*':
                r = n1 * n2;
                if (n1 * n2 == n3) {
                    System.out.println("Yes");
                } else {
                    System.out.println(r);
                }
                break;
        }
    }


}
