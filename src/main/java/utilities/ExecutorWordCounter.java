package utilities;

import java.util.*;
import java.util.concurrent.*;

public class ExecutorWordCounter {

    private final countingWords counter = new countingWords();

    static final HashMap<String,Integer> sharedMap = new HashMap<>();

    public Map<String,Integer> useExecutor(List<String> lines) throws Exception {
        // Split into 2 halves
        List<String> part1 = lines.subList(0, lines.size() / 2);
        List<String> part2 = lines.subList(lines.size() / 2, lines.size());

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit tasks (lambdas calling sequential counter)
        Future<Map<String,Integer>> f1 = executor.submit(() -> counter.countWords(part1));
        Future<Map<String,Integer>> f2 = executor.submit(() -> counter.countWords(part2));

        // Wait for results
        Map<String,Integer> result1 = f1.get();
        Map<String,Integer> result2 = f2.get();

        executor.shutdown();

        // Merge results
        Map<String,Integer> finalMap = new HashMap<>(result1);
        for (var entry : result2.entrySet()) {
            finalMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }

        return finalMap;
    }
}
