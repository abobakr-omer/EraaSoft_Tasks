package sheet1;

import java.util.Scanner;

public class Z_HardCompare {

    public static void main(String[] args) {


        double n1, n2, n3, n4, r1, r2;

        Scanner scanner = new Scanner(System.in);

        n1 = scanner.nextDouble();
        n2 = scanner.nextDouble();
        n3 = scanner.nextDouble();
        n4 = scanner.nextDouble();

        r1 = n2 * Math.log(n1);
        r2 = n4 * Math.log(n3);


        if (r1 > r2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}



