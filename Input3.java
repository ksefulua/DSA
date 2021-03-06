import java.nio.IntBuffer;
import java.io.IOException;
import java.io.File;

public class Input3 extends Input {
    private IntBuffer buffer;
    private int i;
    private int bufferUse;
    private Input1 input;

    public Input3(File file, int B) throws IOException{
        input = new Input1(file);
        buffer = IntBuffer.allocate(B*Integer.BYTES);
        fillBuffer();
    }

    private void fillBuffer() throws IOException{
        bufferUse = 0;
        i = 0;
        buffer.clear();
        while (!input.endOfStream() && bufferUse < buffer.capacity()) {
            buffer.put(input.readNext());
            ++bufferUse;
        }
    }

    @Override
    public int readNext() throws IOException {
        current = buffer.get(i);
        ++i;
        if(endOfStream()) {
            fillBuffer();
        }
        return current;
    }

    @Override
    public boolean endOfStream() {
        return !(i < bufferUse);
    }

    @Override
    public void close() throws IOException{
        input.close();
    }

}
