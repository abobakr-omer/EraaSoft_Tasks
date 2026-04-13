package sheet1;

import java.util.Scanner;

public class Y_TheLast_2_digits {

    public static void main(String[] args) {
        int n1,n2,n3,n4;
        int r=1;
        Scanner scanner=new Scanner(System.in);

        n1=scanner.nextInt()%100;
        n2=scanner.nextInt()%100;
        n3=scanner.nextInt()%100;
        n4=scanner.nextInt()%100;

        r=(n1*n2*n3*n4)%100;


        if (r<=9){
            System.out.println("0"+r);
        }
        else {
            System.out.println(r);
        }
    }


}
