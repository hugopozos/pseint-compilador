package src;

import java.util.ArrayList;
import java.util.HashMap;

/*
* En esta tabla se va a ir guardando los identificadores que se iran declarando con "Definir"
* */
public class TablaIdentificadores {

    // En este HashMap vamos a ir guardado los identificadores(String) y su tipo (String)
    //Un HashMap se compone de una llave(token) y un valor(tipo de dato)
    private static HashMap<String,String> tabla = new HashMap();

    private static ArrayList<Integer> enterosTipos = new ArrayList();
    private static ArrayList<Integer> flotantesTipos = new ArrayList();

    private static ArrayList<Integer> booleanosTipos = new ArrayList();

    private static ArrayList<Integer> cadenaTipos = new ArrayList();

    //En este metodo vamos agregando los identificadores

    //Modificamos
    public static void insertarIdentificadores(String token, String tipo){tabla.put(token,tipo);}

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
    public static boolean checkExistenciaId(String token){

        return tabla.containsKey(token);
    }
    //Obtenemos el tipo de dato del token a traves de la tabla identificadores
    public static String obtenerTipoidentificador(String t){
        return tabla.get(t);
    }


    private static int obtenerTipoKindIdentificador(String t){
        String tipoString = obtenerTipoidentificador(t);
        int kind = 0;
        String tmp = (tipoString != null)  ? tipoString.toLowerCase() : "nulo";
        switch (tipoString.toLowerCase()){
            case "nulo": kind = -1;
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

    private static int obtenerkindAsignado(String t){
        int kind =0;
        //Utilizar expresiones regulares
        if(t.matches("\\d+")){
            //System.out.println("Es un entero");
            kind = 3;
        }else if(t.matches("falso") || t.matches("verdadero")){
            //System.out.println("Es un booleano");
            kind = 7;
        }else if(t.matches("\\d+[.]\\d+")){
            //System.out.println("Es un flotante");
            kind = 4;
        }else{
            //System.out.println("Es una cadena");
            kind = 5;
        }

        return kind;
    }

    //COMPROBACION DE TIPOS
    //ENTRE VARIABLE Y UN TERMINAL
    /*
    * Token identificador: hace referencia a la variable
    * Token asignado: hace referencia al terminal
    * */
    public static boolean verifiacionConToken(String identificador, String asignado){
        //Obtenemos el tipo de identificador en la tabla

        //System.out.println("kind de asignado:"+ asignado.kind);
        //System.out.println("Identificador:" + identificador);


            //Obtenemos el tipo de nuestro token identificador a partir de la tabla de simbolos
            String tipo = tabla.get(identificador).toLowerCase();
            int kind;

            //Evaluamos si asignado no es una variable
            //En dado caso que lo sea, obtenemos su tipo asociado
            if(obtenerTipoidentificador(asignado) == null){
                kind = obtenerkindAsignado(asignado);
            }else{
                //Si es un valor primitivo, aplicamos el metodo obtenerkindAsignado
                kind = obtenerTipoKindIdentificador(asignado);
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
