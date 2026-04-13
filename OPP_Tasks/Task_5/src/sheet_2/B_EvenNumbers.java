import java.util.Scanner;

public class B_EvenNumbers {

    public static void main(String[] args) {
        int n,evenFlag=0;
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();

        for (int i=1;i<=n;i++){
            if (i%2==0){
                evenFlag=1;
                System.out.println(i);
            }
        }
        if (evenFlag==0){
            System.out.println(-1);
        }
    }


}
