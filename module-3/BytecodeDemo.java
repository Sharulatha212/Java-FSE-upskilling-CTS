public class BytecodeDemo {
    private int instanceVar;
    private static final int CONSTANT = 42;

    // Constructor
    public BytecodeDemo(int value) {
        this.instanceVar = value;
    }

    // Instance method with loop
    public int calculateSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // Static method with recursion
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // Method with try-catch
    public void exceptionDemo(int value) {
        try {
            if (value < 0) {
                throw new IllegalArgumentException("Value must be positive");
            }
            this.instanceVar = value;
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    // Method using switch statement
    public String numberToWord(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            default:
                return "Unknown";
        }
    }

    // Main method to demonstrate the class
    public static void main(String[] args) {
        BytecodeDemo demo = new BytecodeDemo(10);

        // Test calculateSum
        System.out.println("Sum of numbers from 1 to 5: " + demo.calculateSum(5));

        // Test factorial
        System.out.println("Factorial of 5: " + factorial(5));

        // Test exception handling
        demo.exceptionDemo(-1);
        demo.exceptionDemo(20);

        // Test switch statement
        System.out.println("Number 2 in words: " + demo.numberToWord(2));
        System.out.println("Number 5 in words: " + demo.numberToWord(5));
    }
}