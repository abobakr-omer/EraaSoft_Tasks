package sheet1;

import java.util.Scanner;

public class R_AgeInDays {
        public static void main(String[] args) {

            int n,y,m,d;

            Scanner scanner = new Scanner(System.in);

            n = scanner.nextInt();

            y = n / 365;

            m = (n-(y*365))/30;

            d = (n-(y*365))-(m*30);

            /*BEST SOLUATION
            int years = n / 365;
            int rem = n % 365;
            int months = rem / 30;
            int days = rem % 30;
            * */

            System.out.println(y+" years");
            System.out.println(m+" months");
            System.out.println(d+" days");


        }
}
