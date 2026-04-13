package sheet1_q3;

public class SimpleCalculator {

    long num1;
    long num2;

    SimpleCalculator(long num1,long num2){
        this.num1=num1;
        this.num2=num2;
    }


    public void summationNumbers(){
        System.out.println("summation= "+(num1+num2));
    }

    public void multiplicationNumbers(){
        System.out.println("multiplication= "+(num1*num2));
    }

    public void subtractionNumbers(){
        System.out.println("subtraction= "+(num1-num2));
    }



}
