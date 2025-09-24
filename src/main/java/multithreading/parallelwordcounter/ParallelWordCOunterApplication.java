package multithreading.parallelwordcounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilities.countingWords;

import java.util.List;

//@SpringBootApplication
public class ParallelWordCOunterApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ParallelWordCOunterApplication.class, args);

        List<String> lines = null;

        try {
            countingWords counter = new countingWords();
            lines = counter.readLinesFromFile("src/main/resources/bigfile.txt");
            System.out.println(counter.countWords(lines));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
