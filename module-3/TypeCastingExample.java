package javaexp;
public class TypeCastingExample {
    public static void main(String[] args) {
        // Declare a double variable
        double myDouble = 9.78;

        // Cast double to int (explicit casting)
        int myInt = (int) myDouble;
        System.out.println("Original double value: " + myDouble);
        System.out.println("After casting to int: " + myInt);

        // Declare an int variable
        int anotherInt = 25;

        // Cast int to double (implicit casting)
        double anotherDouble = anotherInt;
        System.out.println("Original int value: " + anotherInt);
        System.out.println("After casting to double: " + anotherDouble);
    }
}
