import java.util.*;

public class Solucion {

    private HashMap<Integer,Integer> nodos_ordenados;
    private Set<Integer> nodos_escogidos;
    private float valorObjetivo;
    private Instancia inst;



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
        this.nodos_ordenados = new HashMap<>(nodos);
        this.valorObjetivo = valor;
        this.nodos_escogidos = new HashSet<>(nodoscogidos);
        this.inst = im;

    }

    public Solucion() {
        this.nodos_ordenados = new HashMap<>();
        this.valorObjetivo = 0;
        this.nodos_escogidos = new HashSet<>();
        this.inst = new Instancia();

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


    public void realizarShake(int a) {

        Random random = new Random(inst.getSemillaRandom());
        int max = inst.getVertices().size()-1;
        int min = 0;

        boolean cogido = true;

        int b = random.nextInt(max - min) + min;
/*        while (cogido){

            cogido = nodos_escogidos.contains(b);
        }*/
        realizarCambio(a,b);
/*        Integer nodoquitado = nodos_ordenados.get(a);
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

        }*/


    }


    public void realizarCambio(int a, int b){
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

    //Realizar Local Search como nos ha indicado



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

