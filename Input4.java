import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.io.IOException;

public class Input4 extends Input {
    private static final int INTSIZE = Integer.BYTES;
    private MappedByteBuffer mappedRegion;
    private FileChannel ifc;
    private int i = 0;
    private int n = 0;
    private final int BUFF_SIZE;
    private long mappedPortionSize;
    private long totalSize;


    public Input4(File file, int B) throws IOException {
        BUFF_SIZE = B * INTSIZE;
        ifc = new RandomAccessFile(file, "r").getChannel();
        totalSize = ifc.size();
        if (BUFF_SIZE >= totalSize) {
            mappedPortionSize = (int) totalSize;
        } else {
            mappedPortionSize = BUFF_SIZE;
        }
        mappedRegion = ifc.map(FileChannel.MapMode.READ_ONLY, n, mappedPortionSize);
    }

    private void mapNextFileChunk() throws IOException {
        if (totalSize - n - i > BUFF_SIZE) {
            mappedPortionSize = BUFF_SIZE;
        } else {
            mappedPortionSize = (int) (totalSize - n - i);
        }
        n += i;
        i = 0;
        mappedRegion.clear();
        mappedRegion = ifc.map(FileChannel.MapMode.READ_ONLY, n, mappedPortionSize);
    }

    @Override
    public int readNext() throws IOException {
        if (filePortionIsRead()) {
            mapNextFileChunk();
        }
        current = mappedRegion.getInt();
        i += INTSIZE;
        return current;
    }

    @Override
    public boolean endOfStream() {
        return totalSize - n - i <= 0;
    }

    private boolean filePortionIsRead() {
        return (mappedRegion.position() == mappedRegion.capacity());
    }

    @Override
    public void close() throws IOException{
        ifc.close();
    }

}
