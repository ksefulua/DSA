import java.io.IOException;

import org.apache.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import java.io.File;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    private  void outputTest(OutputFactory outputFactory, String test, int k, int N) {
        try {
            SpeedTest speedTest = new SpeedTest();
            StopWatch stopWatch = new Log4JStopWatch(test + " " + k);
            speedTest.speedTestOutput(N, k, outputFactory);
            stopWatch.stop();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void inputTest(InputFactory inputFactory, String test, int k) {
        try {
            SpeedTest speedTest = new SpeedTest();
            StopWatch stopWatch = new Log4JStopWatch(test + " " + k);
            speedTest.speedTestInPut(k, inputFactory);
            stopWatch.stop();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOutputfiles(int k){
        for(int i = 0 ; i < k ; i++ ){
            File file = new File("output\\output_" + i);
            file.delete();
        }
    }

    public static void main(String[] args)  throws IOException{
        Main obj = new Main();
        OutputFactory o1 = new Output1Factory();
        OutputFactory o2 = new Output2Factory();
        OutputFactory o3 = new Output3Factory(10000);
        OutputFactory o4 = new Output4Factory(1000);

        InputFactory i1 = new Input1Factory();
        InputFactory i2 = new Input2Factory();
        InputFactory i3 = new Input3Factory(10000);
        InputFactory i4 = new Input4Factory(1000);

        int K = 5;
        int N = 25000000;
        int averageOver = 1;
        int maxBufferSize = 20000000;
        int minBufferSize = 20;
        int stepBufferSize = 10;

        /*
        for(int j = 1 ; j <= K ; j++ ) {
            for(int i = 0 ; i < averageOver ; i++ ) {
                obj.outputTest(o1, "Output 1", j, N);
                obj.outputTest(o2, "Output 2", j, N);
                obj.outputTest(o3, "Output 3", j, N);
                obj.outputTest(o4, "Output 4", j, N);
            }
        }

        Generator gen = new Generator(N);
        gen.generateFiles();



        for(int j = 1 ; j <= K ; j++ ) {
            for(int i = 0 ; i < averageOver ; i++ ) {
                obj.inputTest(i1, "Input 1", j);
                obj.inputTest(i2, "Input 2", j);
                obj.inputTest(i3, "Input 3", j);
                obj.inputTest(i4, "Input 4", j);
            }
        }


        for(int bufferSize = minBufferSize ; bufferSize <= maxBufferSize ; bufferSize *= stepBufferSize){
            for(int i = 0 ; i < averageOver ; i++ ){
                i4 = new Input4Factory(bufferSize);
                obj.inputTest(i4, "Input 4 B_"+ bufferSize, K);
            }
        }
*/
        for(int bufferSize = minBufferSize*100000; bufferSize <= maxBufferSize ; bufferSize *= stepBufferSize){
            for(int i = 0 ; i < averageOver ; i++ ){
                o4 = new Output4Factory(bufferSize);
                obj.outputTest(o4, "Output 4 B_"+ bufferSize, K, N);
                obj.deleteOutputfiles(K);
            }
        }
       /*
        for(int bufferSize = minBufferSize ; bufferSize <= maxBufferSize ; bufferSize *= stepBufferSize){
            for(int i = 0 ; i < averageOver ; i++ ){
                i3 = new Input3Factory(bufferSize);
                obj.inputTest(i3, "Input 3 B_"+ bufferSize , K);
            }
        }

        for(int bufferSize = minBufferSize; bufferSize <= maxBufferSize ; bufferSize *= stepBufferSize){
            for(int i = 0 ; i < averageOver ; i++ ){
                o3 = new Output3Factory(bufferSize);
                obj.outputTest(o3, "Output 3 B_"+ bufferSize , K, N);
                obj.deleteOutputfiles(K);

            }
        }
        */
    }

}