package Sheet_2_OPP.question_E;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many numbers you want to compare:");
        Integer n = scanner.nextInt();

        Max max = new Max(n);

        System.out.println("Pleases enter the numbers:");
        Integer maxNumber = max.maxNumber(scanner);

        System.out.println(maxNumber);

    }


}
