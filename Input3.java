import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.nio.IntBuffer;
import java.io.IOException;
import java.io.File;

public class Input3 extends Input {
    private IntBuffer buffer;
    private int i;
    private int bufferUse;
    private static int bufferSize = 100 * Integer.BYTES;
    private Input1 input;


    @Override
    public void open(String fileName){
        input = new Input1();
        input.open(fileName);
        buffer = IntBuffer.allocate(bufferSize);
        fillBuffer();

    }

    private void fillBuffer(){
        bufferUse = 0;
        i = 0;
        buffer.clear();
        while (!input.endOfStream() && bufferUse < buffer.capacity()) {
            buffer.put(input.readNext());
            ++bufferUse;
        }

    }

    @Override
    public int readNext() {
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

}
