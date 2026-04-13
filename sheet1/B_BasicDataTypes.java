package sheet1;

import java.util.Scanner;

public class B_BasicDataTypes {

    public static void main(String[] args) {
        int a;
        long l;
        char c;
        float f;
        double d;


        Scanner input = new Scanner(System.in);
        a = Integer.parseInt(input.next());
        l = Long.parseLong(input.next());
        c = input.next().charAt(0);
        f = Float.parseFloat(input.next());
        d = Double.parseDouble(input.next());


        System.out.println(a);
        System.out.println(l);
        System.out.println(c);
        System.out.println(f);
        System.out.println(d);

    }


}
