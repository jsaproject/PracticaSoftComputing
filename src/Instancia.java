import java.io.*;

import java.util.HashMap;
import java.util.*;


public class Instancia {

    HashMap<Integer, HashMap<Integer, Float>> vertices;
    float funcion_objetivo;
    float tiempo;
    String nombre_instancia;
    Integer num_vertices;

    public Instancia() {
        this.vertices = new HashMap<>();
        this.funcion_objetivo = -1;
        this.tiempo = -1;
        this.nombre_instancia = "";
        this.num_vertices = -1;
    }

    public Integer getNum_vertices() {
        return num_vertices;
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

    public ArrayList<Integer> getAristasOrdenadas(Integer a) {
        ArrayList<Integer> integers = new ArrayList<>();
        HashMap<Integer, Float> integerFloatHashMap = vertices.get(a);
        Iterator<Integer> iterator = integerFloatHashMap.keySet().iterator();
        int i = 0;
        float max = 0;
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if (i!=0){
                if (getDistancia(a,next)>max){
                    integers.add(0,next);
                    integers.add(i,next);
                }
            }else{
                integers.add(i,next);
                max = getDistancia(a,next);
            }
        }

        return integers;
    }

    public Set<Integer> getVertices() {
        return vertices.keySet();
    }

    public float getDistancia(Integer a, Integer b) {
        HashMap<Integer, Float> integerFloatHashMap = vertices.get(a);
        return integerFloatHashMap.get(b);
    }

    public void setVertice(Integer a) {
            HashMap<Integer, Float> integerFloatHashMap = new HashMap<>();
            vertices.put(a, integerFloatHashMap);

        }

        public void setArco (Integer a, Integer b, Float distancia){
            HashMap<Integer, Float> integerFloatHashMap = vertices.get(a);
            if (integerFloatHashMap == null) {
                integerFloatHashMap = new HashMap<>();
            }
            integerFloatHashMap.put(b, distancia);
            vertices.put(a, integerFloatHashMap);
        }


    }


