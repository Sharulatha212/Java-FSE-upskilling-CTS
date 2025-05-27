package javaexp;

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user for marks
            System.out.print("Enter marks (out of 100): ");
            int marks = input.nextInt();

            // Validate marks input
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks entered. Please enter a value between 0 and 100.");
            } else {
                // Assign grades based on marks
                if (marks >= 90) {
                    System.out.println("Grade: A");
                } else if (marks >= 80) {
                    System.out.println("Grade: B");
                } else if (marks >= 70) {
                    System.out.println("Grade: C");
                } else if (marks >= 60) {
                    System.out.println("Grade: D");
                } else {
                    System.out.println("Grade: F");
                }
            }
        }
    }
}
