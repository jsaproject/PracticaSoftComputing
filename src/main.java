

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class main {

    public static void main(String[] args) {


        String ruta_carpeta = "C:\\instancias";

        HashInstancias hashInstancias = new HashInstancias();
        hashInstancias.inicializarMapInstancias(ruta_carpeta);
        Collection<Instancia> instancias = hashInstancias.getInstancias();
        Iterator<Instancia> iterator = instancias.iterator();
        Excel excel = new Excel();

       excel.comprobarExcel("VNS", "VNS");
        while(iterator.hasNext()){
            Instancia next = iterator.next();
            Random r = new Random();
            float v = r.nextFloat();
            float v2 = r.nextFloat();
            next.setFuncion_objetivo(v);
            next.setTiempo(v2);
            excel.insertarInstancia(next);
        }
        excel.hallarPromedio();
        excel.cerrarExcel();




    }
}
