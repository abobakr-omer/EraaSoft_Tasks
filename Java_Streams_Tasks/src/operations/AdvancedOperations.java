package operations;

import model.Employee;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedOperations {

    public static void sortBySalaryAndName(List<Employee> employees) {
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());
        sortedEmployees.forEach(employee -> System.out.println(employee.getName() + " - " + employee.getSalary()));
    }

    public static void findSecondHighestNumber(List<Integer> numbers) {
        List<Integer> sortedNumbers = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        Integer secondHighest = sortedNumbers.size() > 1 ? sortedNumbers.get(1) : null;
        System.out.println("Second highest number: " + secondHighest);
    }

    public static void findDuplicateElements(List<Integer> numbers) {
        Map<Integer, Long> duplicates = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        duplicates.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + " appears " + entry.getValue() + " times"));
    }

    public static void removeNullOrEmptyStrings(List<String> names) {
        List<String> cleanedNames = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Cleaned names: " + cleanedNames);
    }

    public static void partitionPassFailGroups(List<Student> students) {
        Map<Boolean, List<Student>> passFailGroups = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getGrade() >= 60));
        System.out.println("Pass/Fail groups: " + passFailGroups);
    }
}