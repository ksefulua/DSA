import java.util.*;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeedTest {


    public void speedTestInPut(int N, int K, InputFactory iFactory) {
        ExecutorService ex = Executors.newFixedThreadPool(K);
        for( int e = 0; e <= K; e++) {
            Runnable inputThread = new InputRunnable(e, iFactory);
            ex.execute(inputThread);
        }
        while (!ex.isTerminated()){};
        System.out.println("Done");
    }

    public static class InputRunnable implements Runnable {
        private final int i;
        private final InputFactory iFactory;

        InputRunnable(int i, InputFactory iFactory){
            this.i = i;
            this.iFactory = iFactory;
        }
        @Override
        public void run(){
            try {
                Input input = iFactory.getFreshInputStream("testFiles/testfile_" + i);
                while (!input.endOfStream()){
                    input.readNext();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void speedTestOutput(int N, int k, OutputFactory oFactory) throws IOException {
        Generator gen = new Generator(N);
        List<Output> streamToTest = new LinkedList<Output>();
        for(int i = 0 ; i < k ; i++ ) {
            streamToTest.add(oFactory.getFreshOutputStream("output/output_" + i));
        }
        ListIterator<Output> it = streamToTest.listIterator();
        int randomNumbers[] = gen.generateNumbers();
        for(int j = 0 ; j < k ; j++ ) {
            Output test = it.next();
            for(int i = 0 ; i < N ; i++ ) {
                test.write(randomNumbers[i]);
            }
        }
    }

}
