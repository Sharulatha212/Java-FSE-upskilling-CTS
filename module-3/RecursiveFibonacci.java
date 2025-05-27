package javaexp;

import java.util.Scanner;

public class RecursiveFibonacci {
    // Recursive method to calculate nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;  // Base cases: fib(0) = 0, fib(1) = 1
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a positive integer (n): ");
            int n = input.nextInt();

            if (n < 0) {
                System.out.println("Please enter a positive integer.");
            } else {
                int result = fibonacci(n);
                System.out.println("Fibonacci number at position " + n + " is: " + result);
            }
        }
    }
}
