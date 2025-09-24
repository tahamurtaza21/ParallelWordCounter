package multithreading.parallelwordcounter;

import utilities.countingWordsParallel;
import utilities.countingWordsSequentially;
import utilities.readFiles;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootApplication
public class ParallelWordCOunterApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(ParallelWordCOunterApplication.class, args);

        // Doing this sequentially

//        List<String> lines = null;

//        try {
//            countingWordsSequentially counter = new countingWordsSequentially();
//            lines = counter.readLinesFromFile("src/main/resources/bigfile.txt");
//            System.out.println(counter.countWords(lines));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }


        List<String> lines = null;

        lines = readFiles.readLinesFromFile("src/main/resources/bigfile.txt");

        try {
            List<String> part1 = lines.subList(0, lines.size() / 2);
            List<String> part2 = lines.subList(lines.size() / 2, lines.size());

            countingWordsParallel counter1 = new countingWordsParallel(part1);
            countingWordsParallel counter2 = new countingWordsParallel(part2);

            Thread t1 = new Thread(counter1);
            Thread t2 = new Thread(counter2);

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            Map<String, Integer> finalMap = new HashMap<>(counter1.getResult());
            finalMap.putAll(counter2.getResult());

            System.out.println(finalMap);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
