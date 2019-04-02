import java.util.*;

public class Solucion {

    private HashMap<Integer,Integer> nodos_ordenados;
    private Set<Integer> nodos_escogidos;
    private float valorObjetivo;
    private Instancia inst;
    private Set posiciones_cambiadas_noreps;


    public Set<Integer> getNodos_escogidos() {
        return nodos_escogidos;
    }

    public void setNodos_escogidos(Set<Integer> nodos_escogidos) {
        this.nodos_escogidos = nodos_escogidos;
    }

    public Instancia getI() {
        return inst;
    }

    public void setI(Instancia i) {
        this.inst = i;
    }

    public Solucion(HashMap<Integer,Integer> nodos, float valor, Instancia im, Set<Integer> nodoscogidos) {
        this.nodos_ordenados = (HashMap<Integer, Integer>) nodos.clone();
        this.valorObjetivo = valor;
        this.nodos_escogidos = new HashSet<>(nodoscogidos);
        this.posiciones_cambiadas_noreps = new HashSet();
        this.inst = im;

    }

    public Solucion() {
        this.nodos_ordenados = new HashMap<>();
        this.valorObjetivo = 0;
        this.nodos_escogidos = new HashSet<>();
        this.inst = new Instancia();
        this.posiciones_cambiadas_noreps = new HashSet();

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


    public Solucion generarSolucionInicial(Instancia instancia){
        inst = instancia;
        Random rand = new Random();
        int max = instancia.getNum_vertices();
        int size_vertices = instancia.getNum_verticestotales()-1;
        int min = 0;
        int value = rand.nextInt(size_vertices - min) + min;
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


            value = aux_nodo;
            i++;
        }

        int k = 0;
        int size = nodos_ordenados.size();
        int aux = 0;
        while(k<size){
            int j = k;
            j++;
            while(j<size){
                Integer integer = nodos_ordenados.get(k);
                Integer integer2 = nodos_ordenados.get(j);

                valorObjetivo = valorObjetivo + inst.getDistancia(integer,integer2);
                j++;
            }
            k++;
        }

        return this;
    }

    public void realizarCambios(int a) {

/*        this.cambios_ordenados=valores_cambiados;
        int size = cambios_ordenados.size();
        int i = 0;
        while(i<size){
            HashMap<Integer, Integer> integerIntegerHashMap = cambios_ordenados.get(i);
            Iterator<Map.Entry<Integer, Integer>> iterator = integerIntegerHashMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<Integer, Integer> next = iterator.next();
                Integer key = next.getKey();
                Integer value = next.getValue();
                nodos_ordenados.put(key, value);
                nodos_ordenados.put(value, key);
            }
        }*/
        Random random = new Random();
        int max = inst.getVertices().size()-1;
        int min = 0;

        boolean cogido = true;
        int b = 0;
        while (cogido){
            b = random.nextInt(max - min) + min;
            cogido = nodos_escogidos.contains(b);
        }
        Integer nodoquitado = nodos_ordenados.get(a);
        nodos_escogidos.remove(nodoquitado);
        nodos_ordenados.put(a,b);
        nodos_escogidos.add(b);

        int k = 0;
        int size_total = nodos_ordenados.size();
        while(k<size_total){
            if (k!=a){
                Integer integer = nodos_ordenados.get(k);


                valorObjetivo = valorObjetivo - inst.getDistancia(integer,nodoquitado);


                valorObjetivo = valorObjetivo + inst.getDistancia(integer,b);



            }
            k++;

        }


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solucion solucion = (Solucion) o;
        return nodos_escogidos.equals(solucion.nodos_escogidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodos_escogidos);
    }
}

