import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File;

public class Output1 implements Output {
    OutputStream os;
    DataOutputStream dos;

    public Output1(File file) throws IOException{
        os = new FileOutputStream (file);
        dos = new DataOutputStream(os);
    }

    @Override
    public void writeInt(int toWrite) throws IOException{
        dos.writeInt(toWrite);
    }

    @Override
    public void close() throws IOException{
        dos.close();
        os.close();
    }
}
