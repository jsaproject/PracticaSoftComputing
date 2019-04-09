

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class main {

    public static void main(String[] args) {


      String ruta_carpeta = "C:\\instancias";

        HashInstancias hashInstancias = new HashInstancias();
        hashInstancias.inicializarMapInstancias(ruta_carpeta);
        ArrayList<Instancia> instancias = hashInstancias.getInstancias();
        Iterator<Instancia> iterator = instancias.iterator();
        Excel excel = new Excel();
    int i = 0;
       excel.comprobarExcel("VNS", "VNS");
        while((iterator.hasNext())){
            Instancia next = iterator.next();
            Grasp grasp = new Grasp();
            Constructive constructive = new Constructive();
            Solucion solucion = constructive.generarSolucionInicial(next);
            long startTime = System.nanoTime()*100000000;;
            grasp.BVNS(solucion,500, 15);
            long endTime = System.nanoTime()- startTime ;
            next.setFuncion_objetivo(grasp.getFuncionObjetivo());
            next.setTiempo(endTime);
            excel.insertarInstancia(next);

        }
        excel.hallarPromedio();
        excel.cerrarExcel();

/*        String prueba = "hola";
        String prueba2 = "h";
*//*
        char[] tempCharArray = prueba.toCharArray();
        tempCharArray[4] = '1';
        prueba = String.valueOf(tempCharArray);
*//*
        int a = 5;
        String s = String.valueOf(a);
        char[] tempCharArray = prueba.toCharArray();
        tempCharArray[0] = s.toCharArray()[0]; // Donde 'x' es la posición que buscas.
        prueba = String.valueOf(tempCharArray);

        System.out.println(prueba);*/


        System.exit(0);


    }
}
