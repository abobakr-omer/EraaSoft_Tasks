package sheet1;

import java.util.Scanner;

public class X_TwoIntervals {

    public static void main(String[] args) {
        long n1, n2, n3, n4;

        Scanner scanner = new Scanner(System.in);

        n1 = scanner.nextLong();
        n2 = scanner.nextLong();
        n3 = scanner.nextLong();
        n4 = scanner.nextLong();

        long start = Math.max(n1, n3);
        long end = Math.min(n2, n4);

        if (start > end) System.out.println(-1);
        else System.out.println(start + " " + end);

    }


}
