import java.io.File;
import java.io.IOException;

public class Output4Factory implements OutputFactory {

    private final int B;

    public Output4Factory(int B) {
        this.B = B;
    }

    @Override
    public Output getFreshOutputStream(String fileName) throws IOException{
        return new Output4(new File(fileName), B);
    }
}
