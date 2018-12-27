import java.io.*;
import java.nio.IntBuffer;

public class Output3 implements Output {
    private OutputStream os;
    private DataOutputStream dos;
    private IntBuffer buffer;
    private static int BUFFERSIZE = Integer.BYTES * 100;

    public Output3(File file, int B)  throws IOException{
        os = new FileOutputStream (file);
        dos = new DataOutputStream(os);
        buffer = IntBuffer.allocate(B);
    }

    @Override
    public void write(int integer) throws IOException {
        if (buffer.capacity() == buffer.position())
            flushBuffer();
        buffer.put(integer);
    }

    private void flushBuffer() throws IOException{
        buffer.flip();
        while (buffer.hasRemaining()) {
            dos.write(buffer.get());
        }
        buffer.clear();
    }

    @Override
    public void close()  throws IOException{
        flushBuffer();
        os.close();
        dos.close();
    }
}
