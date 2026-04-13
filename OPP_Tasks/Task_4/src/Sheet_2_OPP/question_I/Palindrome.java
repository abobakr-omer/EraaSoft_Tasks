package Sheet_2_OPP.question_I;

public class Palindrome {

    private Integer n;

    public Palindrome(Integer n){
        if (n != null && n >= 1 && n <= 10000000) {
            this.n = n;
        } else {
            throw new IllegalArgumentException("N must be between [1 to 10000000] inclusive");
        }
    }

    public void printPalindrome(){
        int rem, c = 0;
        int temp1 = n;
        int temp2 = n;
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

        if (n == revNum) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }




}
