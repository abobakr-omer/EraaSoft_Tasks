package sheet1_q3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        long num1,num2;

        Scanner scanner=new Scanner(System.in);

        num1=scanner.nextLong();
        num2=scanner.nextLong();


        SimpleCalculator calculator=new SimpleCalculator(num1,num2);
        calculator.summationNumbers();
        calculator.multiplicationNumbers();
        calculator.subtractionNumbers();



    }

}
