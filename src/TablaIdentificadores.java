package src;

import java.util.ArrayList;
import java.util.HashMap;

/*
* En esta tabla se va a ir guardando los identificadores que se iran declarando con "Definir"
* */
public class TablaIdentificadores {
    private static HashMap<String,String> tabla = new HashMap();

    private static ArrayList<Integer> enterosTipos = new ArrayList();
    private static ArrayList<Integer> flotantesTipos = new ArrayList();

    private static ArrayList<Integer> booleanosTipos = new ArrayList();

    private static ArrayList<Integer> cadenaTipos = new ArrayList();

    //En este metodo vamos agregando los identificadores
    public static void insertarIdentificadores(Token token, String tipo){tabla.put(token.image,tipo);}

    public static void inicializarTipos(){
        //Guardamos el kind que hace referencia al dato primitivo de entero y el propio kind de entero
        enterosTipos.add(12);
        enterosTipos.add(3);

        //Asi se hace con el resto de valores
        flotantesTipos.add(13);
        flotantesTipos.add(4);

        booleanosTipos.add(14);
        booleanosTipos.add(15);
        booleanosTipos.add(7);

        cadenaTipos.add(10);
        cadenaTipos.add(5);

    }

    //Imprimir valores de la tabla
    public static void mostrarTabla(){
        for(String i:tabla.keySet()){
            System.out.println("Token:" + i + " tipo:" + tabla.get(i));
        }
    }


    //Verificamos la existencia del token en nuestra tabla
    public static boolean checkExistenciaId(Token token){

        return tabla.containsKey(token.image);
    }
    //Obtenemos el tipo de dato del token a traves de la tabla identificadores
    public static String obtenerTipo(Token t){
        return tabla.get(t.image);
    }
    private static int obtenerTipoKind(Token t){
        String tipoString = obtenerTipo(t);
        int kind = 0;
        switch (tipoString.toLowerCase()){
            case "entero": kind = 3;
            break;
            case "flotante": kind = 4;
            break;
            case "booleano": kind = 7;
            break;
            case "cadena": kind = 5;
            break;
        }
        return kind;
    }

    //COMPROBACION DE TIPOS
    //ENTRE VARIABLE Y UN TERMINAL
    /*
    * Token identificador: hace referencia a la variable
    * Token asignado: hace referencia al terminal
    * */
    public static boolean verifiacionConToken(Token identificador, Token asignado){
        //Obtenemos el tipo de identificador en la tabla

        //System.out.println("kind de asignado:"+ asignado.kind);
        //System.out.println("Identificador:" + identificador);


            String tipo = tabla.get(identificador.image).toLowerCase();


            //System.out.println("tipo de indentificador:" + tipo);
            int kind = asignado.kind;

            //Si el kind que asignado es 58 quiere decir que es identificador
            //Por lo que debemos de obtener el kind de su tipo de dato (entero,flotante,booleano,etc)
            if(kind == 58){
                kind = obtenerTipoKind(asignado);
            }

            //Si mi tipo del identificador es entero, comprobamos que el token asignado sea igual de tipo entero o relacionado
            if(tipo.equals("entero") && enterosTipos.contains(kind)){
                return true;
                //Si mi tipo del identificador es cadena, comprobamos que el token asignado sea igual de tipo cdena
            }else if(tipo.equals("cadena") && cadenaTipos.contains(kind)){
                return true;
            }else if(tipo.equals("booleano") && booleanosTipos.contains(kind)){
                return true;
            }else if(tipo.equals("flotante") && flotantesTipos.contains(kind)) {
                return true;
            }
            else {
                return false;
            }
    }
}
