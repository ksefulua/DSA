import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File;

public class Output1 implements Output {

    private OutputStream os;
    private DataOutputStream dos;

    public Output1(File file) throws IOException{
        os = new FileOutputStream (file);
        dos = new DataOutputStream(os);
    }

    @Override
    public void write(int integer) throws IOException {
        dos.writeInt(integer);
    }

    @Override
    public void close() throws IOException{
        dos.close();
        os.close();
    }
}
