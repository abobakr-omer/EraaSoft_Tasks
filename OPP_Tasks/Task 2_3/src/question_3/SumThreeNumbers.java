package question_3;

public class SumThreeNumbers {

    private int num1,num2,num3;

    SumThreeNumbers(int num1,int num2,int num3){
        if((num1%2!=0)||(num2%2!=0)||(num3%2!=0))
            throw new IllegalArgumentException("All three numbers must be even");
        this.num1=num1;
        this.num2=num2;
        this.num3=num3;
    }


    public int sumOfThreeNumbers(){
        return (num1+num2+num3);
    }


}
