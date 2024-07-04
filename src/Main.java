import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTimeSingleThread = System.nanoTime();
        Reader.fileReader();
        long endTimeSingleThread = System.nanoTime();
        long durationSingleThread = (endTimeSingleThread - startTimeSingleThread) / 1_000_000; // in milliseconds

        long startTimeMultiThread = System.nanoTime();
        ReaderMultithreading.fileReader2();
        long endTimeMultiThread = System.nanoTime();
        long durationMultiThread = (endTimeMultiThread - startTimeMultiThread) / 1_000_000; // in milliseconds

        System.out.println("Single Thread Execution Time: " + durationSingleThread + " ms");
        System.out.println("Multi Thread Execution Time: " + durationMultiThread + " ms");
    }
}