import java.util.Scanner;

public class I_Palindrome {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rem, c = 0;
        int num = scanner.nextInt();
        int temp1 = num;
        int temp2 = num;
        int revNum = 0;

        while (  temp1 > 0) {
            temp1 /= 10;
            c++;
        }


        // Store reversed digits in array
        char[] str = new char[c];
        for (int i = 0; i < str.length; i++) {
            rem = temp2 % 10;
            str[i] = (char) (rem + '0');
            temp2 /= 10;
        }

        // Build reversed number
        for (char value : str) {
            revNum = revNum * 10 + (value - '0');
        }

        // Print reversed number
        System.out.println(revNum);

        if (num == revNum) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


    }

}
