import java.io.IOException;

public interface InputFactory {

    public Input getFreshInputStream(String fileName) throws IOException;

}
