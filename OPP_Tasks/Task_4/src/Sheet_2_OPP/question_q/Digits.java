package Sheet_2_OPP.question_q;

public class Digits {

    private final Integer n;
    private final Long[] numbers;

    public Digits(Integer n, Long[] numbers) {

        if (n == null || n < 1) {
            throw new IllegalArgumentException("N must be greater than or equal to 1");
        }

        this.n = n;
        this.numbers = numbers;
    }

    public void printDigits() {

        long rem;
        char s;

        for (int i = 0; i < n; i++) {

            if (numbers[i] == 0) {
                System.out.println(0);
                continue;
            }

            long value = numbers[i];

            while (value > 0) {

                rem = value % 10;
                s = (char) (rem + '0');
                value /= 10;

                if (value > 0) {
                    System.out.print(s + " ");
                } else {
                    System.out.print(s);
                }
            }

            System.out.println();
        }
    }

}
