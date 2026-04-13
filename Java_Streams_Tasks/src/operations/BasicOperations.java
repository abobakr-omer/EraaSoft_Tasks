package operations;

import java.util.*;
import java.util.stream.Collectors;

public class BasicOperations {

    public static void filterEvenNumbers(List<Integer> numbers) {
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
    }

    public static void findNamesStartingWithA(List<String> names) {
        List<String> namesStartingWithA = names.stream()
                .filter(name -> name != null && !name.isEmpty() && name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Names starting with A: " + namesStartingWithA);
    }

    public static void convertToUppercase(List<String> names) {
        List<String> uppercasedNames = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercased names: " + uppercasedNames);
    }

    public static void sortListDescending(List<Integer> numbers) {
        List<Integer> sortedDescending = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Sorted in descending order: " + sortedDescending);
    }

    public static void removeDuplicates(List<Integer> numbers) {
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct numbers: " + distinctNumbers);
    }
}