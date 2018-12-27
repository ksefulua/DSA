import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.file.Path;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.StandardOpenOption;

public class Main {

    public static void main(String[] args) {
        try {

            Generator gen = new Generator(100);
            OutputFactory outputFactory = new Output4Factory(2);
            InputFactory inputFactory = new Input4Factory(1000);
            /*
            Output o = outputFactory.getFreshOutputStream("hello.txt");
            for (int i = 0 ; i < 1000 ; i++) {
                o.write(i);
            }
            o.close();
            */

            Output o = outputFactory.getFreshOutputStream("1.txt");
            int randomNumber[] = {45, 781, 12, 111, 43, 54, 90};
            for(int i : randomNumber) {
                o.write(i);
            }
            o.close();

            EMWMS b = new EMWMS(100, 2, "1.txt", inputFactory, outputFactory);

            /*
            Input i = inputFactory.getFreshInputStream("hello.txt");
            while (!i.endOfStream()) {
                System.out.print(i.readNext() + " ");
            }
            */

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
