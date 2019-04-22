import java.util.*;

public class Constructive {

    public Solucion generarSolucionInicial(Instancia instancia){
        Solucion solucion = new Solucion();
        Random rand = new Random(instancia.getSemillaRandom());
        solucion.setI(instancia);
        int max = instancia.getNum_vertices();
        int size_vertices = instancia.getNum_verticestotales()-1;
        int min = 0;
        int value = rand.nextInt(size_vertices - min) + min;
        int i = 0;
        int aux_nodo = -1;
        while(solucion.getNodos_escogidos().size()<max){
            float distancia_actual = -1;
            float distancia_nodos;
            HashMap<Integer, Integer> nodos_ordenados = solucion.getNodos_ordenados();
            Set<Integer> nodos_escogidos = solucion.getNodos_escogidos();
            nodos_ordenados.put(i, value);
            nodos_escogidos.add(value);
            solucion.setNodos_escogidos(nodos_escogidos);
            solucion.setNodos_ordenados(nodos_ordenados);
            ArrayList<Integer> aristas = instancia.getAristas(value);
            Iterator<Integer> iterator = aristas.iterator();
            while(iterator.hasNext()){
                Integer next = iterator.next();
                if (!solucion.getNodos_escogidos().contains(next)){
                    distancia_nodos = instancia.getDistancia(value, next);
                    if ((distancia_actual < distancia_nodos) && (!solucion.getNodos_escogidos().contains(next))){
                        aux_nodo = next;
                        distancia_actual = distancia_nodos;
                    }
                }
            }


            value = aux_nodo;
            i++;
        }

        int k = 0;
        int size = solucion.getNodos_ordenados().size();
        int aux = 0;
        while(k<size){
            int j = k;
            j++;
            while(j<size){
                Integer integer = solucion.getNodos_ordenados().get(k);
                Integer integer2 = solucion.getNodos_ordenados().get(j);

                solucion.setValorObjetivo(solucion.getValorObjetivo() + instancia.getDistancia(integer,integer2));
                j++;
            }
            k++;
        }

        return solucion;
    }
}
