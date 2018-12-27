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

            Generator generator = new Generator(100);
            Output o1 = new Output1();
            o1.create("hello.txt");
            for (int i = 0 ; i < 1000 ; i++){
                o1.write(i);
            }
            /*
            Writer a = new Writer(new Generator(200), "1.txt");
            a.writeOnfile_2();

            Input i1 = new Input1();
            i1.open("1.txt");

            Output o1 = new Output1();
            /*
            while (!i1.endOfStream()){
                System.out.println(i1.readNext());
            }

            EMWMS b = new EMWMS(8,5,"1.txt", i1, o1);
            */
            Input inputres = new Input4();
            inputres.open("hello.txt");
            while (!inputres.endOfStream()){
                System.out.println(inputres.readNext());
            }



        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
