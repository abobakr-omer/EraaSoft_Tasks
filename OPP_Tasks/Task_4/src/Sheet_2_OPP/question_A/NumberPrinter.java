package Sheet_2_OPP.question_A;

public class NumberPrinter {

    private Integer n;

    public NumberPrinter(Integer n){
        if (n!=null && n>=1 && n<=1000){
            this.n=n;
        }
        else {
            throw new IllegalArgumentException("The number must be [1 to 1000] inclusive");
        }
    }

    public void printLines(){
        for (int i=1;i<=n;i++){
            System.out.println(i);
        }
    }


}
