import java.io.File;
import java.io.IOException;

public interface OutputFactory {

    public Output getFreshOutputStream(String filePath) throws IOException;

}
