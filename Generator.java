import java.util.*;
import java.util.Random;

//50 is the maximum and the 1 is our minimum 
class Generator {

    private int _numberOfint;
    private ArrayList<Integer> _numbersGenerated;
    private Random _rand;

 
    public Generator (int numberOfint ){
        _numberOfint = numberOfint;
        _numbersGenerated = new ArrayList<Integer>();
        _rand = new Random();
    }
    private int getNumberofint(){
        return _numberOfint;
    }

    private void setNumberofint (int newNumberofint){
        _numberOfint = newNumberofint;
    }

    public ArrayList<Integer> generateNumbers (){
        int i = 0;
        while (i<_numberOfint){
            int randNumber = _rand.nextInt(1000) + 1;
            if (! _numbersGenerated.contains (randNumber)){
                _numbersGenerated.add (randNumber);
                i++;
            }
        }

        return _numbersGenerated;
    }
}