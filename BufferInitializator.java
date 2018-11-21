import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
public class BufferInitializator{

    public static IntBuffer _buffer =  IntBuffer.allocate(5); //pour le type 3
    public static MappedByteBuffer _mapped_buffer; // pour le type 4

    public static Boolean isBufferHasBeenModified() {
        System.out.println("Contenu buffer --------------------------");
        Boolean HasBeenModified = false;
        for (int i = 0; i < _buffer.capacity(); i++) {
            System.out.println(_buffer.get(i));
            if (_buffer.get(i) != 0){
                HasBeenModified = true;
            }
        }
        return HasBeenModified;
    } 
}