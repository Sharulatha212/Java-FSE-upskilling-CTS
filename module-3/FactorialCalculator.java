package javaexp;

import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt user for a non-negative integer
            System.out.print("Enter a non-negative integer: ");
            int number = input.nextInt();

            if (number < 0) {
                System.out.println("Invalid input! Factorial is not defined for negative numbers.");
            } else {
                long factorial = 1;  // Use long to handle larger factorials

                // Calculate factorial using for loop
                for (int i = 1; i <= number; i++) {
                    factorial *= i;
                }

                System.out.println("Factorial of " + number + " is: " + factorial);
            }
        }
    }
}
