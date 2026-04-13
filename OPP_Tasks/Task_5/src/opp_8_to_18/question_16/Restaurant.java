package opp_8_to_18.question_16;

import java.util.ArrayList;

public class Restaurant {

    private ArrayList<Order> orders=new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }

    public void printOrdersData(){
        System.out.println("Restaurant's Orders:");
        for (Order order: orders){
            order.showOrder();
            System.out.println("=================");
        }
    }


}
