package opp_8_to_18.question_14;

public class Student {

    private int id;
    private String name;
    private Email email;

    public Student(int id,String name,Email email){
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public void printStudentData(){
        System.out.println("Student Id: " + id);
        System.out.println("Student Name: " + name);
        email.printEmail();
    }




}
