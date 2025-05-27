package com.greetings;

import com.utils.StringUtils;

public class Main {
    public static void main(String[] args) {
        String name = "world";

        // Using StringUtils methods from com.utils module
        String capitalizedName = StringUtils.capitalize(name);
        String repeatedGreeting = StringUtils.repeat("Hello ", 3);
        String reversedName = StringUtils.reverse(name);

        System.out.println("Original name: " + name);
        System.out.println("Capitalized name: " + capitalizedName);
        System.out.println("Repeated greeting: " + repeatedGreeting);
        System.out.println("Reversed name: " + reversedName);

        // Create a complete greeting
        String greeting = repeatedGreeting + capitalizedName + "!";
        System.out.println("\nFinal greeting: " + greeting);
    }
}