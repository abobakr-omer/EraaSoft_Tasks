package Sheet_2_OPP.question_N;

import java.util.ArrayList;
import java.util.Scanner;

public class NumbersHistogram {

    private Character sign;
    private Integer n;

    public NumbersHistogram(Character sign,Integer n){
        this.sign=sign;
        this.n=n;
    }


    public void printNumbersHistogram(Scanner scanner){
        ArrayList<Integer> arrayList=new ArrayList<>();
        for (int index=0;index<n;index++){
            arrayList.add(scanner.nextInt());
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<arrayList.get(i);j++){
                System.out.print(sign);
            }
            System.out.println();
        }


    }


}
