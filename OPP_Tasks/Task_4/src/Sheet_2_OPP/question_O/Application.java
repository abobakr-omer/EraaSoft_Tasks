package Sheet_2_OPP.question_O;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lines/rows:");
        Integer n = scanner.nextInt();

        Pyramid pyramid=new Pyramid(n);
        pyramid.printPyramid();

    }




}
