package Sheet_2_OPP.question_Z;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter the two numbers:");
        Integer k=scanner.nextInt();
        Integer s=scanner.nextInt();

        ThreeNumbers threeNumbers=new ThreeNumbers(k,s);

        System.out.println(threeNumbers.casesOfThreeNumbers());


    }

}
