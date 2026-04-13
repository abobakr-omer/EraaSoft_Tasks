package Sheet1_F_To_O;

import java.util.Scanner;

public class M_CapitalOrSmallOrDigit {
    public static void main(String[] args) {
        char c;
        Scanner scanner = new Scanner(System.in);
        c = scanner.next().charAt(0);
        if (((((c - 'A') + ('Z' - c)) == 25) || (((c - 'a') + ('z' - c)) == 25)) && (c >= 65)) {
            if (c >= 97) {
                System.out.println("ALPHA");
                System.out.println("IS SMALL");
            } else {
                System.out.println("ALPHA");
                System.out.println("IS CAPITAL");
            }
        } else {
            System.out.println("IS DIGIT");
        }
    }
}
