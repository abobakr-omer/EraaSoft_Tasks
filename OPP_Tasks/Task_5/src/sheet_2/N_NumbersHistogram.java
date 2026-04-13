import java.util.Scanner;

public class N_NumbersHistogram {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        char sign=scanner.next().charAt(0);
        int size=scanner.nextInt();

        int [] arr=new int[size];

        for (int i=0;i<arr.length;i++){
            arr[i]=scanner.nextInt();
        }

        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[i];j++){
                System.out.print(sign);
            }
            System.out.println();
        }

    }

}
