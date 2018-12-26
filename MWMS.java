import java.util.PriorityQueue;
import java.util.List;
import java.io.IOException;

public class MWMS {

    PriorityQueue<Input> queue;

    public MWMS(List<Input> d_inputStream) throws IOException {
        queue = new PriorityQueue<Input>();
        for(Input input : d_inputStream) {
            if(!(input.endOfStream())) {
                input.readNext();
                queue.add(input);
            }
        }
    }

    public void merge(Output out) throws IOException {
        while(!queue.isEmpty()) {
            Input in = queue.poll();
            out.writeInt(in.getCurrentValue());
            if(!(in.endOfStream())) {
                in.readNext();
                queue.add(in);
            }
        }
    }
}
