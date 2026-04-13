package Sheet_2_OPP.question_R;

public class SequenceOfNumbersAndSum {

    private Integer n;
    private Integer m;

    public SequenceOfNumbersAndSum(Integer n, Integer m) {
        if (n != null && m != null && n >= -100 && n <= 100 && m >= -100 && m <= 100) {
            this.n = n;
            this.m = m;
        } else {
            throw new IllegalArgumentException("The numbers must be [-100 to 100] inclusive");
        }
    }

    private boolean checkNegativeNumbers() {
        if (n <= 0 || m <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void printSequenceOfNumbersAndSum() {

        if (checkNegativeNumbers()) {
            Integer min = Math.min(n, m);
            Integer max = Math.max(n, m);
            Integer sum = 0;


            for (int i = min; i <= max; i++) {
                sum += i;
                System.out.print(i + " ");
            }

            System.out.println("sum =" + sum);
        } else {
            System.out.println("The numbers not be less than or equal to zero");
        }


    }


}
