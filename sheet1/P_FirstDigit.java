package sheet1;

import java.util.Scanner;

public class P_FirstDigit {

    public static void main(String[] args) {

        int num;
        Scanner scanner = new Scanner(System.in);
        //num=Integer.parseInt(String.valueOf(scanner.next().charAt(0)));
         num = scanner.next().charAt(0) - '0';


        if (num%2==0){
            System.out.println("EVEN");
        }
        else {
            System.out.println("ODD");
        }


       /* **Another Solution
       int num,flag=1,digit=0,subNum;
        Scanner scanner = new Scanner(System.in);
        num=scanner.nextInt();
        subNum=num;
        for (int i=0;flag==1;i++){
            subNum/=10;
            if (subNum==0){
                flag=0;
                digit=i;
            }
        }

        int r=num/(int)(Math.pow(10,digit));
        if (r%2==0){
            System.out.println("EVEN");
        }
        else {
            System.out.println("ODD");
        }*/


    }


}
