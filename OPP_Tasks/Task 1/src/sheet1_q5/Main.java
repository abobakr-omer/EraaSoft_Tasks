package sheet1_q5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        double r;

        Scanner scanner=new Scanner(System.in);

        r=scanner.nextDouble();



        AreaOfACircle areaOfACircle=new AreaOfACircle(r);
        areaOfACircle.printCircleArea();




    }

}
