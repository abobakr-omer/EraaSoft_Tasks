package Sheet1_F_To_O;

import java.util.Scanner;

public class N_Char {
    public static void main(String[] args) {
        char c;
        Scanner scanner = new Scanner(System.in);
        c = scanner.next().charAt(0);
        if (c < 'a') {
            c = (char) (c - 'A' + 'a');
            System.out.println(c);
        } else {
            c = (char) (c - 'a' + 'A');
            System.out.println(c);
        }
    }
}
