package opp_8_to_18.question_16;

public class Person {

    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void printPersonData() {
        System.out.println("Person Id: " + id);
        System.out.println("Person Name: " + name);
    }

}
