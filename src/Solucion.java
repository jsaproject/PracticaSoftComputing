import java.util.*;

public class Solucion {

    private HashMap<Integer,Integer> nodos_ordenados;
    private HashMap<Integer, Float> nodos_distancia;
    private float valorObjetivo;
    private Set<Integer> nodos_escogidos;
    private ArrayList<Integer> nodosAleatorios;
    private HashMap<Integer, HashMap<Integer, Integer>> cambios_ordenados;

    public Solucion(HashMap<Integer,Integer> nodos_ordenados, HashMap<Integer, Float> nodos_distancia, float valorObjetivo, Set<Integer> nodos_escogidos, ArrayList<Integer> nodosAleatorios, HashMap<Integer, HashMap<Integer, Integer>> cambios_ordenados) {
        this.nodos_ordenados = new HashMap<>(nodos_ordenados);
        this.nodos_distancia = new HashMap<>(nodos_distancia);
        this.valorObjetivo = valorObjetivo;
        this.nodos_escogidos = new HashSet<>(nodos_escogidos);
        this.nodosAleatorios = new ArrayList<>(nodosAleatorios);
        this.cambios_ordenados = new HashMap<>(cambios_ordenados);
    }

    public Solucion() {
        this.nodos_ordenados = new HashMap<>();
        this.nodos_distancia = new HashMap<>();
        this.valorObjetivo = 0;
        this.nodos_escogidos = new HashSet<>();
        this.nodosAleatorios = new ArrayList<>();
        this.cambios_ordenados = new HashMap<>();
    }


    public float getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(float valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }


    public HashMap<Integer, Integer> getNodos_ordenados() {
        return nodos_ordenados;
    }

    public void setNodos_ordenados(HashMap<Integer, Integer> nodos_ordenados) {
        this.nodos_ordenados = nodos_ordenados;
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getCambios_ordenados() {
        return cambios_ordenados;
    }

    public void setCambios_ordenados(HashMap<Integer, HashMap<Integer, Integer>> cambios_ordenados) {
        this.cambios_ordenados = cambios_ordenados;
    }

    public HashMap<Integer, Float> getNodos_distancia() {
        return nodos_distancia;
    }

    public void setNodos_distancia(HashMap<Integer, Float> nodos_distancia) {
        this.nodos_distancia = nodos_distancia;
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
        int i = 0;
        int aux_nodo = -1;
        while(nodos_escogidos.size()<max){
            float distancia_actual = -1;
            float distancia_nodos;
            nodos_ordenados.put(i, value);
            nodos_escogidos.add(value);
            ArrayList<Integer> aristas = instancia.getAristas(value);
            Iterator<Integer> iterator = aristas.iterator();
            while(iterator.hasNext()){
                Integer next = iterator.next();
                if (!nodos_escogidos.contains(next)){
                    distancia_nodos = instancia.getDistancia(value, next);
                    if ((distancia_actual < distancia_nodos) && (!nodos_escogidos.contains(next))){
                        aux_nodo = next;
                        distancia_actual = distancia_nodos;
                    }
                }
            }
            valorObjetivo = valorObjetivo + distancia_actual;
            value = aux_nodo;
            i++;
        }

        return this;
    }
}
