import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 5000;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat Server is running...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Set up input and output streams
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Add this client's writer to the set
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Welcome message
                String clientAddress = socket.getInetAddress().getHostAddress();
                System.out.println("New client connected from " + clientAddress);
                broadcast("New user connected from " + clientAddress);

                // Handle messages from this client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("bye")) {
                        break;
                    }
                    broadcast(clientAddress + ": " + message);
                }
            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            } finally {
                // Remove client's writer from the set
                synchronized (clientWriters) {
                    if (out != null) {
                        clientWriters.remove(out);
                    }
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing client socket: " + e.getMessage());
                }

                broadcast("User disconnected from " + socket.getInetAddress().getHostAddress());
            }
        }

        // Broadcast message to all connected clients
        private void broadcast(String message) {
            synchronized (clientWriters) {
                System.out.println(message); // Print on server console
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}