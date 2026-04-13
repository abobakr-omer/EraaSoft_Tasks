import java.util.Scanner;

public class V_PUM {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n=scanner.nextInt();
        int num=1;

        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++){
                System.out.print(num+" ");
                num++;
            }
            System.out.println("PUM");
            num++;
        }

    }

}
