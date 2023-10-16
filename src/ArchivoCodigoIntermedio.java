package src;

import java.io.*;
import java.util.ArrayList;

public class ArchivoCodigoIntermedio {

    private File archivoCodigoIntermedio;

    //Creamos el archivo donde se va a guardar el codigo intermedio
    public ArchivoCodigoIntermedio(String nombreArchivo){
        archivoCodigoIntermedio = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivoCodigoIntermedio);
            salida.close();
            System.out.println("Se creo el archivo");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        }
    }

    //Escribe en el archivo los string de codigo 3 direcciones
    public void escribirArchivo(ArrayList<String> codigoIntermedio){
        try{
            PrintWriter salida = new PrintWriter(archivoCodigoIntermedio);
            for (String renglon:codigoIntermedio) {
                salida.print(renglon);
            }
            salida.close();
            System.out.println("Se termino de escribir en el archivo");
        }catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");

        }
    }

}
