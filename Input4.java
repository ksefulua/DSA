import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.Path;

public class Input4 extends Input {
    private static final int INTSIZE = 4;
    private MappedByteBuffer mappedRegion;
    private FileChannel fileChannel;
    private int i;
    private int allreadyMaped;
    private static int B = 1000;
    private long totalSize;


    @Override public void open(String filename){
        allreadyMaped = 0;
        i=0;
        try {
            File file = new File(filename);
            Path path = file.toPath();
            totalSize = file.length();
            fileChannel = FileChannel.open(path, StandardOpenOption.READ);
            mapping();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void  mapping() throws IOException{
        mappedRegion = fileChannel.map(FileChannel.MapMode.READ_ONLY, allreadyMaped, Math.max((long)B*INTSIZE,totalSize));
        allreadyMaped += mappedRegion.capacity();
    }

    @Override
    public int readNext() throws IOException{
        i += 4;
        current = mappedRegion.getInt();
        if(i>= allreadyMaped && !endOfStream()) {
            mapping();
        }
        return current;
    }

    @Override
    public boolean endOfStream() {
        return !(i < totalSize);
    }

}
