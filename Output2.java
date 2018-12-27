import java.io.*;

public class Output2 implements Output {
    private OutputStream os;
    private BufferedOutputStream bos;
    private DataOutputStream dos;

    public Output2(File file) throws IOException {
        os = new FileOutputStream(file);
        bos = new BufferedOutputStream( os );
        dos = new DataOutputStream(bos);
    }

    @Override
    public void write(int integer) throws IOException {
        dos.writeInt(integer);
        dos.flush();
    }

    @Override
    public void close()  throws IOException {
        dos.close();
        bos.close();
        os.close();
    }

}
