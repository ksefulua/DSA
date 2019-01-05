import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.io.IOException;

public class EMWMS {

    private int M;
    private int memoryAvailable[];
    private List<Input> sortedInput;
    private int tempFile;
    private InputFactory inputFactory;
    private OutputFactory outputFactory;
    private int fileSize;

    public EMWMS(int M, int d, String fileToSort, InputFactory inputFactory, OutputFactory outputFactory) throws IOException {
        this.M = M;
        this.fileSize = (int) (new File(fileToSort).length() / (long) 4);
        this.inputFactory = inputFactory;
        this.outputFactory = outputFactory;
        memoryAvailable = new int[M];
        sortedInput = new LinkedList<Input>();
        tempFile = 0;
        sort(fileToSort);
        mergePhase(d);
    }

    private  void sort(String fileToSort) throws IOException {
        int alreadySort = 0;
        Input input = inputFactory.getFreshInputStream(fileToSort);
        for(int i = 0 ; i < Math.ceil(fileSize / (float) M) ; i++ ) {
            int numberToSort = Math.min(M, fileSize - alreadySort);
            for(int j = 0 ; j < numberToSort; j++ ) {
                memoryAvailable[j] = input.readNext();
            }
            Arrays.sort(memoryAvailable, 0,numberToSort - 1);
            sortedInput.add(save(numberToSort));
            alreadySort += numberToSort;
        }
    }

    private Input save(int numberToSort) throws IOException{
        String fileName = "storage/temp" + ++tempFile + ".txt";
        Output out = outputFactory.getFreshOutputStream(fileName);
        for(int i = 0 ; i < numberToSort ; i++ ) {
            out.write(memoryAvailable[i]);
        }
        out.close();
        return inputFactory.getFreshInputStream(fileName);
    }

    private void mergePhase(int d) throws IOException{
        while(sortedInput.size() != 1) {
            List<Input> toCombine = new LinkedList<Input>();
            int j = 0;
            while(j < d && !sortedInput.isEmpty()) {
                toCombine.add(sortedInput.remove(0));
                ++j;
            }
            MWMS combine = new MWMS(toCombine);
            String fileName = "storage/temp" + ++tempFile + ".txt";
            combine.merge(outputFactory.getFreshOutputStream(fileName));
            sortedInput.add(inputFactory.getFreshInputStream(fileName));
        }
        for(int i = 0 ; i < tempFile ; i++ ) {
            File temp = new File("storage/temp" + i + ".txt");
            temp.delete();
        }
    }

}
