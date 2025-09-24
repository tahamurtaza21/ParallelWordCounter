package utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class countingWordsParallel implements Runnable {

    private List<String> lines;
    private Map<String,Integer> localMap = new HashMap<>();

    public countingWordsParallel(List<String> lines){
        this.lines = lines;
    }

    @Override
    public void run() {
        for(String line : lines) {
            String[] words = line.split("\\W+");
            for(String word : words) {
                if(!word.isEmpty()) {
                    word = word.toLowerCase();
                    localMap.put(word, localMap.getOrDefault(word, 0) + 1);
                }
            }
        }
    }

    public Map<String, Integer> getResult() {
        return localMap;
    }
}
