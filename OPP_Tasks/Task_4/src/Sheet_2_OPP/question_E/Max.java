package Sheet_2_OPP.question_E;

import java.util.Scanner;

public class Max {

    private Integer n;

    public Max(Integer n) {
        if (n != null && n >= 1 && n <= 1000) {
            this.n = n;
        } else {
            throw new IllegalArgumentException("N must be between [1 to 1000] inclusive");
        }
    }

    public Integer maxNumber(Scanner scanner) {
        Integer max = 0;
        for (int i = 0; i < n; i++) {
            Integer number = scanner.nextInt();
            if (number > max) {
                max = number;
            }
        }
        return max;
    }


}
