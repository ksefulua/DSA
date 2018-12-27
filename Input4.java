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
    private static int BUFF_SIZE = 59 * INTSIZE;
    private long mappedPortionSize;
    private long totalSize;


    @Override public void open(String fileName){
        try {
            ifc = new RandomAccessFile(new File(fileName), "r").getChannel();
            totalSize = ifc.size();
            mappedPortionSize = Math.min((long) BUFF_SIZE ,totalSize);
            mappedRegion = ifc.map(FileChannel.MapMode.READ_ONLY, n, mappedPortionSize);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void mapNextFileChunk(){
        mappedPortionSize = Math.max(BUFF_SIZE, totalSize - n - i);
        n += i;
        i = 0;
        mappedRegion.clear();
        try {
            mappedRegion = ifc.map(FileChannel.MapMode.READ_ONLY, n, mappedPortionSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int readNext(){
        if (filePortionIsRead()){
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

    private boolean filePortionIsRead(){
        return (mappedRegion.position() == mappedRegion.capacity());
    }

}
