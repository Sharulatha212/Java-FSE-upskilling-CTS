package javaexp;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Add student names to the list
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter name of student " + i + ": ");
            String name = scanner.nextLine();
            studentNames.add(name);
        }

        // Display all student names
        System.out.println("\nList of Students:");
        for (String name : studentNames) {
            System.out.println(name);
        }

        scanner.close();
    }
}
