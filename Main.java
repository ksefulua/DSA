import java.io.IOException;

import org.apache.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import java.io.File;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);
    private static int[] numbers = new Generator(25000000).generateNumbers();

    private  void outputTest(OutputFactory outputFactory, String test, int k, int N) {
        try {
            SpeedTest speedTest = new SpeedTest(numbers);
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
            SpeedTest speedTest = new SpeedTest(new int[1]);
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

    public void sortTest(int n, int M , int d , String file, InputFactory i, OutputFactory o, String testName ){
        try {
            StopWatch stopWatch = new Log4JStopWatch(testName + "_N-" + n + "_M-" + M + "_D-" + d);
            EMWMS sorter = new EMWMS(M,d,file, i, o);
            stopWatch.stop();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args)  throws IOException{
        Main obj = new Main();
        InputFactory i1 = new Input1Factory();
        InputFactory i2 = new Input2Factory();
        InputFactory i3 = new Input3Factory(2000000);
        InputFactory i4 = new Input4Factory(2000000);

        OutputFactory o1 = new Output1Factory();
        OutputFactory o2 = new Output2Factory();
        OutputFactory o3 = new Output3Factory(2000000);
        OutputFactory o4 = new Output4Factory(2000000);


        int K = 30;
        int N = 25000000;
        int averageOver = 1;
        int maxBufferSize = 2000000;
        int minBufferSize = 2000;
        int stepBufferSize = 10;

        new Generator(N).generateFiles(K);

        for(int i = 1 ; i < K ; i += 1){
            obj.outputTest(o1, "Output1", i, N);
            obj.deleteOutputfiles(K);
        }

        for(int i = 1 ; i < K ; i += 1){
            obj.outputTest(o2, "Output2", i, N);
            obj.deleteOutputfiles(K);
        }

        for(int i = 1 ; i < K ; i += 1){
            obj.outputTest(o3, "Output3", i, N);
            obj.deleteOutputfiles(K);
        }


        for(int i = 1 ; i < K ; i += 1){
            obj.outputTest(o4, "Output4", i, N);
            obj.deleteOutputfiles(K);
        }

        for(int i = 1 ; i < K ; i += 1){
            obj.inputTest(i1, "Input1", i);
        }

        for(int i = 1 ; i < K ; i += 1){
            obj.inputTest(i2, "Input2", i);
        }

        for(int i = 1 ; i < K ; i += 1){
            obj.inputTest(i3, "Input3", i);
        }

        for(int i = 1 ; i < K ; i += 1){
            obj.inputTest(i4, "Input4", i);
        }


        for(int Number = N ; Number <= 500000000; Number *= 10  ) {
            new Generator(Number).generateFiles(K);
            for (int M = 50000000; M <= 50000000; M *= 10) {
                for (int d = 10; d <= 1000000; d*= 10){
                    obj.sortTest(Number,M,d,"testFiles/testfile_0",i4, o4,"EMWMS");
                }
            }
            File file = new File("testFiles/testfile_0");
            file.delete();
        }

    }

}