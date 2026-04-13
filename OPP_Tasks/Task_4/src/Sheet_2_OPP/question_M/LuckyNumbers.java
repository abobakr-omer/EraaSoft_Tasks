package Sheet_2_OPP.question_M;

public class LuckyNumbers {

    private Integer a;
    private Integer b;

    public LuckyNumbers(Integer a,Integer b){
        if (a!=null && b!=null && a>=1 && a<=100000 && b>=1 && b<=100000){
            this.a=a;
            this.b=b;
        }
        else {
            throw new IllegalArgumentException("The numbers must be [1 to 100000] inclusive");
        }
    }

    public void printLuckyNumbers(){
        int c=0;
        for (int i=a;i<=b;i++){
            int temp=i,flag=1;
            while (temp>0){
                int d=temp%10;
                if (d!=4 && d!=7){
                    flag=0;
                }
                temp/=10;
            }
            if (flag==1){
                System.out.println(i);
                c++;
            }
        }
        if (c==0){
            System.out.println(-1);
        }
    }


}
