package javaexp;

import java.util.Scanner;

//Define the custom exception
class InvalidAgeException extends Exception {
 public InvalidAgeException(String message) {
     super(message);
 }
}

public class CustomException {
 // Method to check age
 static void checkAge(int age) throws InvalidAgeException {
     if (age < 18) {
         throw new InvalidAgeException("Invalid age! You must be 18 or older.");
     } else {
         System.out.println("Age is valid. You are allowed to proceed.");
     }
 }

 public static void main(String[] args) {
     try (Scanner input = new Scanner(System.in)) {
         // Prompt the user for age
         System.out.print("Enter your age: ");
         int age = input.nextInt();

         // Check the age
         try {
             checkAge(age);
         } catch (InvalidAgeException e) {
             System.out.println("Exception caught: " + e.getMessage());
         }
     }
 }
}

