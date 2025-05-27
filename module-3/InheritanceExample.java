package javaexp;

//Base class
class Animal {
 void makeSound() {
     System.out.println("Some generic animal sound");
 }
}

//Subclass that inherits from Animal
class Dog extends Animal {
 // Overriding makeSound method
 @Override
 void makeSound() {
     System.out.println("Bark");
 }
}

//Main class to test the behavior
public class InheritanceExample {
 public static void main(String[] args) {
     // Create an object of Animal
     Animal genericAnimal = new Animal();
     System.out.print("Animal sound: ");
     genericAnimal.makeSound();

     // Create an object of Dog
     Dog dog = new Dog();
     System.out.print("Dog sound: ");
     dog.makeSound();
 }
}

