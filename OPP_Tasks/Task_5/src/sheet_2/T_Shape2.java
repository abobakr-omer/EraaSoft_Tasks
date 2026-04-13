import java.util.Scanner;

public class T_Shape2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int spaces = n - 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= spaces; j++) {
                System.out.print(" ");
            }
            for (int z = 1; z < i * 2; z++) {
                System.out.print("*");
            }
            System.out.println();
            spaces--;
        }


    }


}
