import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Excel {



    private String nombreArchivo;
    private FileOutputStream salida;
    private Workbook workbook;

    public Excel(){
        workbook = new XSSFWorkbook();

    }

    public void comprobarExcel(String hoja, String algoritmo) {
        nombreArchivo = "tiempos-" + algoritmo + ".xlsx";
        File archivo = new File(nombreArchivo);
        try {
            if (archivo.exists()) {
                archivo.delete();
                archivo.createNewFile();
                crearExcel(hoja);
            }else{
                archivo.createNewFile();
                crearExcel(hoja);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

            }

    private void crearExcel(String hoja) {

        workbook.createSheet(hoja);

        try {

            salida = new FileOutputStream(nombreArchivo);

            workbook.write(salida);
            anadirCabeceras();


            System.out.println("Archivo creado existosamente en {0}");

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no localizable en sistema de archivos");
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida");
        }
    }

    public void insertarInstancia(Instancia ins){
        try{
            FileInputStream fisNew = new FileInputStream(nombreArchivo);
            workbook = WorkbookFactory.create(fisNew);
            int i = 0;
            Sheet sheetAt = workbook.getSheetAt(workbook.getActiveSheetIndex());
            int lastRowNum = sheetAt.getLastRowNum()+1;
            anadirValores(sheetAt, ins.getNombre_instancia(), Float.toString(ins.getFuncion_objetivo()), Float.toString(ins.getTiempo()), lastRowNum);

            salida = new FileOutputStream(nombreArchivo);
            workbook.write(salida);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void anadirValores(Sheet sheetAt, String primero, String segundo, String tercero, int columna){
       Row a = null;
       int i = 0;
        while(i<3){
            if (sheetAt.getRow(columna) == null){
                a = sheetAt.createRow(columna);
            }

            Cell cell = a.createCell(i);
            switch (i){
                case 0 :
                    cell.setCellValue(primero);
                    break;
                case 1 :
                    cell.setCellValue(segundo);
                    break;
                case 2:
                    cell.setCellValue(tercero);
                    break;
            }

            i++;
        }
    }




    private void anadirCabeceras(){
        try{
            int i = 0;
            FileInputStream fisNew = new FileInputStream(nombreArchivo);
            workbook = WorkbookFactory.create(fisNew);
            Sheet sheetAt = workbook.getSheetAt(workbook.getActiveSheetIndex());

            anadirValores(sheetAt, "Nombre de la instancia", "Funcion Objetivo", "Tiempo", 0);

            salida = new FileOutputStream(nombreArchivo);
            workbook.write(salida);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void cerrarExcel(){
        try{
            workbook.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void hallarPromedio() {
        try{
            float tiempo_promedio = 0;
            float distancia_promedio = 0;
            FileInputStream fisNew = new FileInputStream(nombreArchivo);
            workbook = WorkbookFactory.create(fisNew);
            Sheet sheetAt = workbook.getSheetAt(workbook.getActiveSheetIndex());

            Iterator<Row> rowIterator = sheetAt.iterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                Row next = rowIterator.next();
                distancia_promedio = Float.parseFloat(next.getCell(1).getStringCellValue()) + distancia_promedio;
                tiempo_promedio = Float.parseFloat(next.getCell(2).getStringCellValue()) + tiempo_promedio;

            }

            int lastRowNum = sheetAt.getLastRowNum() + 1;
            Row row = sheetAt.createRow(lastRowNum);
            int celda = 0;
            Cell cell = null;
            while(celda < 3){
                switch (celda){
                    case 0:
                        row.createCell(celda);
                        cell = row.getCell(celda);
                        cell.setCellValue("Promedio");
                        break;
                    case 1:
                        row.createCell(celda);
                        cell = row.getCell(celda);
                        cell.setCellValue(distancia_promedio/(sheetAt.getLastRowNum()-1));
                        break;
                    case 2:
                        row.createCell(celda);
                        cell = row.getCell(celda);
                        cell.setCellValue(tiempo_promedio/(sheetAt.getLastRowNum()-1));
                        break;
                }
                celda++;
            }

            salida = new FileOutputStream(nombreArchivo);
            workbook.write(salida);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}



