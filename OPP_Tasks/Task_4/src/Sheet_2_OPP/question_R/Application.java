package Sheet_2_OPP.question_R;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter the two number to calculate the Sequence of Numbers and Sum:");
        Integer n=scanner.nextInt();
        Integer m=scanner.nextInt();

        SequenceOfNumbersAndSum sequenceOfNumbersAndSum=new SequenceOfNumbersAndSum(n,m);

        sequenceOfNumbersAndSum.printSequenceOfNumbersAndSum();

    }


}
