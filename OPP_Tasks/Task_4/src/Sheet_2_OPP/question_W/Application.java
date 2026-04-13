package Sheet_2_OPP.question_W;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lines/rows:");
        Integer n = scanner.nextInt();

        Shape3 shape3=new Shape3(n);
        shape3.printShape_3();

    }

}
