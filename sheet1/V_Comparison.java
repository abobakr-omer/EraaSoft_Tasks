package sheet1;

import java.util.Scanner;

public class V_Comparison {

    public static void main(String[] args) {


        int n1, n2;
        char c;
        Scanner scanner = new Scanner(System.in);

        n1 = scanner.nextInt();
        c = scanner.next().charAt(0);
        n2 = scanner.nextInt();


        switch (c) {
            case '>':
                if (n1 > n2) {
                    System.out.println("Right");
                } else {
                    System.out.println("Wrong");
                }
                break;
            case '<':
                if (n1 < n2) {
                    System.out.println("Right");
                } else {
                    System.out.println("Wrong");
                }
                break;
            case '=':
                if (n1 == n2) {
                    System.out.println("Right");
                } else {
                    System.out.println("Wrong");
                }
                break;
        }
    }

}
