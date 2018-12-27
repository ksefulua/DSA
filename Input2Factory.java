import java.io.File;
import java.io.IOException;

public class Input2Factory implements InputFactory {

    @Override
    public Input getFreshInputStream(String fileName) throws IOException{
        return new Input2(new File(fileName));
    }
}
