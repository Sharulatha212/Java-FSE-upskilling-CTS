package javaexp;

import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user for a string
            System.out.print("Enter a string: ");
            String original = input.nextLine();

            // Remove non-alphanumeric characters and convert to lowercase
            String cleaned = original.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

            // Reverse the cleaned string
            StringBuilder reversed = new StringBuilder(cleaned).reverse();

            // Check if the cleaned string equals its reverse
            if (cleaned.equals(reversed.toString())) {
                System.out.println("The string is a palindrome.");
            } else {
                System.out.println("The string is not a palindrome.");
            }
        }
    }
}
