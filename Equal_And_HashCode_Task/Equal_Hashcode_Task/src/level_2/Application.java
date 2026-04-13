package level_2;

import level_1.Person;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        Map<Person,String> persons=new HashMap<>();

        persons.put(new Person(1, "Ahmed"), "Value 1");
        persons.put(new Person(1, "Mohamed"), "Value 2");
        persons.put(new Person(2, "Ali"), "Value 3");
        persons.put(new Person(3, "Sara"), "Value 4");
        persons.put(new Person(3, "Mona"), "Value 5");
        persons.put(new Person(4, "Omar"), "Value 6");
        persons.put(new Person(5, "Laila"), "Value 7");
        persons.put(new Person(5, "Nour"), "Value 8");
        persons.put(new Person(6, "Hany"), "Value 9");
        persons.put(new Person(7, "Yara"), "Value 10");


        Person personSearch=new Person(1,"Ahmed");

        System.out.println(persons.containsKey(personSearch));



    }


}
