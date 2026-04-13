import java.util.Scanner;

public class Q_Digits {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        long [] arr=new long[n];
        long rem=0;

        for (int i=0;i<arr.length;i++){
            arr[i]=scanner.nextLong();
        }
        char s;

        for (int i=0;i<n;i++){

            if (arr[i] == 0) {          // handle N = 0
                System.out.println(0);
                continue;
            }

            while (arr[i]>0){
                rem=arr[i]%10;
                s=(char) (rem+'0');
                arr[i]/=10;
                if (arr[i]>0){
                    System.out.print(s+" ");
                }
                else {
                    System.out.print(s);
                }

            }
            System.out.println();
        }


    }


}
