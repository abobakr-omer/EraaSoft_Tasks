package question_6;

public class PublicSchoolStudent extends Student {

    public PublicSchoolStudent(Integer id, String name) {
        super(id, name);
    }

    public void printData() {
        System.out.println("Student:");
        System.out.println("  ID   : " + getId());
        System.out.println("  Name : " + getName());
    }

}
