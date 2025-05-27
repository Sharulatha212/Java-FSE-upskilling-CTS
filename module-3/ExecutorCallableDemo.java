import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.time.Instant;
import java.time.Duration;
import java.util.Random;

public class ExecutorCallableDemo {
    // Simulates a task that processes data and returns a result
    static class DataProcessor implements Callable<ProcessingResult> {
        private final int taskId;
        private final int dataSize;
        private final Random random;

        public DataProcessor(int taskId, int dataSize) {
            this.taskId = taskId;
            this.dataSize = dataSize;
            this.random = new Random();
        }

        @Override
        public ProcessingResult call() throws Exception {
            // Simulate processing time based on data size
            Thread.sleep(random.nextInt(1000)); // Random processing time up to 1 second

            // Simulate data processing and generate result
            int processedItems = random.nextInt(dataSize);
            double successRate = (double) processedItems / dataSize;

            return new ProcessingResult(
                    taskId,
                    processedItems,
                    dataSize,
                    successRate,
                    Thread.currentThread().getName());
        }
    }

    // Value object to hold processing results
    static class ProcessingResult {
        private final int taskId;
        private final int processedItems;
        private final int totalItems;
        private final double successRate;
        private final String threadName;

        public ProcessingResult(int taskId, int processedItems, int totalItems,
                double successRate, String threadName) {
            this.taskId = taskId;
            this.processedItems = processedItems;
            this.totalItems = totalItems;
            this.successRate = successRate;
            this.threadName = threadName;
        }

        @Override
        public String toString() {
            return String.format(
                    "Task %d [Thread: %s] - Processed %d/%d items (%.2f%% success rate)",
                    taskId, threadName, processedItems, totalItems, successRate * 100);
        }

        public double getSuccessRate() {
            return successRate;
        }
    }

    public static void main(String[] args) {
        int numberOfTasks = 10;
        int dataSize = 1000;

        // Create a fixed thread pool with number of cores + 1 threads
        int numberOfThreads = Runtime.getRuntime().availableProcessors() + 1;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        System.out.printf("Starting execution with %d threads for %d tasks%n",
                numberOfThreads, numberOfTasks);

        Instant start = Instant.now();

        try {
            // Create and submit tasks
            List<Future<ProcessingResult>> futures = new ArrayList<>();
            for (int i = 0; i < numberOfTasks; i++) {
                Callable<ProcessingResult> task = new DataProcessor(i + 1, dataSize);
                futures.add(executor.submit(task));
            }

            // Collect and process results
            List<ProcessingResult> results = new ArrayList<>();
            for (Future<ProcessingResult> future : futures) {
                try {
                    ProcessingResult result = future.get(2, TimeUnit.SECONDS);
                    results.add(result);
                    System.out.println(result);
                } catch (TimeoutException e) {
                    System.err.println("Task timed out!");
                } catch (ExecutionException e) {
                    System.err.println("Task failed: " + e.getCause().getMessage());
                }
            }

            // Calculate and display summary statistics
            if (!results.isEmpty()) {
                double avgSuccessRate = results.stream()
                        .mapToDouble(ProcessingResult::getSuccessRate)
                        .average()
                        .orElse(0.0);

                System.out.printf("%nSummary:%n");
                System.out.printf("Total tasks completed: %d%n", results.size());
                System.out.printf("Average success rate: %.2f%%%n", avgSuccessRate * 100);
            }

        } catch (InterruptedException e) {
            System.err.println("Execution interrupted!");
            Thread.currentThread().interrupt();
        } finally {
            // Shutdown the executor service
            executor.shutdown();
            try {
                // Wait for all tasks to complete or timeout
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        Duration duration = Duration.between(start, Instant.now());
        System.out.printf("%nTotal execution time: %dms%n", duration.toMillis());
    }
}