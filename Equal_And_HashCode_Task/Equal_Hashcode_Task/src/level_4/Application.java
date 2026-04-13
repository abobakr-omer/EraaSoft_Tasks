package level_4;

import java.util.HashMap;
import java.util.HashSet;

public class Application {

    public static void main(String[] args) {

        HashSet<Product> products = new HashSet<>();
        products.add(new Product(100, 10));
        products.add(new Product(110, 20));
        products.add(new Product(200, 30));

        System.out.println("Products: " + products);

        HashSet<StudentById> studentsById = new HashSet<>();
        studentsById.add(new StudentById(1, "a@test.com"));
        studentsById.add(new StudentById(1, "b@test.com"));
        studentsById.add(new StudentById(2, "c@test.com"));

        System.out.println("Students by id: " + studentsById);

        HashSet<StudentByEmail> studentsByEmail = new HashSet<>();
        studentsByEmail.add(new StudentByEmail(1, "same@test.com"));
        studentsByEmail.add(new StudentByEmail(2, "same@test.com"));
        studentsByEmail.add(new StudentByEmail(3, "x@test.com"));

        System.out.println("Students by email: " + studentsByEmail);

        HashMap<Car, Integer> cars = new HashMap<>();

        cars.put(new Car("ABC123", "Red"),10);
        cars.put(new Car("ABC123", "Blue"),20);
        cars.put(new Car("XYZ999", "Black"),30);

        System.out.println("Cars Map"+cars);



    }


}
