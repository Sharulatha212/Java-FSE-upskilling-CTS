package javaexp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriting {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt the user for input
            System.out.print("Enter a string to write to the file: ");
            String userInput = scanner.nextLine();

            // Write to the file
            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(userInput);
                System.out.println("Data has been written to output.txt successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }
    }
}

