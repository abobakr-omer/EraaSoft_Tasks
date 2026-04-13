package question_2_2;

public class Teacher {

    private Long id; // id > 0
    private String name; // size >=3 and all digits char(a-z)
    private float age; // age >= 25 and age <= 60
    private String phoneNumber; //  +20111390660 13 cher and start with +20
    private float salary;  // salary >= 3000


    public void setId(Long id) {
        if (id != null && id > 0) {
            this.id = id;
        } else {
            System.out.println("ID must be greater than 0");
        }
    }

    public void setName(String name) {
        if (name != null && name.length() >= 3 && name.matches("[a-zA-Z ]+")) {
            this.name = name;
        } else {
            System.out.println("Name must be at least 3 characters and contain only letters and spaces");
        }
    }

    public void setAge(float age) {
        if (age >= 25 && age <= 60) {
            this.age = age;
        } else {
            System.out.println("Age must be between 25 and 60");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.matches("\\+20\\d{11}")) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Phone Number must start with +20 and its length is 13 characters");
        }
    }

    public void setSalary(float salary) {
        if (salary >= 3000) {
            this.salary = salary;
        } else {
            System.out.println("Salary must be greater than or equal 3000");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getSalary() {
        return salary;
    }
}
