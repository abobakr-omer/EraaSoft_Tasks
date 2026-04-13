package sheet1_q5;

public class AreaOfACircle {

    double r;
    final double PI=3.141592653;

    AreaOfACircle(double r){
        this.r=r;
    }


    private double calculateCircleArea(double r ){
        return (PI*r*r);
    }

    public void printCircleArea(){
        System.out.println("Circle Area= "+calculateCircleArea(r));
    }



}
