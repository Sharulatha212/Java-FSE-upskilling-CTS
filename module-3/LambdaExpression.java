package javaexp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpression {
    public static void main(String[] args) {
        // Create a list of strings
        List<String> names = new ArrayList<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("Bob");
        names.add("David");

        // Sort the list using a lambda expression (lexicographical order)
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

        // Display the sorted list
        System.out.println("Sorted names:");
        names.forEach(System.out::println);
    }
}

