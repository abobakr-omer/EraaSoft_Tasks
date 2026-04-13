package opp_8_to_18.question_16;

public class Order {

    int orderId;
    Person person;   // Order has a Person
    Food food;       // Order has a Food
    Bill bill;       // Order has a Bill
    Gift gift;       // Order has a Gift

    public Order(int orderId, Person person, Food food, Bill bill, Gift gift) {
        this.orderId = orderId;
        this.person = person;
        this.food = food;
        this.bill = bill;
        this.gift = gift;
    }


    public void showOrder() {
        System.out.println("Order Id: " + orderId);
        person.printPersonData();
        food.printFoodData();
        bill.printBillData();
        gift.printGiftData();
    }


}
