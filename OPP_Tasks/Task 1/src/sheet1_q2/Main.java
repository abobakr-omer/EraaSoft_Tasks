package sheet1_q2;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int n;
        long l;
        char c;
        float f;
        double d;

        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        l = input.nextLong();
        c = input.next().charAt(0);
        f = input.nextFloat();
        d = input.nextDouble();

        BasicDataTypes data=new BasicDataTypes(n,l,c,f,d);
        data.printDataTypes();



        }
    }
