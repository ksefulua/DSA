import java.nio.IntBuffer;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Input3 extends Input {
    IntBuffer buffer;
    int i;
    int bufferUse;
    Input1 input;

    public Input3(File fileName, int B) throws IOException {
        input = new Input1(fileName);
        buffer = IntBuffer.allocate(B);
        fillBuffer();
    }

    private void fillBuffer() throws IOException{
        bufferUse = 0;
        i = 0;
        buffer.clear();
        while(input.hasNext() && bufferUse < buffer.capacity()  ) {
            buffer.put(input.getNext());
            ++bufferUse;
        }
    }

    @Override
    public int getNext() throws IOException {
        current = buffer.get(i);
        ++i;
        if(!hasNext()) {
            fillBuffer();
        }
        return current;
    }

    @Override
    public boolean hasNext() {
        return i < bufferUse;
    }

    @Override
    public void close() throws IOException{
        input.close();
    }

}
