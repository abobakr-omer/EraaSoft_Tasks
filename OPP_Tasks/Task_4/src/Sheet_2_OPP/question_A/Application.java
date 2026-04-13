package Sheet_2_OPP.question_A;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number:");
        Integer n=scanner.nextInt();

        NumberPrinter numberPrinter =new NumberPrinter(n);

        numberPrinter.printLines();

    }


}
