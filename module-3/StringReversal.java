package javaexp;

import java.util.Scanner;

public class StringReversal {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user for a string
            System.out.print("Enter a string to reverse: ");
            String original = input.nextLine();

            // Use StringBuilder to reverse the string
            StringBuilder reversed = new StringBuilder(original);
            reversed.reverse();

            // Display the reversed string
            System.out.println("Reversed string: " + reversed.toString());
        }
    }
}
