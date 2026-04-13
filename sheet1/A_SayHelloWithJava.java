package sheet1;

import java.util.Scanner;


public class A_SayHelloWithJava {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        System.out.println("Hello, " + name);
    }
}
