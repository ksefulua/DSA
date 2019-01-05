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
    void deleteDirectoryRecursionJava6(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursionJava6(entry);
                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    public static void main(String[] args)  throws IOException{
        Main obj = new Main();
        OutputFactory o3 = new Output4Factory(2000000);
        InputFactory i3 = new Input4Factory(2000000);

        int K = 30;
        int N = 25000000;
        int averageOver = 1;
        int maxBufferSize = 2000000;
        int minBufferSize = 2000;
        int stepBufferSize = 10;

        for(int i = 1 ; i < K ; i += 1){
            o3 = new Output3Factory(200000);
            obj.outputTest(o3, "Output3 ", i, N);
            obj.deleteOutputfiles(K);
        }

        for(int i = 1 ; i < K ; i += 1){
                i3 = new Input3Factory(200000);
                obj.inputTest(i3, "Input3 ", i);
        }


/*


        for(int N = 250000000 ; N <= 500000000; N *= 10  ) {
            new Generator(N).generateFiles();
            for (int M = 50000000; M <= 50000000; M *= 10) {
                for (int d = 10; d <= 1000000; d*= 10){
                    obj.sortTest(N,M,d,"testFiles\\testfile_0",i4, o4,"EMWMS");
                    //obj.deleteDirectoryRecursionJava6(new File("storage"));
                }
            }
            File file = new File("testFiles\\testfile_0");
            file.delete();
        }


        /*
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