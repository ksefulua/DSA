import java.io.IOException;

public interface Output{
    
    public void writeInt(int toWrite) throws IOException;

    public void close() throws IOException;
}
