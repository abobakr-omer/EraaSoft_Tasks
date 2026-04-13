import java.util.ArrayList;
import java.util.Scanner;

public class M_LuckyNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = 0;


        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = a; i <= b; i++) {
            int flag = 1;
            int temp = i;
            while (temp > 0) {
                int d = temp % 10;
                if (d != 4 && d != 7) {
                    flag = 0;
                }
                temp /= 10;
            }
            if (flag == 1) {
                arrayList.add(i);
                c = 1;
            }
        }

        if (c == 1) {
            for (int i : arrayList) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println(-1);
        }

    }


}
