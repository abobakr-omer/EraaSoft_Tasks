package Sheet_2_OPP.question_G;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of test cases:");
        Integer n = scanner.nextInt();

        Factorial factorial = new Factorial(n);
        System.out.println("Pleases enter the numbers to get their Factorial:");
        factorial.calculateFactorial(scanner);


    }


}
