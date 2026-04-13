package sheet1;

import java.util.Scanner;


public class Q_CoordinatesOfAPoint {
    public static void main(String[] args) {

        float x = 0, y = 0;

        Scanner scanner = new Scanner(System.in);
        x = scanner.nextFloat();
        y = scanner.nextFloat();



        if (x > 0 && y > 0) {
            System.out.println("Q1");
        } else if (x < 0 && y > 0) {
            System.out.println("Q2");
        } else if (x < 0 && y < 0) {
            System.out.println("Q3");
        } else if (x > 0 && y < 0) {
            System.out.println("Q4");
        }
        else if (x ==0 && y== 0) {
            System.out.println("Origem");
        }
        else if ((x > 0||x<0) && y == 0) {
            System.out.println("Eixo X");
        }
        else if (x ==0 &&( y < 0||y>0)) {
            System.out.println("Eixo Y");
        }



    }
}
