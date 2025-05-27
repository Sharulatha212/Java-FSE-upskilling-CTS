package javaexp;

public class OperatorPrecedence {
    public static void main(String[] args) {
        // Example 1
        int result1 = 10 + 5 * 2;
        System.out.println("Result of 10 + 5 * 2 = " + result1);
        System.out.println("Explanation: 5 * 2 is evaluated first (multiplication has higher precedence), then added to 10.");

        // Example 2
        int result2 = (10 + 5) * 2;
        System.out.println("\nResult of (10 + 5) * 2 = " + result2);
        System.out.println("Explanation: Parentheses are evaluated first, so 10 + 5 = 15, then multiplied by 2.");

        // Example 3
        int result3 = 20 - 4 + 2;
        System.out.println("\nResult of 20 - 4 + 2 = " + result3);
        System.out.println("Explanation: Left-to-right evaluation for same-precedence operators (subtraction and addition).");

        // Example 4
        int result4 = 10 + 6 / 3 * 2;
        System.out.println("\nResult of 10 + 6 / 3 * 2 = " + result4);
        System.out.println("Explanation: Division and multiplication are evaluated left to right: (6 / 3) = 2, then 2 * 2 = 4, then 10 + 4 = 14.");
    }
}
