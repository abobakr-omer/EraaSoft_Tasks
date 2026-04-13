package operations;

import model.Employee;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsOperations {

    public static void groupByDepartment(List<Student> students) {
        Map<String, List<Student>> groupedByDepartment = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println("Students grouped by department: " + groupedByDepartment);
    }

    public static void partitionEvenOdd(List<Integer> numbers) {
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Partitioned even and odd numbers: " + partitioned);
    }

    public static void createCommaSeparatedString(List<String> names) {
        String result = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("Comma-separated names: " + result);
    }

    public static void groupByAge(List<Employee> employees) {
        Map<Integer, Long> employeesByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));
        System.out.println("Employees grouped by age: " + employeesByAge);
    }

    public static void averageSalaryByDepartment(List<Employee> employees) {
        Map<String, Double> averageSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average salary by department: " + averageSalaryByDept);
    }
}