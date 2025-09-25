package multithreading.parallelwordcounter;

import utilities.ExecutorWordCounter;
import utilities.countingWords;
import utilities.readFiles;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootApplication
public class ParallelWordCounterApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(ParallelWordCOunterApplication.class, args);


        List<String> lines = readFiles.readLinesFromFile("src/main/resources/random_big_file.txt");

        // Doing this sequentially

//        List<String> lines = null;

//        try {
//            countingWords counter = new countingWords();
//            System.out.println(counter.countWords(lines));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }


//        // Reading it in parallel (raw threads version)
//        try {
//            List<String> lines = readFiles.readLinesFromFile("src/main/resources/bigfile.txt");
//
//            // Split work
//            List<String> part1 = lines.subList(0, lines.size() / 2);
//            List<String> part2 = lines.subList(lines.size() / 2, lines.size());
//
//            countingWords counter = new countingWords();
//
//            // holders for results
//            final Map<String, Integer>[] result1 = new Map[1];
//            final Map<String, Integer>[] result2 = new Map[1];
//
//            // thread 1
//            Thread t1 = new Thread(() -> {
//                result1[0] = counter.countWords(part1);
//            });
//
//            // thread 2
//            Thread t2 = new Thread(() -> {
//                result2[0] = counter.countWords(part2);
//            });
//
//            // start threads
//            t1.start();
//            t2.start();
//
//            // wait for them
//            t1.join();
//            t2.join();
//
//            // merge results
//            Map<String, Integer> finalMap = new HashMap<>(result1[0]);
//            for (var entry : result2[0].entrySet()) {
//                finalMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
//            }
//
//            System.out.println(finalMap);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
//             Normal Map
            ExecutorWordCounter executorCounter = new ExecutorWordCounter();
            Map<String,Integer> result = executorCounter.useExecutor(lines);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
