package javaexp;
public class MethodOverloading {

    // Method to add two integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Method to add two doubles
    public static double add(double a, double b) {
        return a + b;
    }

    // Method to add three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        // Calling add methods with different parameters
        int sum1 = add(5, 10);
        double sum2 = add(4.5, 3.2);
        int sum3 = add(1, 2, 3);

        // Display results
        System.out.println("Sum of two integers (5 + 10): " + sum1);
        System.out.println("Sum of two doubles (4.5 + 3.2): " + sum2);
        System.out.println("Sum of three integers (1 + 2 + 3): " + sum3);
    }
}
