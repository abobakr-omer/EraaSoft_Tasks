package level_1;

public class Application {

    public static void main(String[] args) {

        Person person1=new Person(1,"ahmed");
        Person person2=new Person(1,"ahmed");
        Person person3=new Person(2,"bakr");

        System.out.println(person1.equals(person2));

        System.out.println(person1.equals(person3));



    }


}
