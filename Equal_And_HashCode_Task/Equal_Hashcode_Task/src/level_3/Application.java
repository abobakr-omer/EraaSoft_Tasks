package level_3;

import level_1.Person;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<level_1.Person,String> persons=new HashMap<>();

        persons.put(new level_1.Person(1, "Ahmed"), "Value 1");
        persons.put(new level_1.Person(1, "Mohamed"), "Value 2");
        persons.put(new Person(2, "Ali"), "Value 3");


        Person personSearch=new Person(1,"Ahmed");

        System.out.println(persons.containsKey(personSearch));


    }


}
