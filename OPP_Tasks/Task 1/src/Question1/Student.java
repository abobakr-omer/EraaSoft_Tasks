package Question1;

public class Student {

    //Variables
    String name;
    int age;
    int id;

   static int countStudent=0;

   //Constructor
    Student(String name,int age,int id){
        this.name=name;
        this.age=age;
        this.id=id;
        countStudent++;
    }

    //Methods, Class' behaviors
    public void printStudentData(){
        System.out.println("Name: "+this.name);
        System.out.println("age: "+this.age);
        System.out.println("id: "+this.id);

    }


    public static void printStudentNumbers(){
        System.out.println("Student Numbers: "+countStudent);
    }



}
