package Sheet_2_OPP.question_N;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Please Enter the  symbol:");
        Character sign=scanner.next().charAt(0);

        System.out.println("Please enter the number of lines:");
        Integer number=scanner.nextInt();

        NumbersHistogram numbersHistogram=new NumbersHistogram(sign,number);

        System.out.println("Please enter the numbers:");
        numbersHistogram.printNumbersHistogram(scanner);

    }




}
