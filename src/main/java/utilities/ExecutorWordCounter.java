package utilities;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class ExecutorWordCounter {

    public Map<String,Integer> useExecutor(List<String> lines) throws Exception {

        countingWords counter = new countingWords();
        ConcurrentHashMap<String, LongAdder> sharedMap = new ConcurrentHashMap<>();

        // Split into 2 halves
        List<String> part1 = lines.subList(0, lines.size() / 2);
        List<String> part2 = lines.subList(lines.size() / 2, lines.size());

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit tasks (each increments into the shared map)
        Future<ConcurrentHashMap<String,LongAdder>> f1 = executor.submit(() -> counter.countWords(part1, sharedMap));
        Future<ConcurrentHashMap<String,LongAdder>> f2 = executor.submit(() -> counter.countWords(part2, sharedMap));

        // Wait for completion
        f1.get();
        f2.get();

        executor.shutdown();

        // Convert LongAdder map → normal Map<String,Integer>
        Map<String,Integer> finalMap = new HashMap<>();
        sharedMap.forEach((k,v) -> finalMap.put(k, v.intValue()));

        return finalMap;
    }
}
