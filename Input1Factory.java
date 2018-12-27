import java.io.File;
import java.io.IOException;

public class Input1Factory implements InputFactory {

    @Override
    public Input getFreshInputStream(String fileName) throws IOException{
        return new Input1(new File(fileName));
    }
}
