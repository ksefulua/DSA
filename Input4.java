import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Input4 extends Input {
    private final int INTSIZE = 4;
    MappedByteBuffer mappedRegion;
    FileChannel fileChannel;
    int i;
    int allreadyMaped;
    int B;
    long totalSize;

    public Input4(File file, int B) throws IOException {
        this.B = B;
        allreadyMaped = 0;
        i=0;
        Path path = file.toPath();
        totalSize = file.length();
        fileChannel = FileChannel.open(path, StandardOpenOption.READ);
        mapping();
    }

    public void  mapping() throws IOException{
        mappedRegion = fileChannel.map(FileChannel.MapMode.READ_ONLY, allreadyMaped, Math.max((long)B*INTSIZE,totalSize));
        allreadyMaped += mappedRegion.capacity();
    }

    @Override
    public int getNext() throws IOException{
        i += 4;
        current = mappedRegion.getInt();
        if(i>= allreadyMaped && hasNext()) {
            mapping();
        }
        return current;
    }

    @Override
    public boolean hasNext() {
        return i < totalSize;
    }

    @Override
    public void close() {
    }

}
