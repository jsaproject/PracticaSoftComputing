import java.util.*;

public class TabuSearch {

    private Solucion x,y;
    private ArrayList<Solucion> vecindad;
    private ArrayList<Solucion> vecindadReducida;
    private ArrayList<Solucion> memoria;

    public TabuSearch(Solucion x) {
        this.x = x;
        this.y = new Solucion();
        this.vecindad = new ArrayList<>();
        this.vecindadReducida = new ArrayList<>();
        this.memoria = new ArrayList<>();
    }

    public Solucion TabuSearch(){
        int k = 15;
        int kactual = 0;
        while (kactual<k){


            k++;
        }

        return x;
    }
}
