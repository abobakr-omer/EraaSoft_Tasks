package sheet1;

import java.util.Scanner;

public class I_WelcomeForYouWithConditions {
    public static void main(String[] args) {
        int num1,num2;
        Scanner scanner=new Scanner(System.in);
        num1=scanner.nextInt();
        num2=scanner.nextInt();

        if (num1>=num2){
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }

    }
}
