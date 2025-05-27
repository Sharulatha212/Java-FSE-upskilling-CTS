package javaexp;

//Define the interface
interface Playable {
 void play();  // Abstract method
}

//Guitar class implementing Playable
class Guitar implements Playable {
 public void play() {
     System.out.println("Playing the guitar...");
 }
}

//Piano class implementing Playable
class Piano implements Playable {
 public void play() {
     System.out.println("Playing the piano...");
 }
}

//Main class to test the interface
public class InterfaceImplementation {
 public static void main(String[] args) {
     // Create objects of Guitar and Piano
     Playable myGuitar = new Guitar();
     Playable myPiano = new Piano();

     // Call the play method
     myGuitar.play();
     myPiano.play();
 }
}

