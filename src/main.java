

import java.util.ArrayList;
import java.util.Iterator;


public class main {

    public static void main(String[] args) {


      String ruta_carpeta = "C:\\instancias";

        HashInstancias hashInstancias = new HashInstancias();
        hashInstancias.inicializarMapInstancias(ruta_carpeta);
        ArrayList<Instancia> instancias = hashInstancias.getInstancias();
        Iterator<Instancia> iterator = instancias.iterator();
        Excel excel = new Excel();
        int kvns= 100;
        int kTabu = 10;
       excel.comprobarExcel("VNS", "VNS", kvns, kTabu);
        long startTimePrograma = System.currentTimeMillis();
        while((iterator.hasNext())){
            Instancia next = iterator.next();
            VNS VNS = new VNS();
            Constructive constructive = new Constructive();
            Solucion solucion = constructive.generarSolucionInicial(next);
            long startTime = System.nanoTime();
            VNS.BVNS(solucion,kvns, 15, kTabu);
            long endTime = System.nanoTime()- startTime ;
            next.setFuncion_objetivo(VNS.getFuncionObjetivo());
            next.setTiempo(endTime);
            excel.insertarInstancia(next);
        }
        long endTimePrograma = System.currentTimeMillis() - startTimePrograma;
        excel.insertarParametros(endTimePrograma, kvns, kTabu, 1234);

        excel.hallarPromedio();
        excel.cerrarExcel();




        System.exit(0);


    }
}
