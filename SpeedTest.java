import java.util.*;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeedTest {
    private static int[] numbers;

    public void speedTestInPut(int K, InputFactory iFactory) {
        ExecutorService ex = Executors.newFixedThreadPool(K);
        for( int e = 0; e < K; e++) {
            Runnable inputThread = new InputRunnable(e, iFactory);
            ex.execute(inputThread);
        }
        ex.shutdown();
        while (!ex.isTerminated()) {}
    }

    public static class InputRunnable implements Runnable {
        private final int i;
        private final InputFactory iFactory;

        InputRunnable(int i, InputFactory iFactory) {
            this.i = i;
            this.iFactory = iFactory;
        }
        @Override
        public void run() {
            try {
                Input input = iFactory.getFreshInputStream("testFiles\\testfile_" + i);
                while (!input.endOfStream()) {
                    input.readNext();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void speedTestOutput(int N, int k, OutputFactory oFactory) throws IOException {
        Generator gen = new Generator(N);
        numbers = gen.generateNumbers();

        ExecutorService ex = Executors.newFixedThreadPool(k);
        for( int e = 0; e < k; e++) {
            Runnable outputThread = new OutputRunnable(e, oFactory);
            ex.execute(outputThread);
        }
        ex.shutdown();
        while (!ex.isTerminated()) {}
    }

    public static class OutputRunnable implements Runnable {
        private final int i;
        private final OutputFactory iFactory;

        OutputRunnable(int i, OutputFactory iFactory) {
            this.i = i;
            this.iFactory = iFactory;
        }
        @Override
        public void run() {
            try {
                Output output = iFactory.getFreshOutputStream("output\\output_" + i);
                for (int i : numbers) {
                    output.write(i);
                }
                output.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
