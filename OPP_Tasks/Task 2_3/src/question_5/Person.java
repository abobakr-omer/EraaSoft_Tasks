package question_5;

public class Person extends BaseEntity {

    Person(String name, Integer id) {
        super(name, id);
    }

    public void printPersonData() {
        System.out.println("Person:");
        System.out.println("  ID   : " + getId());
        System.out.println("  Name : " + getName());
    }

}
