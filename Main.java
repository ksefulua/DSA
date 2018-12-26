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

            Writer a = new Writer(new Generator(1000), "1.txt");
            a.writeOnfile_2();

            Input i1 = new Input1();
            i1.open("1.txt");
            /*
            while (!i1.endOfStream()){
                System.out.println(i1.readNext());
            }
            */

            EMWMS b = new EMWMS(8,5,"1.txt", i1);

            Input inputres = new Input1();
            inputres.open("temp156.txt");
            while (!inputres.endOfStream()){
                System.out.println(inputres.readNext());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
