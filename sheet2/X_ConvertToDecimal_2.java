import java.util.Scanner;

public class X_ConvertToDecimal_2 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();

        for (int j=0;j<n;j++){
            int t=scanner.nextInt();
            int c=0;
            int sum=0;
            while (t>0){
                if (t%2==1){
                    c++;
                }
                t/=2;
            }
            for (int i=0;i<c;i++){
                sum+=Math.pow(2,i);
            }

            System.out.println(sum);
        }







    }


}
