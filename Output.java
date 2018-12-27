import java.io.IOException;

public interface Output {

    void write(int toWrite) throws IOException;

    void close() throws IOException;
}
