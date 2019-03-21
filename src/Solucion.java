import java.util.*;

public class Solucion {

    private Queue<Integer> nodos_ordenados;
    private HashMap<Integer, Float> nodos_distancia;
    private float valorObjetivo;
    private Set<Integer> nodos_escogidos;
    private ArrayList<Integer> nodosAleatorios;

    public Solucion(Queue<Integer> nodos_ordenados, HashMap<Integer, Float> nodos_distancia, float valorObjetivo, Set<Integer> nodos_escogidos, ArrayList<Integer> nodosAleatorios) {
        this.nodos_ordenados = nodos_ordenados;
        this.nodos_distancia = nodos_distancia;
        this.valorObjetivo = valorObjetivo;
        this.nodos_escogidos = nodos_escogidos;
        this.nodosAleatorios = nodosAleatorios;
    }

    public Solucion() {

    }

    public Queue<Integer> getNodos_ordenados() {
        return nodos_ordenados;
    }

    public void setNodos_ordenados(Queue<Integer> nodos_ordenados) {
        this.nodos_ordenados = nodos_ordenados;
    }

    public HashMap<Integer, Float> getNodos_distancia() {
        return nodos_distancia;
    }

    public void setNodos_distancia(HashMap<Integer, Float> nodos_distancia) {
        this.nodos_distancia = nodos_distancia;
    }

    public float getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(float valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public Set<Integer> getNodos_escogidos() {
        return nodos_escogidos;
    }

    public void setNodos_escogidos(Set<Integer> nodos_escogidos) {
        this.nodos_escogidos = nodos_escogidos;
    }

    public ArrayList<Integer> getNodosAleatorios() {
        return nodosAleatorios;
    }

    public void setNodosAleatorios(ArrayList<Integer> nodosAleatorios) {
        this.nodosAleatorios = nodosAleatorios;
    }

    public Solucion generarSolucionInicial(Instancia instancia){
        Random rand = new Random();
        int max = instancia.getNum_vertices();
        int min = 1;
        int value = rand.nextInt(max - min) + min;
        ArrayList<Integer> aristasOrdenadas = instancia.getAristasOrdenadas(value);
        nodos_ordenados.add(value);
        nodos_escogidos.add(value);
        vertices.
        instancia.getDistancia(value)
        Solucion solucion = new Solucion();
        return solucion;
    }
}
