package sheet1;

import java.util.Scanner;

public class G_SummationFrom1ToN {
    public static void main(String[] args) {


        long num,sum;
        Scanner scanner=new Scanner(System.in);
        num=scanner.nextLong();

        sum=num*(num+1)/2;
        System.out.println(sum);



        /*
        Another solution but it gives Time limit exceeded
        for (int i=0;flag==1;i++){

            if (num<=0){
                System.out.println(sum);
                flag=0;
            }
            else{
                sum+=num;
                num-=1;
            }
        }*/

    }
}
