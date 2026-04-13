package sheet1;

import java.util.Scanner;

public class D_Difference {
    public static void main(String[] args) {
        long num1,num2,num3,num4,r;

        Scanner scanner=new Scanner(System.in);

        num1=scanner.nextLong();
        num2=scanner.nextLong();
        num3=scanner.nextLong();
        num4=scanner.nextLong();

        r=(num1*num2)-(num3*num4);

        System.out.println("Difference = "+r);


    }





}
