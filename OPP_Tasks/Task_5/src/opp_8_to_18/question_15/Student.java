package opp_8_to_18.question_15;

import opp_8_to_18.question_14.Email;

public class Student {

    private int id;
    private String name;
    private Course course;

    public Student(int id,String name,Course course){
        this.id=id;
        this.name=name;
        this.course=course;
    }

    public void printStudentData(){
        System.out.println("Student Id: " + id);
        System.out.println("Student Name: " + name);
        course.printCourses();
    }




}
