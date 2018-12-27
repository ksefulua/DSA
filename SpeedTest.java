import java.util.*;
import java.io.IOException;

public class SpeedTest {


    public void speedTestInPut(int N, int K, InputFactory iFactory) throws IOException {
        for( int e = 1; e <= K; e++) {
            System.out.println(e);
            List<Input> streamToTest = new LinkedList<Input>();
            for(int i = 0 ; i < e ; i++ ) {
                streamToTest.add(iFactory.getFreshInputStream("testFiles/testfile_" + i));
            }
            ListIterator<Input> it = streamToTest.listIterator();
            for(int j = 0 ; j < e ; j++ ) {
                Input test = it.next();
                for(int i = 0 ; i < N ; i++ ) {
                    test.readNext();
                }
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
