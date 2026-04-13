package question_6;

public class Student {

    private Integer id;
    private String name;

    public Student(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(Integer id) {
        if (id != null && id > 0) {
            this.id = id;
        } else {
            System.out.println("ID must be greater than Zero");
        }
    }

    public void setName(String name) {
        if (name != null && name.matches("[a-zA-Z ]+") && name.length() >= 3) {
            this.name = name;
        } else {
            System.out.println("Name must be at least 3 characters and contain only letters and spaces");
        }
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void printData() {
        System.out.println("Student:");
        System.out.println("  ID   : " + id);
        System.out.println("  Name : " + name);
    }


}
