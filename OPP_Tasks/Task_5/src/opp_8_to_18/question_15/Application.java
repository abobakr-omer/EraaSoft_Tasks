package opp_8_to_18.question_15;

import opp_8_to_18.question_14.Email;

public class Application {

    public static void main(String[] args) {

        Course course1=new Course("Java course");

        Student student1=new Student(1,"Bakr",course1);

        student1.printStudentData();


    }


}
