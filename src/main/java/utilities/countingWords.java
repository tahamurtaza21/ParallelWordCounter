package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class countingWords {

    public ConcurrentHashMap<String,LongAdder> countWords(List<String> lines, ConcurrentHashMap<String,LongAdder> sharedMap) throws IOException {

        // uses the same shared map that executor word counter provided

        for(String line : lines)    // gets each line
        {
            String[] words = line.split("\\W+");  // splits it into words
            for(String word : words){
                if(!word.isEmpty())
                {
                    word = word.toLowerCase(); // normalizes it
                    sharedMap.computeIfAbsent(word, w -> new LongAdder()).increment();
                }
            }
        }
        return sharedMap;
    }


}
