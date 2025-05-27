package javaexp;

import java.util.Scanner;

public class ArraySumAverage {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the number of elements: ");
            int n = input.nextInt();

            if (n <= 0) {
                System.out.println("Number of elements must be positive.");
                return;
            }

            int[] numbers = new int[n];
            int sum = 0;

            // Read elements into the array
            System.out.println("Enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                numbers[i] = input.nextInt();
                sum += numbers[i];
            }

            // Calculate average
            double average = (double) sum / n;

            // Display sum and average
            System.out.println("Sum of elements: " + sum);
            System.out.println("Average of elements: " + average);
        }
    }
}

