import java.io.IOException;
import java.util.*;
import java.util.Random;
import java.util.stream.IntStream;
import java.io.File;
//50 is the maximum and the 1 is our minimum
class Generator {

    private int _numberOfint;
    private Random _rand;
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final int MIN_VALUE = Integer.MIN_VALUE;


    public Generator (int numberOfint ) {
        _numberOfint = numberOfint;
        _rand = new Random();
    }

    public int[] generateNumbers () {
        return _rand.ints(_numberOfint, MIN_VALUE, MAX_VALUE).toArray();
    }

    public void generateFiles(int numberOfFiles) throws IOException {
        for(int i = 0; i < numberOfFiles; i++) {
            String filename = "testFiles/testfile_" + i;
            Output o = new Output4(new File(filename), 5000);
            int[] numbers = generateNumbers();
            for (int j : numbers) {
                o.write(j);
            }
        }
    }
}