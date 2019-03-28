import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Grasp {

    private Instancia i;
    private Solucion solucionFinal;
    private int funcionObjetivo;
    private int kactual;
    private int kmaximo;


    public int BVNS(Solucion a, int kmax, long finaltime){

        solucionFinal = a;
        kmaximo = kmax;
        Solucion solucionShake = new Solucion();
        Solucion solucionLocalSearch = new Solucion();
        while (System.currentTimeMillis()<finaltime){
            kactual = 1;
            while(kactual<=kmax){
                    solucionShake = shake(solucionFinal, kactual);
                    solucionLocalSearch = localSearch(solucionShake);
            }
        }

        return kmax;

    }


    private Solucion shake (Solucion solucionFinal, int kactual) {
        Solucion solucion = new Solucion(solucionFinal.getNodos_ordenados(), solucionFinal.getNodos_distancia(),solucionFinal.getValorObjetivo(),solucionFinal.getNodos_escogidos(), solucionFinal.getNodosAleatorios(), solucionFinal.getCambios_ordenados());
        Random rand = new Random();
        solucion = solucionFinal;
        int max = i.getNum_vertices();
        int min = 1;
        int i = 0;
        while(i < kactual){
            int posicion1 = rand.nextInt(max - min) + min;
            int posicion2 = rand.nextInt(max - min) + min;

        }

        return solucion;
    }


    private Solucion localSearch(Solucion solucionShake) {

        return new Solucion();
    }



}
