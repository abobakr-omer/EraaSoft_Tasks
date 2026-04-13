package Question1;


public class Main {
    public static void main(String[] args) {

        Student student1=new Student("Ahmed",25,1);
        Student student2=new Student("Hossam",25,2);
        Student student3=new Student("bakr",25,3);

        student1.printStudentData();
        student2.printStudentData();
        student3.printStudentData();

        Student.printStudentNumbers();

    }
}