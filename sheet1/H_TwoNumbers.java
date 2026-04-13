package sheet1;

import java.util.Scanner;

public class H_TwoNumbers {
    public static void main(String[] args) {
        long num1,num2;
        double d;
        long f,c,r;
        Scanner scanner=new Scanner(System.in);
        num1=scanner.nextLong();
        num2=scanner.nextLong();

        d=(double) num1/num2;

        f=(long)Math.floor(d);
        c=(long)Math.ceil(d);
        r=(long)Math.round(d);

        System.out.println("floor "+num1+" / "+num2+" = "+f);
        System.out.println("ceil "+num1+" / "+num2+" = "+c);
        System.out.println("round "+num1+" / "+num2+" = "+r);

    }
}
