import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class HashInstancias {


    private HashMap<Integer, Instancia> lista_instancias;

    private int a;

    public void inicializarMapInstancias(String ruta_carpeta) {
        lista_instancias = new HashMap<>();
        File carpeta = null;
        int i = 0;
        a=0;

        try {
            carpeta = new File(ruta_carpeta);

            for (final File ficheroEntrada : carpeta.listFiles()) {
                if (ficheroEntrada.isDirectory()) {
                    String name = ficheroEntrada.getName();
                    //inicializarMapInstancias(name);
                } else {
                    Instancia instancia = inicializarInstancia(ruta_carpeta + "\\" + ficheroEntrada.getName(), ficheroEntrada.getName());
                    lista_instancias.put(a,instancia);
                    a++;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Instancia inicializarInstancia(String ruta, String nombre_instancia){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        int v,v2,aux;
        float distancia;
        aux = -1;
        Instancia instancia = new Instancia();
        instancia.setNombre_instancia(nombre_instancia);
        try {
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);


            String linea = br.readLine();
            while((linea=br.readLine())!=null){
                String []  bar = linea.split(" ");

                v = Integer.parseInt(bar[0]);
                v2 = Integer.parseInt(bar[1]);
                distancia = Float.parseFloat(bar[2]);


                if (v != aux){
                    instancia.setVertice(v);
                }

                aux = v;
                instancia.setArco(v,v2,distancia);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }


        }
        
        return instancia;
    
    }

    public Collection<Instancia> getInstancias (){
        return  lista_instancias.values();
    }


}
