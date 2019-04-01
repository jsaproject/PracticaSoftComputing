import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Grasp {

    private Solucion solucionFinal;
    private float funcionObjetivo;
    private int kmaximo;
    private Set<String> solucionesVisitadas;

    public Grasp() {
        this.solucionFinal = new Solucion();
        this.funcionObjetivo = -1;
        this.kmaximo = -1;
        solucionesVisitadas = new HashSet<>();
    }


    public float getFuncionObjetivo() {
        return funcionObjetivo;
    }

    public void setFuncionObjetivo(int funcionObjetivo) {
        this.funcionObjetivo = funcionObjetivo;
    }

    public int BVNS(Solucion a, int kmax, long finaltime){

        solucionFinal = a;
        solucionesVisitadas.add(solucionFinal.getHashValuenormal());
        funcionObjetivo = solucionFinal.getValorObjetivo();
        kmaximo = kmax;
        Solucion solucionShake = new Solucion();
        Solucion solucionLocalSearch = new Solucion();
        Timer timer = new Timer();

        //while (timer.purge()<finaltime){
            int kactual = 1;
            while(kactual<=kmax){
                    solucionShake = shake(a, kactual);
                    if(!solucionesVisitadas.contains(solucionShake.getHashValueShake())){
                        solucionLocalSearch = localSearch(solucionShake);
                        solucionesVisitadas.add(solucionLocalSearch.getHashValueShake());
                        kactual = NeighborhoodChange(solucionFinal,solucionLocalSearch, kactual);
                    }else{
                        kactual++;
                    }

            }
        //}

        return kmax;

    }

    private int NeighborhoodChange(Solucion a, Solucion solucionLocalSearch, int kactual) {
        if (a.getValorObjetivo()>=solucionLocalSearch.getValorObjetivo()){
            return kactual+1;
        }else{
            solucionFinal = solucionLocalSearch;
            funcionObjetivo = solucionLocalSearch.getValorObjetivo();
            return 1;
        }
    }


    private Solucion shake (Solucion solucionFinal, int kactual) {
        Solucion solucion = new Solucion(solucionFinal.getNodos_ordenados(),solucionFinal.getValorObjetivo(), solucionFinal.getI(),solucionFinal.getNodos_escogidos(),solucionFinal.getHashValuenormal());
        Random rand = new Random();
        int max = solucionFinal.getNodos_ordenados().size()-1;
        int min = 0;
        int i = 0;
/*        Integer[] arrayEnteros = new Integer[2];
        HashMap<Integer, Integer[]> valores_cambiados = new HashMap<>();*/
        while(i < kactual){
/*            arrayEnteros[0] = rand.nextInt(max - min) + min;
            arrayEnteros[1] = rand.nextInt(max - min) + min;

            valores_cambiados.put(i,arrayEnteros);
            i++;*/

        solucion.realizarCambios(rand.nextInt(max - min) + min);
        i++;
        }
        //solucion.realizarCambios(valores_cambiados);

        return solucion;
    }


    private Solucion localSearch(Solucion solucionShake) {
        solucionShake.calcularFuncionObjetivo();
        return solucionShake;
    }



}
