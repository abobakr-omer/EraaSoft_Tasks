package opp_8_to_18.question_11;

public class RestaurantControl implements Restaurant {

    private String order;



    @Override
    public void addOrder(String order) {
        this.order=order;
    }

    @Override
    public void showOrder() {
        System.out.println("Your Order: " + order);
    }
}
