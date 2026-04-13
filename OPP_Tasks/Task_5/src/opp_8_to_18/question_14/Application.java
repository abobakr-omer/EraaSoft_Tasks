package opp_8_to_18.question_14;

public class Application {

    public static void main(String[] args) {

        Email email1=new Email("test@gmail.com","Dear student how are you?");

        Student student1=new Student(1,"Bakr",email1);

        student1.printStudentData();


    }


}
