package Sheet_2_OPP.question_G;

import java.util.Scanner;

public class Factorial {

    private Integer n;

    public Factorial(Integer n) {
        if (n != null && n >= 1 && n <= 15) {
            this.n = n;
        } else {
            throw new IllegalArgumentException("N must be between [1 to 15] inclusive");
        }
    }

    public void calculateFactorial(Scanner scanner){
        for (int i=0;i<n;i++){
            Integer number=scanner.nextInt();
            Integer sum=1;
            for (int j=1;j<=number;j++){
                sum*=j;
            }
            System.out.println(sum);
        }
    }


}
