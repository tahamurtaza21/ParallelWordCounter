package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import static utilities.ExecutorWordCounter.sharedMap;

public class countingWords {

    public HashMap<String,Integer> countWords(List<String> lines) throws IOException {
//        HashMap<String,Integer> wordCount = new HashMap<>();
        for(String line : lines)    // gets each line
        {
            String[] words = line.split("\\W+");  // splits it into words
            for(String word : words){
                if(!word.isEmpty())
                {
                    word = word.toLowerCase(); // normalizes it
                    sharedMap.put(word,sharedMap.getOrDefault(word,0)+1); //
                }
            }
        }
        return sharedMap;
    }


}
