import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DecompileDemo {
    // Instance variables with different access modifiers
    private final String name;
    protected int value;
    public static final double PI = 3.14159;

    // Inner class
    private class InnerHelper {
        private String prefix;

        InnerHelper(String prefix) {
            this.prefix = prefix;
        }

        String formatMessage(String msg) {
            return prefix + ": " + msg;
        }
    }

    // Constructor with initialization
    public DecompileDemo(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Method using lambda and streams
    public List<Integer> processNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n > 0)
                .map(n -> n * value)
                .collect(Collectors.toList());
    }

    // Method with try-with-resources and generic type
    public <T> void processWithResource(T data) {
        try (AutoCloseable resource = new AutoCloseable() {
            @Override
            public void close() throws Exception {
                System.out.println("Closing resource for: " + data);
            }
        }) {
            System.out.println("Processing: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Synchronized method with enum
    private enum Status {
        ACTIVE, INACTIVE, PENDING
    }

    public synchronized Status checkStatus() {
        if (value > 0) {
            return Status.ACTIVE;
        } else if (value < 0) {
            return Status.INACTIVE;
        }
        return Status.PENDING;
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        DecompileDemo demo = new DecompileDemo("Test", 5);

        // Test lambda and streams
        List<Integer> numbers = new ArrayList<>();
        numbers.add(-1);
        numbers.add(2);
        numbers.add(3);
        System.out.println("Processed numbers: " + demo.processNumbers(numbers));

        // Test generic method
        demo.processWithResource("Sample Data");

        // Test inner class
        InnerHelper helper = demo.new InnerHelper("DEBUG");
        System.out.println(helper.formatMessage("Test message"));

        // Test synchronized method
        System.out.println("Status: " + demo.checkStatus());
    }
}