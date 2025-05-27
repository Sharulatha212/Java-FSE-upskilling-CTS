package javaexp;

//Thread class by extending Thread
class MessageThread extends Thread {
 private String message;

 public MessageThread(String message) {
     this.message = message;
 }

 public void run() {
     for (int i = 0; i < 5; i++) {
         System.out.println(message + " - Count: " + (i + 1));
         try {
             Thread.sleep(500); // Pause for half a second
         } catch (InterruptedException e) {
             System.out.println("Thread interrupted");
         }
     }
 }
}

public class ThreadCreation {
 public static void main(String[] args) {
     // Create two thread instances
     MessageThread thread1 = new MessageThread("Thread 1 message");
     MessageThread thread2 = new MessageThread("Thread 2 message");

     // Start both threads
     thread1.start();
     thread2.start();
 }
}
