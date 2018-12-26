import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Output4 implements Output {
    private int i = 0;
    private int n = 0;
    private FileChannel ofc;
    private MappedByteBuffer mbb;
    private static final int INT_SIZE = Integer.BYTES;
    private static int BUFFERSIZE = INT_SIZE * 50;

    @Override public void create(String fileName){
        try {
            ofc = new RandomAccessFile(new File(fileName), "rw").getChannel();
            mbb = ofc.map(FileChannel.MapMode.READ_WRITE, 0, BUFFERSIZE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void write(int integer){
        if(isFull()){
            flushBuffer();
        }
        mbb.putInt(integer);
        i += INT_SIZE;
    }

    @Override
    public void close(){
        try {
            ofc.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void flushBuffer(){
        n += i;
        i = 0;
        try {
            mbb.clear();
            mbb = ofc.map(FileChannel.MapMode.READ_WRITE, n, BUFFERSIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFull(){
        return ((BUFFERSIZE - i) < INT_SIZE);
    }

}
