package task_4;

public class App {

    public static void main(String[] args) {
        Employee employee1=new Employee(1,"ahmed",26);
        Phone phone1=new Phone(1,"01012345678");

        employee1.setPhone(phone1);

        System.out.println(phone1.getEmployee().getName());
        System.out.println(employee1.getPhone().getPhoneNumber());

    }

}
