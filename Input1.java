import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Input1 extends Input {
    private InputStream is;
    private DataInputStream ds;

    public Input1(File file) throws IOException, FileNotFoundException {
        is = new FileInputStream(file);
        ds  = new DataInputStream(is);
    }

    @Override
    public int readNext() {
        try {
            current = ds.readInt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return current;
    }

    @Override
    public boolean endOfStream() {
        try {
            return !(ds.available() > 0);
        }
        catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }


}
