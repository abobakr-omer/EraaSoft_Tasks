package question_5;

public class Student extends ShareData {

    private Integer age;

    Student(String name, Integer id, String phoneNumber, Integer age) {
        super(name, id, phoneNumber);
        setAge(age);
    }

    public void setAge(Integer age) {
        if (age != null && age > 7 && age < 30) {
            this.age = age;
        } else {
            System.out.println("Student age must be greater than 7 and less than 30");
        }
    }

    public Integer getAge() {
        return age;
    }

    public void printStudentData() {
        System.out.println("Student:");
        System.out.println("  ID    : " + getId());
        System.out.println("  Name  : " + getName());
        System.out.println("  Phone : " + getPhoneNumber());
        System.out.println("  Age   : " + age);
    }

}
