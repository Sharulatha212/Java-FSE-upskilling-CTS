package javaexp;

import java.util.Scanner;

public class Multipilicationtable {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user for a number
            System.out.print("Enter a number: ");
            int number = input.nextInt();

            // Print multiplication table using a for loop
            System.out.println("Multiplication Table for " + number + ":");
            for (int i = 1; i <= 10; i++) {
                int result = number * i;
                System.out.println(number + " x " + i + " = " + result);
            }
        }
    }
}
