import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
class runThreads extends Thread {
    int N=500;
    public runThreads(String name){
        super(name);
      }
      public void run(){
        
        String file = this.getName()+".txt"; // Nom du thread.txt
        Writer writer = new Writer (new Generator (1), file); // on genere un element car 
        // l'enoncé demande d'ecrire N fois un element
        Reader reader = new Reader (file);
        IntBuffer buffer =  IntBuffer.allocate(N); // initialisation du buffer pour la methode 3 avec capacité 1
        
        for (int i=0; i<N; i++){
            //methode 1 : 
            /* Si tu veux tester l'ecriture N fois d'un element et la lecture 
            pour la methode 1 tu dois enlever du commentaire la ligne 22 et la ligne 38
            et si tu execute ça affichera le temps en nanoseconds que ça a pris
            pareil pour la methode 2 (sort du commentaire les lignes 24 et 40)*/
            //writer.writeOnFile_1();
            //methode 2 :
            //writer.writeOnfile_2();
            /* Comme tu le vois j'ai pas fais la methode 3 et 4 car j'arrive 
            pas a cerner ce qe le prof veux j'ai des doutes. (Quand il dis ecrire N fois 1 element) mais sinon
            les methodes d'ecriture et de lecture sont faite pour les methodes 3 et 4 (cf Writer (writeOnfile_3 et 4) et
             Reader(ReadFile_3 et 4 )). donc en bref 
            c'est juste quand il dis ecrire N fois 1 element que j'ai des doutes concernant
            ces deux methodes. */
            //buffer = writer.writeOnfile_3 (buffer);
            // MappedByteBuffer maped = writer.writeOnfile_4 (4, 0)
            // reader.readFile_4 (maped);
            
        }
        for (int i=0; i<N; i++){
            //methode 1 :
            //reader.readFile_1 ();
            //methode 2 :
            //reader.readFile_2 ();
            /* 
            Pareil ici pour la methode  3 et 4 */ 
            //reader.ReadFile_3 (buffer);
        }
      }      
}