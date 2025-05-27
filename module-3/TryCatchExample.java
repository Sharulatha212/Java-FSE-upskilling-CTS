package javaexp;

import java.util.Scanner;

public class TryCatchExample {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user for two integers
            System.out.print("Enter the numerator: ");
            int numerator = input.nextInt();

            System.out.print("Enter the denominator: ");
            int denominator = input.nextInt();

            // Try to divide and catch exceptions
            try {
                int result = numerator / denominator;
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: Cannot divide by zero.");
            }
        }
    }
}
