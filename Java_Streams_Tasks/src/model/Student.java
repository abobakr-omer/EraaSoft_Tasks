package model;

public class Student {
    private String name;
    private String department;
    private double grade;

    // Constructor
    public Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getGrade() {
        return grade;
    }

    // Optional: Override toString() for better output formatting
    @Override
    public String toString() {
        return "Student{name='" + name + "', department='" + department + "', grade=" + grade + "}";
    }
}