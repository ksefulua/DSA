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
    public void write(int integer) {
        try {
            dos.writeInt(integer);
            dos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            dos.close();
            bos.close();
            os.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
