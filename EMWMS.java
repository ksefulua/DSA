import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.io.IOException;

public class EMWMS {

    int M;
    File fileToSort;
    int memoryAvailable[];
    List<Input> sortedInput;
    int tempFile;
    int d;

    public EMWMS(int M, int d, File fileToSort) throws IOException {
        this.M = M;
        this.fileToSort = fileToSort;
        this.d = d;
        memoryAvailable = new int[M];
        sortedInput = new LinkedList<Input>();
        tempFile = 0;
        sort();
    }

    private  void sort() throws IOException {
        int N = (int) (fileToSort.length() / (long)4); //length return the size in byte
        int alreadySort = 0;
        Input input = new Input1(fileToSort);
        for(int i = 0 ; i < Math.ceil(N / M) ; i++ ) {
            int numberToSort = Math.min(M, N - alreadySort);
            for(int j = 0 ; j < numberToSort; j++ ) {
                memoryAvailable[j] = input.getNext();
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
        File tempFIle = new File("temp" + ++tempFile + ".txt");
        Output out = new Output1(tempFIle);
        for(int i : memoryAvailable) {
            out.writeInt(i);
        }
        out.close();
        return new Input1(tempFIle);
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
            File tempFIle = new File("temp" + ++tempFile + ".txt");
            combine.merge(new Output1(tempFIle));
            sortedInput.add(new Input1(tempFIle));
        }
        Input in = sortedInput.remove(0);
        while(in.hasNext()){
            System.out.print(in.getNext()+ " ");
        }
        System.out.println();
        for(int i = 0 ; i < tempFile ; i++ ){
            File temp = new File("temp" + i + ".txt");
            temp.delete();
        }
    }

}
