import java.util.*;
import java.util.Random;
import java.util.stream.IntStream;

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
}