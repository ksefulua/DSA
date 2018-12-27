import java.io.File;
import java.io.IOException;

public class Output1Factory implements OutputFactory {

    @Override
    public Output getFreshOutputStream(String fileName) throws IOException{
        return new Output1(new File(fileName));
    }
}
