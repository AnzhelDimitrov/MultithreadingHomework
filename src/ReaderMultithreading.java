import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReaderMultithreading {

    public static void fileReader2() throws IOException {
        HashMap<String, Integer> duplicateWords = new HashMap<>();
        Lock lock = new ReentrantLock();
        List<Thread> threads = new ArrayList<>();

        try {
            File fileFolder = new File("D:\\JavaHomework\\MultithreadingAdv\\src\\resources");
            File[] files = fileFolder.listFiles();

            assert files != null;
            for (File file : files) {
                Runnable task = () -> {
                    try (BufferedReader buffered = new BufferedReader(new FileReader(file))) {
                        String line;

                        while ((line = buffered.readLine()) != null) {
                            List<String> wordsList = Arrays.asList(line.split("[\\s.,;]+"));
                            lock.lock();
                            try {
                                for (String word : wordsList) {
                                    duplicateWords.put(word, duplicateWords.getOrDefault(word, 0) + 1);
                                }
                            } finally {
                                lock.unlock();
                            }
                        }
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                };

                Thread thread = new Thread(task);
                threads.add(thread);
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

        } catch (Exception e) {
            e.getStackTrace();
        }

        for (String word : duplicateWords.keySet()) {
            int count = duplicateWords.get(word);
            System.out.println(word + ": " + count);
        }
    }
}
