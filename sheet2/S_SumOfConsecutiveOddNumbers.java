import java.util.Scanner;

public class S_SumOfConsecutiveOddNumbers {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int cases=scanner.nextInt();


        for (int i=0;i<cases;i++){
            int x=scanner.nextInt();
            int y=scanner.nextInt();

            int min = Math.min(x, y);
            int max = Math.max(x, y);

            int sum = 0;

            for (int j=min+1;j<max;j++){
                if (j%2!=0){
                    sum+=j;
                }
            }

            System.out.println(sum);


        }


    }

}
