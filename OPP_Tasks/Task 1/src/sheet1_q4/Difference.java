package sheet1_q4;

public class Difference {

    int num1;
    int num2;
    int num3;
    int num4;

    Difference(int num1, int num2,int num3, int num4){
        this.num1=num1;
        this.num2=num2;
        this.num3=num3;
        this.num4=num4;
    }

    private int differenceResult(){
       return (num1*num2)-(num3*num4);
    }

    public void printDifferenceResult(){
        System.out.println("Difference = "+ differenceResult());
    }






}
