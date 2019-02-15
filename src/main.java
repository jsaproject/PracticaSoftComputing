
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

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
