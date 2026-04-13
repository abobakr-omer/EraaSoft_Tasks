package Sheet_2_OPP.question_Z;

public class ThreeNumbers {

    private Integer k;
    private Integer s;

    public ThreeNumbers(Integer k, Integer s) {
        if (k != null && s != null && k >= 0 && k <= 3000 && s >= 0 && s <= 9000) {
            this.k = k;
            this.s = s;
        } else {
            throw new IllegalArgumentException("The numbers must be (0 ≤ K ≤3000,0 ≤ S ≤ 3K)");
        }
    }

    public Integer casesOfThreeNumbers() {
        Integer cases = 0;
        for (int x = 0; x <= k; x++) {
            for (int y = 0; y <= k; y++) {
                int z = s - x - y;
                if (z >= 0 && z <= k) {
                    cases++;
                }
            }
        }
        return cases;
    }

}
