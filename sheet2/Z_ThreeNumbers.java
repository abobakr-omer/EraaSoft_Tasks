import java.util.Scanner;

public class Z_ThreeNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int s = scanner.nextInt();
        int cases = 0;

        for (int x = 0; x <= k; x++) {
            for (int y = 0; y <= k; y++) {
                int z=s-x-y;
                if (z>=0&&z<=k){
                    cases++;
                }
            }
        }

        System.out.println(cases);


    }


}
