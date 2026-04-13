package question_4;

public class Student extends Person {

    private Integer age;

    Student(Integer id, String name, Integer age) {
        super(id, name);
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
        System.out.println("  Name : " + getName());
        System.out.println("  ID   : " + getId());
        System.out.println("  Age  : " + getAge());
    }

}
