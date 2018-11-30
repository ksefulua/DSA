import java.util.ArrayList;
import java.lang.System.nanoTime;
/*
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
*/
class Launch {
    public static void main(String[] args) {
        int K=30; // K =30 car il faut prendre min (30, le nombre de stream que permet d'ouvrir Java = 1024)
        // Je creer donc K thread qui vont chacun se charger d'ouvrir K streams (fichier)
        ArrayList<runThreads> threadPool = new ArrayList<runThreads>();
        for (int i = 0; i< K; i++){
            threadPool.add (new runThreads("Thread-"+Integer.toString(i)));
        }

        long startTime = System.nanoTime();
        for (int j = 0; j < threadPool.size(); j ++){
            try {
                threadPool.get(j).start();
                threadPool.get(j).join();
            } catch (InterruptedException e) {
                System.out.println("Eroor thread");
            }
        }
        /* Pour voir le temps d'execution après que tout les threads ait fini de travailler*/
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.print(totalTime);
        System.out.println(" nanoseconds");
        long diviser = 1000000000;
        long sec = totalTime / diviser;
        System.out.print(sec);
        System.out.println(" seconds");
    }
}



