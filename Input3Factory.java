import java.io.File;
import java.io.IOException;

public class Input3Factory implements InputFactory {
    private final int B;

    public Input3Factory(int B) {
        this.B = B;
    }

    @Override
    public Input getFreshInputStream(String fileName) throws IOException{
        return new Input3(new File(fileName), B);
    }
}
