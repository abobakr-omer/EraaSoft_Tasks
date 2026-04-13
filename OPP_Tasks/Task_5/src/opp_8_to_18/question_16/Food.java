package opp_8_to_18.question_16;

public class Food {


    String foodName;
    double price;

    public Food(int id, String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    public void printFoodData() {
        System.out.println("Food Name: " + foodName);
        System.out.println("Food Price: " + price);
    }


}
