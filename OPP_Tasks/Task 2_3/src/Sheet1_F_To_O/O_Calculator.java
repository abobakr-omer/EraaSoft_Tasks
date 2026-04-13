package Sheet1_F_To_O;

import java.util.Scanner;

public class O_Calculator {
    public static void main(String[] args) {
        int n1 = 0, n2 = 0;
        String s;
        char sign = '+';
        int c1 = -1, c2 = -1;
        int signIndex = 0;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();

        for (int i = 0; i <= (s.length() - 1); i++) {
            if ((s.charAt(i) != '*') && (s.charAt(i) != '/') && (s.charAt(i) != '+') && (s.charAt(i) != '-')) {
                c1++;
            } else {
                sign = s.charAt(i);
                signIndex = i;
                break;
            }
        }

        for (int i = 1 + signIndex; i <= (s.length() - 1); i++) {
            c2++;

        }

        for (int i = 0; c1 != -1; i++, c1--) {

            n1 += (s.charAt(i) - '0') * ((int) Math.pow(10, c1));

        }

        for (int i = 1 + signIndex; c2 != -1; i++, c2--) {

            n2 += (s.charAt(i) - '1' + 1) * ((int) Math.pow(10, c2));

        }

        switch (sign) {
            case '+':
                System.out.println(n1 + n2);
                break;
            case '-':
                System.out.println(n1 - n2);
                break;
            case '*':
                System.out.println(n1 * n2);
                break;
            case '/':
                System.out.println(n1 / n2);
                break;

        }


    }
}
