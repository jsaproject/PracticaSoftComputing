import java.util.*;

public class TabuSearch {

    private Solucion x,y;
    private ArrayList<Solucion> vecindad;
    private ArrayList<Solucion> vecindadReducida;
    private ArrayList<Solucion> memoria;
    private int k;

    public TabuSearch(Solucion x, int valorTabu) {
        this.x = new Solucion(x.getNodos_ordenados(),x.getValorObjetivo(), x.getI(),x.getNodos_escogidos());
        this.y = new Solucion(x.getNodos_ordenados(),x.getValorObjetivo(), x.getI(),x.getNodos_escogidos());
        this.vecindad = new ArrayList<>();
        this.vecindadReducida = new ArrayList<>();
        this.memoria = new ArrayList<>();
        this.k = valorTabu;
    }

    public Solucion TabuSearch(){
        int kactual = 0;
        while (kactual<k){
                generarVecindad();
                generarVecindadReducida();
            kactual++;
        }

        return y;
    }

    private void generarVecindadReducida() {
        int size = vecindad.size();
        size = size/3;
        int i = 0;
        Random random = new Random(x.getI().getSemillaRandom());
        int i1 = random.nextInt(vecindad.size()-0)+0;
        x = vecindad.get(i1);
/*        if (size==0){
            size = vecindad.size();
        }
        while(i<size){
            Random random = new Random(x.getI().getSemillaRandom());
            int i1 = random.nextInt(vecindad.size()-0)+0;
            if (i==0){
                x = vecindad.get(i1);
            }else{
                CompararFloats compararFloats = new CompararFloats();
                if(compararFloats.mayor(vecindad.get(i1).getValorObjetivo(),x.getValorObjetivo())){
                    x = vecindad.get(i1);
                }
            }
            i++;
        }*/
    }

    private void generarVecindad() {
        Instancia i = this.x.getI();
        int size = x.getNodos_escogidos().size();
        int k = 0;
         ArrayList<Integer> vertices = i.getVertices();
         while(k<size) {
            Iterator<Integer> iterator = vertices.iterator();
            while(iterator.hasNext()){
                Solucion solucion = new Solucion(x.getNodos_ordenados(),x.getValorObjetivo(), x.getI(),x.getNodos_escogidos());
                solucion.realizarCambio(k,iterator.next());
                vecindad.add(solucion);
                CompararFloats compararFloats = new CompararFloats();
                if(compararFloats.mayor(solucion.getValorObjetivo(), y.getValorObjetivo())){
                    y = solucion;
                }
            }
            k++;
        }
    }
}
