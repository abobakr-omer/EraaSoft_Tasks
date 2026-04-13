package opp_8_to_18.question_13;

public class Shirt {

    private int number;
    private String color;

    public Shirt(int number,String color){
        this.number=number;
        this.color=color;
    }

    public void printShirt(){
        System.out.println("Shirt Number: " + number);
        System.out.println("Shirt Color: " + color);
    }


}
