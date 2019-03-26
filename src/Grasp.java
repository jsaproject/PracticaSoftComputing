import java.util.ArrayList;
import java.util.HashMap;
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
        int p = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch latch = new CountDownLatch(p);
        int k = 1;
        int kmax = 10;
        int iter = 1;
        while(k<=kmax){
            final int kfinal = k;
            for (int i =0; i<p; i++){
                final int processor = i;
                pool.submit(() ->{
                    shake2(processor, kfinal);
                    improve2(processor,kfinal);
                    latch.countDown();
                });
            }
            latch.await();

            k++;

            System.out.println("Iteracion"+ iter);
            iter++;
        }

        ArrayList<Integer> nodos_ordenados = solucionFinal.getNodos_ordenados();
        pool.shutdown();
        return new Solucion();
    }

    private void shake2(int a, int b){
        System.out.println("Procesador: "+a+" , K actual: " + b);
    }

    private void improve2(int a, int b){
        System.out.println("Procesador: "+a+" , K actual: " + b);
    }


    private Solucion localSearch(Solucion solucionShake) {

        return new Solucion();
    }



}
