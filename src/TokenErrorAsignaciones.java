package src;

import java.io.PrintStream;
import java.util.Hashtable;
import java.lang.String;
import java.util.ArrayList;
public class TokenErrorAsignaciones
{
    /*
    |--------------------------------------------------------------------------
    | Hashtable el cual almacenara los tokens de entrada declarados.
    |--------------------------------------------------------------------------
    */
    private static Hashtable tabla = new Hashtable();

    /*
    |--------------------------------------------------------------------------
    | Listas que guardan los tipos compatibles de las variables
    |--------------------------------------------------------------------------
    */
    private static ArrayList<Integer> enteroAL = new ArrayList();
    private static ArrayList<Integer> flotanteAL = new ArrayList();
    private static ArrayList<Integer> cadenaAL = new ArrayList();
    private static ArrayList<Integer> caracterAL = new ArrayList();
    private static ArrayList<Integer> booleanoAL = new ArrayList();

    /*
    |-------------------------------------------------------------------------------------------------------------
    | Método que agrega a la tabla de tokens el identificador que esta siendo declarado junto con su tipo de dato
    |-------------------------------------------------------------------------------------------------------------
    |
    | Token identificador -> variable
    | Int tipo -> tipo de dato
    | X: 20
    */
    public static void insertarSimbolo(Token identificador, int tipo)
    {
        tabla.put(identificador.image, tipo);
    }

    /*
    |-------------------------------------------------------------------------------------------------
    | Método que inicializa las tablas en las cuales se almacenaran los tipo de datos compatibles
    |-------------------------------------------------------------------------------------------------
    */
    public static void insertarTablas()
    {

        enteroAL.add(3);
        enteroAL.add(12);

        flotanteAL.add(4);
        flotanteAL.add(13);

        cadenaAL.add(10);
        caracterAL.add(11);

        booleanoAL.add(7);
        booleanoAL.add(14);
        booleanoAL.add(15);
    }

    /*
    |----------------------------------------------------------------------------------------------------------------
    | Método que se encarga de verificar si una asignación entre variables es válida en función de sus tipos de datos
    |----------------------------------------------------------------------------------------------------------------
    */
    public static String verificarAsignacion(Token TokenIzq, Token TokenAsig)
    {
        /* Almacenan el tipo de dato del identificador y de las asignaciones */
        int tipoIdentificador1;
        int tipoIdentificador2;

        /* Obtenemos el tipo de dato del identificador, asi como, si el token enviado no se declara como los tipos
            de datos asignados en tablas. */
        if(TokenIzq.kind != 44 && TokenIzq.kind != 45){
            try{
                /* Si TokenIzq.image existe dentro de tabla, entonces tipoIdentificador1 toma el tipo de dato con el
                    que fue declarado */
                tipoIdentificador1 = (Integer)tabla.get(TokenIzq.image);
            }catch(Exception e){
                return "Error: El identificador " + TokenIzq.image + " No ha sido declarado \r\nLinea: " + TokenIzq.beginLine;
            }
        }else {
            tipoIdentificador1 = 0;
        }

        /* Obtenemos el tipo de dato que se pueda estar asignando y se obtiene su tipo de la tabla de tokens
            para poder hacer las comparaciones */
        if(TokenAsig.kind == 49){
            try{
                tipoIdentificador2 = (Integer)tabla.get(TokenAsig.image);
            }catch(Exception e){
                return "Error: El identificador " + TokenAsig.image + " No ha sido declarado \r\nLinea: " + TokenIzq.beginLine;
            }
        /* Si el dato pertenece dentro de la tabla de tokens entonces
            tipoIdentificador2 = tipo_del_dato */
        }else if(TokenAsig.kind == 48 || TokenAsig.kind == 50 || TokenAsig.kind == 51 || TokenAsig.kind == 52){
            tipoIdentificador2 = TokenAsig.kind;
        }else{
            /* Si no, se inicializa en un valor sin significado respecto a los tokens declarados para no marcar error
                al comparar */
            tipoIdentificador2 = 0;
        }

        /*----------------------------------------
        | Enteros                                |
        |----------------------------------------|
        | Si la lista contiene el valor de tipoIdentificador2, entonces es compatible y se puede hacer la asignación
        */
        if(tipoIdentificador1 == 44){
            if(enteroAL.contains(tipoIdentificador2)){
                return " ";
            }else{
                return "Error: No se puede convertir " + TokenAsig.image + " a Entero \r\nLinea: " + TokenIzq.beginLine;
            }
        }else if(tipoIdentificador1 == 45){ /* Flotantes */
            if(flotanteAL.contains(tipoIdentificador2)){
                return " ";
            }else {
                return "Error: No se puede convertir " + TokenAsig.image + " a Flotante \r\nLinea: " + TokenIzq.beginLine;
            }
        }else if(tipoIdentificador1 == 46){ /* Cadena */
            if(cadenaAL.contains(tipoIdentificador2)){
                return " ";
            }else {
                return "Error: No se puede convertir " + TokenAsig.image + " a Cadena \r\nLinea: " + TokenIzq.beginLine;
            }
        }else if(tipoIdentificador1 == 47){ /* Caracter */
            if(caracterAL.contains(tipoIdentificador2)){
                return " ";
            }else {
                return "Error: No se puede convertir " + TokenAsig.image + " a Caracter \r\nLinea: " + TokenIzq.beginLine;
            }
        }else if(tipoIdentificador1 == 48){ /* Booleanos */
            if(booleanoAL.contains(tipoIdentificador2)){
                return " ";
            }else {
                return "Error: No se puede convertir " + TokenAsig.image + " a Booleano \r\nLinea: " + TokenIzq.beginLine;
            }
        }else{
            return "El Identificador " + TokenIzq.image + " no ha sido declarado" + " Linea: " + TokenIzq.beginLine;
        }
    }

    /*
    |-------------------------------------------------------------------------------------------------
    | Método que verifica si un identificador no ha sido declarado ej: i++, i--
    |-------------------------------------------------------------------------------------------------
    */
    public static String verificarVariable(Token checkToken)
    {
        try{
            /* Intenta obtener el token a verificar(checkToken) de la tabla de los tokens */
            int tipoIdentificador1 = (Integer)tabla.get(checkToken.image);
            return " ";
        }catch(Exception e){
            return "Error: El identificador " + checkToken.image + " No ha sido declarado \r\nLinea: " + checkToken.beginLine;
        }
    }

}
