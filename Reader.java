import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream; 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.OutputStream; 
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

class Reader {

    File _file;
    ArrayList<Integer> _numbersReaded;
    Reader (String file) {
        _file = new File(file) ;

    }
    public File getFile () {
        return _file;
    }

    public void readFile_1 () {
        
        try {
            InputStream is = new FileInputStream(_file);
            DataInputStream ds = new DataInputStream(is);
            System.out.println("readed values below with first reader");
            while (ds.available()>0){
                int x = ds.readInt();
                System.out.println(x);
            }
            ds.close();
            is.close();
        } catch (IOException e) {
            System.out.println ("Oups.. Something went wrong with the file...");
        }
    }

    public void readFile_2 () {
        try {
            InputStream is = new FileInputStream(_file);
            BufferedInputStream bis = new BufferedInputStream( is );
            DataInputStream ds = new DataInputStream( bis );
            System.out.println("readed values below with second reader");
            while (ds.available()>0){
                int x = ds.readInt();
                System.out.println(x);
            }
            ds.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            System.out.println ("Oups.. Something went wrong with the file...");
        }
        
    }

    public void readFile_3 () {

        if ( BufferInitializator.isBufferHasBeenModified()){ // le buffer est plein donc 
            // on va ecrire le contenu du buffer dans un fichier
            int bufferCapacity = BufferInitializator._buffer.capacity(); 

            File file = new File ("ecrireContenuBuffer.txt");
            try {
                OutputStream os = new FileOutputStream (file);
                DataOutputStream dos = new DataOutputStream(os);
                for (int i=0; i<bufferCapacity; i++){
                    dos.writeInt(BufferInitializator._buffer.get(i));
                } 
                dos.close();
                os.close();
            } catch (IOException e) {
                System.out.println("Opus .. Something went wrong with the file...");
            }
        }
        else {
            System.out.println("The buffer is empty");
        }
    }

    public void readFile_4 () {
        try {
            FileOutputStream os = new FileOutputStream("ecrireContenuMapper.txt");
            FileChannel fileChannel =  os.getChannel();
            fileChannel.write(BufferInitializator._mapped_buffer);

        } catch (IOException e) {
            System.out.println("Something went wrong with the file");
        }
    }

    public void test_si_sur_la_lecture (String fichierAlire) {
        
        try {
            InputStream is = new FileInputStream(fichierAlire);
            DataInputStream ds = new DataInputStream(is);
            System.out.println("readed values below with first reader from test method");
            while (ds.available()>0){
                int x = ds.readInt();
                System.out.println(x);
            }
            ds.close();
            is.close();
        } catch (IOException e) {
            System.out.println ("Oups.. Something went wrong with the file...");
        }
    }
}