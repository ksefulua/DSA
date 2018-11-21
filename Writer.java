import java.io.DataOutputStream;
import java.io.OutputStream; 
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStream; 
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.nio.MappedByteBuffer;
class Writer {
    private Generator _generator;
    private File _file;
    private String _fileStr;
    private ArrayList<Integer> _numbersGenerated; 
    Writer (Generator generator, String file){
        _generator = generator;
        _file = new File( file);
        _fileStr = file;
        getNumbersGenerated();

    }

    private void getNumbersGenerated () {
        _numbersGenerated = _generator.generateNumbers();
    }
    public Generator getGenerator (){
       return _generator;
    }

    public File getFile(){
        return _file;
    }

    public void writeOnFile_1() {

        try {
            OutputStream os = new FileOutputStream (_file);
            DataOutputStream dos = new DataOutputStream(os);
            System.out.println("Values writted");
            for (int i=0; i<_numbersGenerated.size(); i++){
                System.out.println(_numbersGenerated.get(i));
                dos.writeInt(_numbersGenerated.get(i));
            } 
            dos.close();
            os.close();
        } catch (IOException e) {
            System.out.println("Opus .. Something went wrong with the file...");
        }
    }

    public void writeOnfile_2 () {

        try {
            OutputStream os = new FileOutputStream (_file);
            BufferedOutputStream bos = new BufferedOutputStream( os );
            DataOutputStream dos = new DataOutputStream(bos);
            for (int i=0; i<_numbersGenerated.size(); i++){
                dos.writeInt(_numbersGenerated.get(i));
            } 
            dos.close();
            bos.close();
            os.close();
        } catch (IOException e) {
            System.out.println("Opus .. Something went wrong with the file...");
        }

    }

    public void writeOnFile_3 (){
       
        if ( ! BufferInitializator.isBufferHasBeenModified()){
            int bufferCapacity = BufferInitializator._buffer.capacity(); 
            writeOnFile_1 ();
            try {
                InputStream is = new FileInputStream(_file);
                DataInputStream ds = new DataInputStream(is);
                for (int i = 0; i < bufferCapacity; i++){
                    BufferInitializator._buffer.put (ds.readInt());
                }
                ds.close();
                is.close();
            } catch (IOException e) {
                System.out.println ("Oups.. Something went wrong with the file...");
            }
            BufferInitializator.isBufferHasBeenModified();
        } 
        else {
            System.out.println("The buffer is already full");
        }
    }

    public void writeOnfile_4 (int B, int index) { // B c'est le nombre de données qu'on map et index c'est à partir de où qu'on map.
        writeOnFile_1();
        try {
            Path path = Paths.get(_fileStr);
            FileChannel fileChannel = FileChannel.open (path, StandardOpenOption.READ, StandardOpenOption.WRITE );
            BufferInitializator._mapped_buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, index*B, B);
            fileChannel.close();
            System.out.println("finished");

        } catch (IOException e) {
            System.out.println("Oups.. Something went wrong with the file ...");
        }
    }
}