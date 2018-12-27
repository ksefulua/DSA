import java.io.IOException;

public abstract class Input implements Comparable<Input> {

    int current;

    public abstract int readNext() throws IOException;

    public abstract boolean endOfStream() throws IOException;

    public abstract void close() throws IOException;

    public int getCurrentValue() {
        return current;
    }

    @Override
    public int compareTo(Input other) {
        if(other.getCurrentValue() == getCurrentValue()) {
            return 0;
        }
        else if(getCurrentValue() < other.getCurrentValue()) {
            return -1;
        }
        return 1;
    }
}
