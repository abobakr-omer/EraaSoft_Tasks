package sheet1;

import java.util.Scanner;

public class U_FloatOrInt {

    public static void main(String[] args) {
        float d;
        Scanner scanner=new Scanner(System.in);
        d=scanner.nextFloat();

        int n=(int)d;
        float r=d-n;
        if (r>0){
            System.out.println("float "+n+" "+r);
        }
        else {
            System.out.println("int "+n);
        }
    }

}
