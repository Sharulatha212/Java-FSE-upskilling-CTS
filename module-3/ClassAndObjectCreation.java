package javaexp;

//Car class with attributes and method
class Car {
 // Attributes
 String make;
 String model;
 int year;

 // Method to display car details
 void displayDetails() {
     System.out.println("Car Make: " + make);
     System.out.println("Car Model: " + model);
     System.out.println("Car Year: " + year);
     System.out.println("----------------------");
 }
}

//Main class to create and use Car objects
public class ClassAndObjectCreation {
 public static void main(String[] args) {
     // Create first Car object
     Car car1 = new Car();
     car1.make = "Toyota";
     car1.model = "Corolla";
     car1.year = 2020;

     // Create second Car object
     Car car2 = new Car();
     car2.make = "Honda";
     car2.model = "Civic";
     car2.year = 2022;

     // Call displayDetails() method
     System.out.println("Car 1 Details:");
     car1.displayDetails();

     System.out.println("Car 2 Details:");
     car2.displayDetails();
 }
}
