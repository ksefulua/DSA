import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.io.IOException;

public class EMWMS {

    private int M;
    private String fileToSort;
    private int memoryAvailable[];
    private List<Input> sortedInput;
    private int tempFile;
    private int d;
    private Input inputStream;
    private int fileSize;

    public EMWMS(int M, int d, String fileToSort, Input inputStream) throws IOException {
        this.M = M;
        this.fileToSort = fileToSort;
        this.fileSize = (int) (new File(fileToSort).length() / (long) 4);
        this.inputStream = inputStream;
        this.d = d;
        memoryAvailable = new int[M];
        sortedInput = new LinkedList<Input>();
        tempFile = 0;
        sort();
    }

    private  void sort() throws IOException {
        int alreadySort = 0;
        inputStream.open(fileToSort);
        for(int i = 0 ; i < Math.ceil(fileSize / M) ; i++ ) {
            int numberToSort = Math.min(M, fileSize - alreadySort);
            for(int j = 0 ; j < numberToSort; j++ ) {
                memoryAvailable[j] = inputStream.readNext();
            }
            randomizedQuickSort(0, numberToSort - 1);
            sortedInput.add(save());
            alreadySort += numberToSort;
        }
        mergePhase();
    }

    private void randomizedQuickSort(int s, int e) {
        if(s < e ) {
            int randomIndex = new Random().nextInt(e - s) + s;
            int pivot = memoryAvailable[randomIndex];
            swap(randomIndex, e);
            int i = s - 1 ;
            for(int j = s ; j < e ; j++ ) {
                if(memoryAvailable[j] <= pivot) {
                    i++;
                    swap( i, j);
                }
            }
            swap(i + 1, e);
            randomizedQuickSort(s, i);
            randomizedQuickSort(i + 2, e);
        }
    }

    private void swap(int i1, int i2) {
        int buffer = memoryAvailable[i1];
        memoryAvailable[i1] = memoryAvailable[i2];
        memoryAvailable[i2] = buffer;
    }

    private Input save() throws IOException{
        String fileName = "temp" + ++tempFile + ".txt";
        File tempFIle = new File(fileName);
        Output out = new Output1(tempFIle);
        for(int i : memoryAvailable) {
            out.writeInt(i);
        }
        out.close();

        return getFreshInputStream(fileName);
    }

    private Input getFreshInputStream(String filepath) {
        Input input;
        try {
            input = this.inputStream.getClass().newInstance();
            input.open(filepath);
            return input;
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    private void mergePhase() throws IOException{
        while(sortedInput.size() != 1) {
            List<Input> toCombine = new LinkedList<Input>();
            int j = 0;
            while(j<d && !sortedInput.isEmpty()){
                toCombine.add(sortedInput.remove(0));
                ++j;
            }
            MWMS combine = new MWMS(toCombine);
            String fileName = "temp" + ++tempFile + ".txt";
            File tempFIle = new File(fileName);
            combine.merge(new Output1(tempFIle));
            sortedInput.add(getFreshInputStream(fileName));
        }
        Input in = sortedInput.remove(0);
        while(in.endOfStream()){
            System.out.print(in.readNext()+ " ");
        }
        System.out.println();
        for(int i = 0 ; i < tempFile ; i++ ){
            File temp = new File("temp" + i + ".txt");
            temp.delete();
        }
    }

}
