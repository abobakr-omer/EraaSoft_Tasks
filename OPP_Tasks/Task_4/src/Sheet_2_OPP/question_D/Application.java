package Sheet_2_OPP.question_D;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            Integer number = scanner.nextInt();
            if (FixedPassword.isCorrect(number)) {
                System.out.println("Correct");
                return;
            } else {
                System.out.println("Wrong");
            }

        }

    }


}
