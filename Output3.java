import java.io.*;
import java.nio.IntBuffer;

public class Output3 implements Output {
    private Output1 out;
    private IntBuffer buffer;
    private static int BUFFERSIZE = Integer.BYTES * 100;

    public Output3(File file, int B)  throws IOException{
        out = new Output1(file);
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
            out.write(buffer.get());
        }
        buffer.clear();
    }

    @Override
    public void close()  throws IOException{
        flushBuffer();
        out.close();
    }
}
