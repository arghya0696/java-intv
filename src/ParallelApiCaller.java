import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope.*;
import java.util.function.Supplier;

public class ParallelApiCaller {

    private static String fetchData(String apiName, long delayMillis, boolean shouldFail) throws InterruptedException {



        // Use String.format instead of STR.
        System.out.println(String.format("Starting API call: %s on thread: %s", apiName, Thread.currentThread()));
        Thread.sleep(Duration.ofMillis(delayMillis)); // Simulate network latency
        if (shouldFail) {
            // Use String.format instead of STR.
            System.out.println(String.format("API call %s failed!", apiName));
            // Use String.format instead of STR.
            throw new RuntimeException(String.format("Simulated failure in %s", apiName));
        }
        // Use String.format instead of STR.
        String result = String.format("Data from %s", apiName);
        // Use String.format instead of STR.
        System.out.println(String.format("Finished API call: %s with result: '%s'", apiName, result));
        return result;
    }


    private static String apiCall1() {
            try {
                return fetchData("API-1 (User Data)", 1000, false); // 1 second delay, success
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                System.err.println("API-1 interrupted.");
                // Wrap checked exception if needed, or rethrow RuntimeException
                throw new RuntimeException(e);
            }
    }

    private static String apiCall2() {
            try {
                return fetchData("API-2 (Product Info)", 1500, false); // 1.5 seconds delay, success
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("API-2 interrupted.");
                throw new RuntimeException(e);
            }
    }

    private static String apiCall3Failing() {
            try {
                // Set shouldFail to true to simulate failure
                return fetchData("API-3 (Order Status)", 500, true); // 0.5 second delay, failure
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("API-3 interrupted.");
                throw new RuntimeException(e);
            }
    }

    private static String apiCall4() {
            try {
                return fetchData("API-4 (Inventory Check)", 2000, false); // 2 seconds delay, success
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("API-4 interrupted.");
                throw new RuntimeException(e);
            }
    }

    private static void doNothing() {
        try {
            fetchData("API-5 doNothing", 2000, false); // 2 seconds delay, success
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        System.out.println("Starting parallel API calls...");
        Instant start = Instant.now();

        try (var scope = new ShutdownOnFailure()) {

            // Fork initiates each task (Callable) in its own new virtual thread
            Supplier<String> future1 = scope.fork(ParallelApiCaller::apiCall1);
            Supplier<String> future2 = scope.fork(ParallelApiCaller::apiCall2);
            //Subtask<String> future3 = scope.fork(apiCall3Failing()); // Uncomment to test failure scenario
            Supplier<String> future4 = scope.fork(ParallelApiCaller::apiCall4);

//            for(int i=0;i<15;i++)
//                scope.fork(() -> {
//                    doNothing();
//                    return null;
//                });

            // Wait for all tasks to complete or for the scope to shut down (due to failure or interruption)
            scope.join().throwIfFailed();

            String result1 = future1.get();
            String result2 = future2.get();
            // String result3 = future3.resultNow(); // Uncomment if using apiCall3Failing
            String result4 = future4.get();


            System.out.println("\n--- All API calls successful ---");
            // Use String.format instead of STR.
            System.out.println(String.format("Result 1: %s", result1));
            System.out.println(String.format("Result 2: %s", result2));
            // System.out.println(String.format("Result 3: %s", result3)); // Uncomment if using apiCall3Failing
            System.out.println(String.format("Result 4: %s", result4));

        } catch (ExecutionException e) {
            // This block catches the exception wrapped by throwIfFailed()
            System.err.println("\n--- An API call failed ---");
            // Use String.format instead of STR.
            System.err.printf("Error: %s%n", e.getCause().getMessage()); // Get the original exception
            // When one task fails with ShutdownOnFailure, other running tasks are cancelled (interrupted).
            // You might see "interrupted" messages from the other tasks.
        } catch (InterruptedException e) {
            // This block catches interruption of the main thread while waiting in join()
            System.err.println("\n--- Main thread interrupted while waiting for API calls ---");
            Thread.currentThread().interrupt(); // Re-interrupt the thread
        } finally {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.printf("\nTotal execution time: %d ms%n", timeElapsed.toMillis());

        }
    }
}
