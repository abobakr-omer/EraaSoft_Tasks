package operations;

import java.util.*;
import java.util.stream.Collectors;

public class OptionalOperations {

    public static void flattenList(List<List<String>> nestedWords) {
        List<String> flattened = nestedWords.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattened);
    }

    public static void extractUniqueChars(List<String> words) {
        Set<Character> uniqueChars = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toSet());
        System.out.println("Unique characters: " + uniqueChars);
    }

    public static void filterNonEmptyOptionals(List<Optional<String>> optionals) {
        List<String> nonEmptyOptionals = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Non-empty Optionals: " + nonEmptyOptionals);
    }

    public static void mapToLengths(List<String> words) {
        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Lengths of words: " + lengths);
    }

    public static void returnUppercaseStartingWithA(List<String> words) {
        List<String> result = words.stream()
                .filter(word -> word.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase words starting with A: " + result);
    }
}