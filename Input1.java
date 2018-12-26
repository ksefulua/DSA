import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Input1 extends Input {
    InputStream is;
    DataInputStream ds;

    public Input1(File file) throws IOException, FileNotFoundException {
        is = new FileInputStream(file);
        ds  = new DataInputStream(is);
    }

    @Override
    public int getNext() throws IOException {
        current = ds.readInt();
        return current;
    }

    @Override
    public boolean hasNext() throws IOException {
        return ds.available() > 0;
    }

    @Override
    public void close()throws IOException {
        ds.close();
        is.close();
    }
}
