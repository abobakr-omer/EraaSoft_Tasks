package sheet1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class E_AreaOfACircle {
    public static void main(String[] args) {
       final double PI=3.141592653;
        double r,area;

        Scanner scanner=new Scanner(System.in);

        r=scanner.nextDouble();

        area=PI*Math.pow(r,2);

        System.out.println(new DecimalFormat("#.#########").format(area));


    }
}
