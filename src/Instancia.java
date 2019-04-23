import java.io.*;

import java.util.HashMap;
import java.util.*;


public class Instancia {

    private HashMap<Integer, HashMap<Integer, Float>> vertices;
    private float funcion_objetivo;
    private float tiempo;
    private String nombre_instancia;
    private Integer num_vertices;
    private Integer semillaRandom;
    private ArrayList<Integer> verticesRecorrer;
    private HashMap<Integer, Integer> posicionVertices;
    private int i = 0;


    public Instancia() {
        this.vertices = new HashMap<>();
        this.funcion_objetivo = -1;
        this.tiempo = -1;
        this.nombre_instancia = "";
        this.num_vertices = -1;
        this.semillaRandom = 1234;
        this.verticesRecorrer = new ArrayList<>();
        this.posicionVertices = new HashMap<>();
        this.i = 0;
    }

    public Integer getSemillaRandom() {
        return semillaRandom;
    }

    public void setSemillaRandom(Integer semillaRandom) {
        this.semillaRandom = semillaRandom;
    }

    public Integer getNum_vertices() {
        return this.num_vertices;
    }

    public Integer getNum_verticestotales(){
        return this.vertices.size();
    }

    public void setNum_vertices(Integer num_vertices) {
        this.num_vertices = num_vertices;
    }

    public float getFuncion_objetivo() {
        return funcion_objetivo;
    }

    public void setFuncion_objetivo(float funcion_objetivo) {
        this.funcion_objetivo = funcion_objetivo;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public String getNombre_instancia() {
        return nombre_instancia;
    }

    public void setNombre_instancia(String nombre_instancia) {
        this.nombre_instancia = nombre_instancia;
    }

    public ArrayList<Integer> getAristasOrdenadas(Integer a, Set<Integer> nodos_visitados) {
        ArrayList<Integer> integers = new ArrayList<>();
        HashMap<Integer, Float> integerFloatHashMap = vertices.get(a);
        Iterator<Integer> iterator = integerFloatHashMap.keySet().iterator();
        int i = 0;
        float max = 0;
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if (!nodos_visitados.contains(next)){
                if (i!=0){
                    if (getDistancia(a,next)>max) {
                        integers.add(0, next);
                    }else {
                        integers.add(i,next);
                    }
                }else{
                    integers.add(i,next);
                    max = getDistancia(a,next);
                }
            }

        }

        return integers;
    }

    public ArrayList<Integer> getAristas(int a){
        ArrayList<Integer> aristas = new ArrayList<>();
        Iterator<Integer> iterator = this.vertices.get(a).keySet().iterator();
        while(iterator.hasNext()){
            aristas.add(iterator.next());
        }
        return aristas;
    }

    public ArrayList<Integer> getVertices() {

        return new ArrayList<>(this.verticesRecorrer);
    }

    public float getDistancia(Integer a, Integer b) {
        if(a.equals(b)){
            return -1000f;
        }else{
            try{
                HashMap<Integer, Float> integerFloatHashMap = this.vertices.get(a);
                return integerFloatHashMap.get(b);
            }catch (Exception e){
                System.out.println("nodo1: "+a+" nodo2: " + b+ " nombre_instancia: " +nombre_instancia);
            }

        }
        return 1;
    }

    public void setVertice(Integer a) {
            HashMap<Integer, Float> integerFloatHashMap = new HashMap<>();
            if (this.vertices.get(a) == null){
                this.vertices.put(a, integerFloatHashMap);
                this.verticesRecorrer.add(i,a);
                this.posicionVertices.put(a,i);
                i++;
            }


        }

        public void setArco (Integer a, Integer b, Float distancia){
            HashMap<Integer, Float> integerFloatHashMap = this.vertices.get(a);
            if (integerFloatHashMap == null) {
                integerFloatHashMap = new HashMap<>();
            }
            integerFloatHashMap.put(b, distancia);
            this.vertices.put(a, integerFloatHashMap);
        }


    }


