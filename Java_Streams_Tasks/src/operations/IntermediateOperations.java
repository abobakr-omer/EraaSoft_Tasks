package operations;

import java.util.*;
import java.util.stream.Collectors;

public class IntermediateOperations {

    public static void countLongStrings(List<String> names) {
        long countLongNames = names.stream()
                .filter(name -> name != null && name.length() > 5)
                .count();
        System.out.println("Count of names longer than 5 characters: " + countLongNames);
    }

    public static void findFirstElementStartingWithA(List<String> names) {
        Optional<String> firstNameWithA = names.stream()
                .filter(name -> name != null && !name.isEmpty() && name.startsWith("A"))
                .findFirst();
        System.out.println("First name starting with A: " + firstNameWithA.orElse("No match found"));
    }

    public static void checkDivisibleByFive(List<Integer> numbers) {
        boolean anyDivBy5 = numbers.stream().anyMatch(n -> n % 5 == 0);
        System.out.println("Any number divisible by 5: " + anyDivBy5);
    }

    public static void collectIntoSet(List<String> names) {
        Set<String> uniqueNames = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .collect(Collectors.toSet());
        System.out.println("Unique names: " + uniqueNames);
    }

    public static void skipElements(List<Integer> numbers) {
        List<Integer> skipped = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("Skipped the first 3 elements: " + skipped);
    }
}
