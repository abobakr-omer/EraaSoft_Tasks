package Sheet_2_OPP.question_K;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter the number to calculate its divisors:");
        Integer n=scanner.nextInt();

        Divisors divisors=new Divisors(n);

        divisors.printPositiveDivisors();

    }

}
