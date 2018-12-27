import java.util.*;

public class SpeedTest {


    public void speedTestIntPut(int N, int k, InputFactory iFactory) {
        List<Input> streamToTest = new LinkedList<Input>();
        for(int i = 0 ; i < k ; i++ ) {
            streamToTest.add(iFactory.getFreshInputStream("a"));
        }
        ListIterator<Input> it = streamToTest.listIterator();
        for(int j = 0 ; j < k ; j++ ) {
            Input test = it.next();
            for(int i = 0 ; i < N ; i++ ) {
                test.readNext();
            }
        }
    }

    public void speedTestOutput(int N, int k, OutputFactory oFactory,Generator gen) {
        List<Output> streamToTest = new LinkedList<Output>();
        for(int i = 0 ; i < k ; i++ ) {
            streamToTest.add(oFactory.getFreshOutputStream("a"));
        }
        ListIterator<Output> it = streamToTest.listIterator();
        for(int j = 0 ; j < k ; j++ ) {
            Output test = it.next();
            for(int i = 0 ; i < N ; i++ ) {
                test.write(gen.generate());
            }
        }
    }

}
