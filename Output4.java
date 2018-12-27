import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

public class Output4 implements Output {
    private int i = 0;
    private int n = 0;
    private FileChannel ofc;
    private MappedByteBuffer mbb;
    private static final int INT_SIZE = Integer.BYTES;
    private final int BUFFERSIZE;

    public Output4(File file, int B) throws IOException {
        BUFFERSIZE = B * INT_SIZE;
        ofc = new RandomAccessFile(file, "rw").getChannel();
        mbb = ofc.map(FileChannel.MapMode.READ_WRITE, n, BUFFERSIZE);
    }

    @Override
    public void write(int integer) throws IOException{
        if(isFull()) {
            flushBuffer();
        }
        mbb.putInt(integer);
        i += INT_SIZE;
    }

    @Override
    public void close() throws IOException{
        mbb.force();
        ofc.truncate(n + i);
        ofc.close();
    }

    private void flushBuffer() throws IOException{
        n += i;
        i = 0;
        mbb.clear();
        mbb = ofc.map(FileChannel.MapMode.READ_WRITE, n, BUFFERSIZE);
    }

    private boolean isFull() {
        return ((BUFFERSIZE - i) < INT_SIZE);
    }

}
