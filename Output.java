import java.io.IOException;

public interface Output{

    void create(String filePath);

    void write(int toWrite) throws IOException;

    void close() throws IOException;
}
