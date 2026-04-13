package Sheet_2_OPP.question_K;

public class Divisors {

    private Integer n;

    public Divisors(Integer n){
        if (n!=null && n>=1 && n<=10000){
            this.n=n;
        }
        else {
            throw new IllegalArgumentException("The number must be [1 to 10000] inclusive");
        }
    }

    public void printPositiveDivisors(){
        for (int i=1;i<=n;i++){
            if (n%i==0){
                System.out.println(i);
            }
        }
    }


}
