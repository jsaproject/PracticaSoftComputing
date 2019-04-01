import java.util.*;

public class Solucion {

    private HashMap<Integer,Integer> nodos_ordenados;
    private Set<Integer> nodos_escogidos;
    private float valorObjetivo;
    private ArrayList<Integer> posiciones_cambiadas;
    private HashMap<Integer, Integer> nodos_ordenados_shake;
    private Set<Integer> nodos_escogidos_shake;
    private Instancia inst;
    private Set posiciones_cambiadas_noreps;
    private String hashValuenormal;
    private String hashValueShake;

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

    public Solucion(HashMap<Integer,Integer> nodos, float valor, Instancia im, Set<Integer> nodoscogidos, String hashValue) {
        this.nodos_ordenados = (HashMap<Integer, Integer>) nodos.clone();
        this.valorObjetivo = valor;
        this.posiciones_cambiadas = new ArrayList<>();
        this.nodos_ordenados_shake = (HashMap<Integer, Integer>) nodos.clone();
        this.nodos_escogidos = new HashSet<>(nodoscogidos);
        this.nodos_escogidos_shake = new HashSet<>(nodoscogidos);
        this.posiciones_cambiadas_noreps = new HashSet();
        this.inst = im;
        this.hashValuenormal = hashValue;
        this.hashValueShake = hashValue;
    }

    public Solucion() {
        this.nodos_ordenados = new HashMap<>();
        this.valorObjetivo = 0;
        this.nodos_ordenados_shake = new HashMap<>();
        this.posiciones_cambiadas = new ArrayList<>();
        this.nodos_escogidos = new HashSet<>();
        this.inst = new Instancia();
        this.posiciones_cambiadas_noreps = new HashSet();
        this.nodos_escogidos_shake = new HashSet<>();
        this.hashValuenormal= "";
        this.hashValueShake = "";
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
            crearHashSolucion(value);
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

        if (!posiciones_cambiadas_noreps.contains(a)) {
        posiciones_cambiadas_noreps.add(a);
        posiciones_cambiadas.add(a);
    }
        boolean cogido = true;
        int b = 0;
        while (cogido){
            b = random.nextInt(max - min) + min;
            cogido = nodos_escogidos_shake.contains(b);
        }
        Integer integer = nodos_ordenados_shake.get(a);
        nodos_escogidos_shake.remove(integer);
        nodos_ordenados_shake.put(a,b);
        nodos_escogidos_shake.add(b);
        modificarHashShake(b,a);

    }

    public void calcularFuncionObjetivo() {
        Iterator<Integer> iterator = posiciones_cambiadas.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            int size = nodos_ordenados.size();
            float distancia = 0;
            float distancia1 = 0;
            float distancia2 = 0;
            float distancia3 = 0;

            int k = 0;
            int size_total = nodos_ordenados.size();
            while(k<size_total){
                if (k!=next){
                    Integer integer = nodos_ordenados.get(k);
                    Integer integer2 = nodos_ordenados.get(next);

                    valorObjetivo = valorObjetivo - inst.getDistancia(integer,integer2);

                    integer = nodos_ordenados_shake.get(k);
                    integer2 = nodos_ordenados_shake.get(next);

                    valorObjetivo = valorObjetivo + inst.getDistancia(integer,integer2);



                }
                k++;

            }

/*            try{


            if((next != 0)&&(next != size)){
                Integer integer = nodos_ordenados.get(next);
                Integer integer1 = nodos_ordenados.get(next - 1);
                Integer integer2 = nodos_ordenados.get(next + 1);
                Integer integer3 = nodos_ordenados_shake.get(next);
                Integer integer4 = nodos_ordenados_shake.get(next-1);
                Integer integer5 = nodos_ordenados_shake.get(next + 1);

                distancia = inst.getDistancia(integer, integer1);
                 distancia1 = inst.getDistancia(integer, integer2);
                 distancia2 = inst.getDistancia(integer3, integer4);
                 distancia3 = inst.getDistancia(integer3, integer5);
            }
            if(next == 0){
                Integer integer = nodos_ordenados.get(next);

                Integer integer2 = nodos_ordenados.get(next + 1);
                Integer integer3 = nodos_ordenados_shake.get(next);

                Integer integer5 = nodos_ordenados_shake.get(next + 1);
                distancia1 = inst.getDistancia(integer, integer2);
                 distancia3 = inst.getDistancia(integer3, integer5);
            }
            if(next == size){
                Integer integer = nodos_ordenados.get(next);
                Integer integer1 = nodos_ordenados.get(next - 1);

                Integer integer3 = nodos_ordenados_shake.get(next);
                Integer integer4 = nodos_ordenados_shake.get(next-1);

                 distancia = inst.getDistancia(integer, integer1);
                 distancia2 = inst.getDistancia(integer3, integer4);
            }

            valorObjetivo = valorObjetivo - distancia - distancia1;
            valorObjetivo = valorObjetivo + distancia2 + distancia3;

            }catch (Exception e){
                System.out.println(next);
            }*/
        }

    }

    public void crearHashSolucion(int a){
        hashValuenormal = hashValuenormal + String.valueOf(a);
    }

    public void modificarHashShake(int a, int posicion){
        String s = String.valueOf(a);
        char[] tempCharArray = hashValueShake.toCharArray();
        tempCharArray[posicion] = s.toCharArray()[0];
        hashValueShake = String.valueOf(tempCharArray);
    }

    public String getHashValuenormal() {
        return hashValuenormal;
    }

    public void setHashValuenormal(String hashValuenormal) {
        this.hashValuenormal = hashValuenormal;
    }

    public String getHashValueShake() {
        return hashValueShake;
    }

    public void setHashValueShake(String hashValueShake) {
        this.hashValueShake = hashValueShake;
    }
}
