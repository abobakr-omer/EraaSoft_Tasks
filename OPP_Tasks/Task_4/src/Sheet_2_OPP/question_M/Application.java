package Sheet_2_OPP.question_M;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter the two number to calculate their Lucky numbers:");
        Integer a=scanner.nextInt();
        Integer b=scanner.nextInt();

        LuckyNumbers luckyNumbers=new LuckyNumbers(a,b);

        luckyNumbers.printLuckyNumbers();
    }

}
