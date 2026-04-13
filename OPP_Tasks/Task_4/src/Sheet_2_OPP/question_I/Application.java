package Sheet_2_OPP.question_I;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter the number:");
        Integer n=scanner.nextInt();

        Palindrome palindrome=new Palindrome(n);

        palindrome.printPalindrome();

    }


}
