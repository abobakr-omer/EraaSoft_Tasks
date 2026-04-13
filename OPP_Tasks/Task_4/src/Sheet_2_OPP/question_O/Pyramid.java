package Sheet_2_OPP.question_O;

public class Pyramid {

    private Integer n;

    public Pyramid(Integer n) {
        if (n != null && n >= 1 && n <= 99) {
            this.n = n;
        } else {
            throw new IllegalArgumentException("N must be between [1 to 99] inclusive");
        }
    }

    public void printPyramid() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }


}
