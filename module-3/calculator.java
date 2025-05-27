package javaexp;
import java.util.Scanner;
public class calculator {

	    public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);

	        // Prompt the user to enter two numbers
	        System.out.print("Enter the first number: ");
	        double num1 = input.nextDouble();

	        System.out.print("Enter the second number: ");
	        double num2 = input.nextDouble();

	        // Ask the user to choose an operation
	        System.out.println("Choose an operation: +, -, *, /");
	        char operator = input.next().charAt(0);

	        double result;

	        // Perform the selected operation
	        switch (operator) {
	            case '+':
	                result = num1 + num2;
	                System.out.println("Result: " + result);
	                break;
	            case '-':
	                result = num1 - num2;
	                System.out.println("Result: " + result);
	                break;
	            case '*':
	                result = num1 * num2;
	                System.out.println("Result: " + result);
	                break;
	            case '/':
	                if (num2 != 0) {
	                    result = num1 / num2;
	                    System.out.println("Result: " + result);
	                } else {
	                    System.out.println("Error: Cannot divide by zero.");
	                }
	                break;
	            default:
	                System.out.println("Invalid operation.");
	        }

	        input.close();
	    }
	}

