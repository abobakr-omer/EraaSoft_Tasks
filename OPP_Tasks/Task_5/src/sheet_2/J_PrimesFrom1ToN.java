import java.util.Scanner;

public class J_PrimesFrom1ToN {

    public static boolean isPrime(int num){
        if (num<2){
            return false;
        }
        for (int i=2;i<num;i++){
            if (num%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        int num=scanner.nextInt();

        boolean first = true;

        for (int i = 2; i <= num; i++) {
            if (isPrime(i)) {
                if (!first) {
                    System.out.print(" ");
                }
                System.out.print(i);
                first = false;
            }
        }

    }


}
