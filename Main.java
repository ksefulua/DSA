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

    }

}