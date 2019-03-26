import java.util.*;

public class Solucion {

    private ArrayList<Integer> nodos_ordenados;
    private HashMap<Integer, Float> nodos_distancia;
    private float valorObjetivo;
    private Set<Integer> nodos_escogidos;
    private ArrayList<Integer> nodosAleatorios;

    public Solucion(ArrayList nodos_ordenados, HashMap<Integer, Float> nodos_distancia, float valorObjetivo, Set<Integer> nodos_escogidos, ArrayList<Integer> nodosAleatorios) {
        this.nodos_ordenados = nodos_ordenados;
        this.nodos_distancia = nodos_distancia;
        this.valorObjetivo = valorObjetivo;
        this.nodos_escogidos = nodos_escogidos;
        this.nodosAleatorios = nodosAleatorios;
    }

    public Solucion() {
        this.nodos_ordenados = new ArrayList<>();
        this.nodos_distancia = new HashMap<>();
        this.valorObjetivo = 0;
        this.nodos_escogidos = new HashSet<>();
        this.nodosAleatorios = new ArrayList<>();
    }


    public float getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(float valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }


    public ArrayList<Integer> getNodos_ordenados() {
        return nodos_ordenados;
    }

    public void setNodos_ordenados(ArrayList<Integer> nodos_ordenados) {
        this.nodos_ordenados = nodos_ordenados;
    }

    public Solucion generarSolucionInicial(Instancia instancia){
        Random rand = new Random();
        int max = instancia.getNum_vertices();
        int min = 1;
        int value = rand.nextInt(max - min) + min;
        int i = 0;
        while(nodos_escogidos.size()<max){
            nodos_ordenados.add(i, value);
            nodos_escogidos.add(value);
            ArrayList<Integer> aristasOrdenadas = instancia.getAristasOrdenadas(value, nodos_escogidos);
            valorObjetivo = valorObjetivo + instancia.getDistancia(value, aristasOrdenadas.get(0));
            value = aristasOrdenadas.get(0);
            i++;
        }
        Solucion solucion = new Solucion();
        return solucion;
    }
}
