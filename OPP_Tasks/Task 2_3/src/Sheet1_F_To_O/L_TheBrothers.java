package Sheet1_F_To_O;

import java.util.Scanner;

public class L_TheBrothers {
    public static void main(String[] args) {
        String f1, s1, f2, s2;
        Scanner scanner = new Scanner(System.in);
        f1 = scanner.next();
        s1 = scanner.next();
        f2 = scanner.next();
        s2 = scanner.next();

        if (s1.equals(s2)) {
            System.out.println("ARE Brothers");
        } else {
            System.out.println("NOT");
        }
    }
}
