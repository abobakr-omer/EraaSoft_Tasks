package Sheet_2_OPP.question_W;

public class Shape3 {

    private Integer n;

    public Shape3(Integer n) {
        if (n != null && n >= 1 && n <= 99) {
            this.n = n;
        } else {
            throw new IllegalArgumentException("N must be between [1 to 99] inclusive");
        }
    }

    public void printShape_3() {
        Integer spaces=n-1;
        for (int i = 1; i <= n; i++) {
            for (int j=1;j<=spaces;j++){
                System.out.print(" ");
            }
            for (int z=1;z<i*2;z++){
                System.out.print("*");
            }
            System.out.println();
            spaces--;
        }

        for (int i = n; i > 0; i--) {
            for (int j=spaces;j>=0;j--){
                System.out.print(" ");
            }
            for (int z=1;z<i*2;z++){
                System.out.print("*");
            }
            System.out.println();
            spaces++;
        }
    }


}
