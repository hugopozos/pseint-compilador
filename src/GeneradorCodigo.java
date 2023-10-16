package src;

import java.util.ArrayList;

public class GeneradorCodigo {

    public static int tmpActual=0;
    public static int lbActual=0;

    public static ArrayList<String>representacionIntermedia = new ArrayList();
    public static ArrayList<String> temporales = new ArrayList();

    public static String obtenerUltimoTemporal(){
        return temporales.get(temporales.size() - 1);
    }

    public static String operadoresAritmeticos(String ex1, String ex2, String op){
        tmpActual++;
        String tmp = "tmp"+tmpActual;
        temporales.add(tmp);
        String aux = tmp + ":=" + ex1 + op + ex2;
        //Almacenarlo en tabla identificadores
        representacionIntermedia.add(aux + "\n");
        return tmp;
    }
    public static void asignacion(String s, String e){
        String aux= s +":="+e;
        representacionIntermedia.add(aux);
    }
}
