import java.io.*;
import java.nio.IntBuffer;

public class Output3 implements Output {
    private OutputStream os;
    private BufferedOutputStream bos;
    private DataOutputStream dos;
    private IntBuffer buffer;
    private static int BUFFERSIZE = Integer.BYTES*5;

    @Override
    public void create(String fileName){
        try {
            os = new FileOutputStream(new File(fileName));
            bos = new BufferedOutputStream( os );
            dos = new DataOutputStream(bos);
            buffer = IntBuffer.allocate(BUFFERSIZE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(int integer){
        if (buffer.capacity() == buffer.position()) flushBuffer();
        buffer.put(integer);
    }

    private void flushBuffer() {
        buffer.flip();
        while (buffer.hasRemaining()) {
            try {
                dos.writeInt(buffer.get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        buffer.clear();
    }

        @Override
    public void close(){
        try {
            flushBuffer();
            dos.close();
            bos.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
