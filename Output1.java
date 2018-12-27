import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File;

public class Output1 implements Output {
    private OutputStream os;
    private DataOutputStream dos;


    @Override public void create(String fileName){
        try {
            os = new FileOutputStream (new File(fileName));
            dos = new DataOutputStream(os);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void write(int integer){
        try {
            dos.writeInt(integer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        try {
            dos.close();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
