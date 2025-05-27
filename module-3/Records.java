package javaexp;

import java.util.List;
import java.util.stream.Collectors;

public class Records {

    // Define the record Person with two components: name and age
    public record Person(String name, int age) {}

    public static void main(String[] args) {
        // Create instances of Person
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 17);
        Person p3 = new Person("Charlie", 30);

        // Print the person instances
        System.out.println("Persons:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // Create a List of Persons
        List<Person> persons = List.of(p1, p2, p3);

        // Filter persons aged 18 or older using Stream API
        List<Person> adults = persons.stream()
                                     .filter(person -> person.age() >= 18)
                                     .collect(Collectors.toList());

        // Display filtered list
        System.out.println("\nAdults (age >= 18):");
        adults.forEach(System.out::println);
    }
}
