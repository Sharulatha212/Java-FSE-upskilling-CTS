package javaexp;

import java.util.Scanner;

public class LeapYearChecker {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Prompt the user to enter a year
            System.out.print("Enter a year: ");
            int year = input.nextInt();

            // Apply leap year logic
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) {
                        System.out.println(year + " is a leap year.");
                    } else {
                        System.out.println(year + " is not a leap year.");
                    }
                } else {
                    System.out.println(year + " is a leap year.");
                }
            } else {
                System.out.println(year + " is not a leap year.");
            }
        }
    }
}
