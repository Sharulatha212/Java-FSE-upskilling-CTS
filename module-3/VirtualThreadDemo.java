import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadDemo {
    private static final int THREAD_COUNT = 100_000;
    private static final AtomicInteger completedTasks = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("Starting Virtual Threads vs Platform Threads comparison");
        System.out.println("Number of threads to create: " + THREAD_COUNT);

        // Test with platform threads
        System.out.println("\nTesting with Platform Threads:");
        runPlatformThreads();

        // Reset counter
        completedTasks.set(0);

        // Test with virtual threads
        System.out.println("\nTesting with Virtual Threads:");
        runVirtualThreads();
    }

    private static void runPlatformThreads() {
        List<Thread> threads = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        Instant start = Instant.now();

        try {
            // Create and start platform threads
            for (int i = 0; i < THREAD_COUNT; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        // Simulate some work (sleep for 10ms)
                        Thread.sleep(10);
                        completedTasks.incrementAndGet();
                        latch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                threads.add(thread);
            }

            // Start all threads
            for (Thread thread : threads) {
                thread.start();
            }

            // Wait for all threads to complete
            latch.await();

            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);

            System.out.println("Platform Threads completed: " + completedTasks.get());
            System.out.println("Total time taken: " + duration.toMillis() + "ms");
            System.out.println("Average time per thread: " +
                    (duration.toMillis() / (float) THREAD_COUNT) + "ms");

        } catch (InterruptedException e) {
            System.err.println("Execution interrupted: " + e.getMessage());
        }
    }

    private static void runVirtualThreads() {
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        List<Thread> virtualThreads = new ArrayList<>();

        Instant start = Instant.now();

        try {
            // Create and start virtual threads
            for (int i = 0; i < THREAD_COUNT; i++) {
                Thread vThread = Thread.ofVirtual().unstarted(() -> {
                    try {
                        // Simulate some work (sleep for 10ms)
                        Thread.sleep(10);
                        completedTasks.incrementAndGet();
                        latch.countDown();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                virtualThreads.add(vThread);
            }

            // Start all virtual threads
            for (Thread thread : virtualThreads) {
                thread.start();
            }

            // Wait for all threads to complete
            latch.await();

            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);

            System.out.println("Virtual Threads completed: " + completedTasks.get());
            System.out.println("Total time taken: " + duration.toMillis() + "ms");
            System.out.println("Average time per thread: " +
                    (duration.toMillis() / (float) THREAD_COUNT) + "ms");

        } catch (InterruptedException e) {
            System.err.println("Execution interrupted: " + e.getMessage());
        }
    }
}