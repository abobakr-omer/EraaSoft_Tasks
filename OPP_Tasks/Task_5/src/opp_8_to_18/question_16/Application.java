package opp_8_to_18.question_16;

public class Application {

    public static void main(String[] args) {
        Person p1 = new Person(1, "Ahmed");
        Food f1 = new Food(101, "Pizza", 150);
        Bill b1 = new Bill(1001, 150);
        Gift g1 = new Gift(501, "Juice");

        Order o1 = new Order(1, p1, f1, b1, g1);

        Restaurant restaurant = new Restaurant();
        restaurant.addOrder(o1);

        restaurant.printOrdersData();
    }


}
