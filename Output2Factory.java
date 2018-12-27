import java.io.File;
import java.io.IOException;

public class Output2Factory implements OutputFactory {

    @Override
    public Output getFreshOutputStream(String fileName) throws IOException{
        return new Output2(new File(fileName));
    }
}
