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

            Writer a = new Writer(new Generator(50), "1.txt");
            a.writeOnfile_2();

            EMWMS b = new EMWMS(8,5,new File("1.txt"));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
