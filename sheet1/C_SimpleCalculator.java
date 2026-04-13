package sheet1;

import java.util.Scanner;

public class C_SimpleCalculator {
    public static void main(String[] args) {
        long num1,num2;
        Scanner input=new Scanner(System.in);



                num1=input.nextLong();
                num2=input.nextLong();

                System.out.println(num1+" + "+num2+" = "+(num1+num2));
                System.out.println(num1+" * "+num2+" = "+(num1*num2));
                System.out.println(num1+" - "+num2+" = "+(num1-num2));

            }



    }

