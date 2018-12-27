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

            Generator gen = new Generator(20000);
            OutputFactory outputFactory = new Output4Factory(100);
            InputFactory inputFactory = new Input4Factory(100);
            Output o = outputFactory.getFreshOutputStream("1.txt");
            for (int i : gen.generateNumbers()) {
                o.write(i);
            }
            o.close();
            EMWMS b = new EMWMS(100, 30, "1.txt", inputFactory, outputFactory);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
