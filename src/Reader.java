import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Reader {

    public static void fileReader() throws IOException {
        HashMap<String, Integer> duplicateWords = new HashMap<>();
        try {
            File fileFolder = new File("D:\\JavaHomework\\MultithreadingAdv\\src\\resources");
            File[] files = fileFolder.listFiles();
            ArrayList<String> wordsList = new ArrayList<>();

            assert files != null;
            for (File file : files) {
                BufferedReader buffered = new BufferedReader(new FileReader(file));
                String line;

                while ((line = buffered.readLine()) != null) {
                    wordsList.clear();
                    wordsList.addAll(Arrays.asList(line.split("[\\s.,;]+")));
                    for (String word : wordsList) {
                        if (duplicateWords.containsKey(word)) {
                            int value = duplicateWords.get(word);
                            duplicateWords.put(word, value + 1);
                        } else {
                            duplicateWords.put(word, 1);
                        }
                    }
                }
                buffered.close();
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
