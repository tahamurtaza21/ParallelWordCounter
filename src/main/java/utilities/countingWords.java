package utilities;

import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class countingWords {

    public List<String> readLinesFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path, StandardCharsets.UTF_16LE);
    }

    public HashMap<String,Integer> countWords(List<String> lines) throws IOException {
        HashMap<String,Integer> wordCount = new HashMap<>();
        for(String line : lines)    // gets each line
        {
            System.out.println(line);
            String[] words = line.split("\\W+");  // splits it into words
            for(String word : words){
                if(!word.isEmpty())
                {
                    word = word.toLowerCase(); // normalizes it
                    wordCount.put(word,wordCount.getOrDefault(word,0)+1); //
                }
            }
        }
        return wordCount;
    }


}
