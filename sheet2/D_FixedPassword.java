import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D_FixedPassword {

    public static void main(String[] args) {


        Scanner scanner=new Scanner(System.in);

        while (true){
         int number=scanner.nextInt();
            if (number==1999){
                System.out.println("Correct");
                return;
            }
            else {
                System.out.println("Wrong");
            }
        }


    }

}
