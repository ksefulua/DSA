import java.io.File;
import java.io.IOException;

public class Output3Factory implements OutputFactory {
    
    private final int B;
     
    public Output3Factory(int B){
        this.B = B;
    }

    @Override
    public Output getFreshOutputStream(String fileName) throws IOException{
        return new Output3(new File(fileName), B);
    }
}
