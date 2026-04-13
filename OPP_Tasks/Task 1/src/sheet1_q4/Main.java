package sheet1_q4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int num1,num2,num3,num4;

        Scanner scanner=new Scanner(System.in);

        num1=scanner.nextInt();
        num2=scanner.nextInt();
        num3=scanner.nextInt();
        num4=scanner.nextInt();


        Difference calculator=new Difference(num1,num2,num3,num4);

        calculator.printDifferenceResult();




    }

}
