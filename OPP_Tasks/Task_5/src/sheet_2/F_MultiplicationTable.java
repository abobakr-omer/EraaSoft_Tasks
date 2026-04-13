import java.util.Scanner;

public class F_MultiplicationTable {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        int n=scanner.nextInt();

        for (int i=1;i<=12;i++){
            System.out.println(n+" "+"* "+i+" "+"= "+(n*i));
        }


    }

}
