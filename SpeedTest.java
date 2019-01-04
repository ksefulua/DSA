import java.util.*;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeedTest {
    private static int[] numbers;

    public SpeedTest(int[] numbers){
        this.numbers = numbers;
    }

    public void speedTestInPut(int K, InputFactory iFactory) {
        Thread[] threads = new Thread[30];
        for( int e = 0; e < K; e++) {
            threads[e] = new Thread(new InputRunnable(e, iFactory));
            threads[e].start();
        }
        try {
            for(int i = 0; i < K ; i++){
                threads[i].join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

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

    public void speedTestOutput(int N, int K, OutputFactory oFactory) throws IOException {
        Thread[] threads = new Thread[30];
        for( int e = 0; e < K; e++) {
            threads[e] = new Thread(new OutputRunnable(e, oFactory));
            threads[e].start();
        }
        try {
            for(int i = 0; i < K ; i++){
                threads[i].join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
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
