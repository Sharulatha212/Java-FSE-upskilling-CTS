package javaexp;

import java.util.Scanner;

public class EvenOrOddChecker {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user for an integer
            System.out.print("Enter an integer: ");
            int number = input.nextInt();

            // Use modulus operator to check even or odd
            if (number % 2 == 0) {
                System.out.println(number + " is even.");
            } else {
                System.out.println(number + " is odd.");
            }
        }
    }
}
