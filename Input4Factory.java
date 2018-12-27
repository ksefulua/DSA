import java.io.File;
import java.io.IOException;

public class Input4Factory implements InputFactory {
    private final int B;

    public Input4Factory(int B) {
        this.B = B;
    }

    @Override
    public Input getFreshInputStream(String fileName) throws IOException{
        return new Input4(new File(fileName), B);
    }
}
