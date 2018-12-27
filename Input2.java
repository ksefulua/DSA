import java.io.DataInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Input2 extends Input {
    private InputStream is;
    private BufferedInputStream bis;
    private DataInputStream ds;

    public Input2(File file) throws IOException{
        is = new FileInputStream(file);
        bis = new BufferedInputStream(is);
        ds = new DataInputStream(bis);
    }

    @Override
    public int readNext() throws IOException {
        current = ds.readInt();
        return current;
    }

    @Override
    public boolean endOfStream() throws IOException{
        return !(ds.available() > 0);
    }

    @Override
    public void close() throws IOException{
        ds.close();
        bis.close();
        is.close();
    }

}
