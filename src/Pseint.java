/* Generated By:JavaCC: Do not edit this line. Pseint.java */
package src;
import java.util.ArrayList;
import src.TablaIdentificadores;
public class Pseint implements PseintConstants {

        static ArrayList<String> tabla = PseintTokenManager.tabla;
        public static void main(String[] args) {
            try{
                Pseint pseint = new Pseint(System.in);
                pseint.programa();

                if(tabla.size() != 0){
                    System.out.println("ERRORES PROGRAMA");
                    System.out.println("=================");
                    for(String i:tabla){
                        System.out.println(i);
                    }
                }else{
                    System.out.println("\nCompilacion completada con exito");

                }
              }catch(Exception e){
                System.out.println(e.getMessage());
              }
          }

// Gramaticas

//CAMBIO: Nuestro programa puede no tener sentencias entre Algoritmo y FinAlgoritmo
  final public void programa() throws ParseException {
    try {
      jj_consume_token(INICIO);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LEER:
        case ESCRIBIR:
        case DEFINIR:
        case INICIO_CICLO_PARA:
        case INICIO_CICLO_MIENTRAS:
        case INICIO_CICLO_REPETIR:
        case INICIO_CONDICIONAL_SI:
        case INICIO_CONDICIONAL_SEGUN:
        case VARIABLE:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        sentencias();
      }
      jj_consume_token(FIN);
                TablaIdentificadores.mostrarTabla();
    } catch (ParseException e) {
            Token t;
            do{
                t=getNextToken();
            }while(t.kind != EOF);
            tabla.add("Parser Error:" + e.getMessage());
    }
  }

  final public void sentencias() throws ParseException {
    TablaIdentificadores.inicializarTipos();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VARIABLE:
      sentenciaAsignacion();
      break;
    case DEFINIR:
      sentenciaDeclaracionVariables();
      break;
    case LEER:
      leerDato();
      break;
    case ESCRIBIR:
      imprimirDato();
      break;
    case INICIO_CONDICIONAL_SI:
      sentenciaSi();
      break;
    case INICIO_CONDICIONAL_SEGUN:
      sentenciaSegun();
      break;
    case INICIO_CICLO_PARA:
      sentenciaPara();
      break;
    case INICIO_CICLO_REPETIR:
      sentenciaRepetir();
      break;
    case INICIO_CICLO_MIENTRAS:
      sentenciaMientras();
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//ASIGNACION DE VALOR
//Aqui hay un problema
  final public void sentenciaAsignacion() throws ParseException {
    Token identificador = new Token();
    Token asignado = new Token();
    try {
      jj_consume_token(VARIABLE);
                    //EXISTENCIA DE IDENTIFICADORES
                    // Sino existe el id, lo guardamos en nuestra arreglo de errores
                     if(!TablaIdentificadores.checkExistenciaId(token)){
                           tabla.add("The indentifier: " + token.image + " doesn't exist, at line:" +
                                   token.beginLine + " column:" + token.beginColumn);
                     }else{
                        identificador = token;
                     }
      asignado = asignacion();
                            //Se evalua si se esta asignando el tipo correcto al identificador
                       //System.out.println("identificador: " + identificador.image);
                       //System.out.println("asignado: " + asignado.image);
                      if(!TablaIdentificadores.verifiacionConToken(identificador,asignado)){
                                tabla.add("The token: " + asignado.image + " doesn't correspond to the:" +
                                            TablaIdentificadores.obtenerTipo(identificador) + " type");
                          }
      jj_consume_token(DELIMITADOR);
    } catch (ParseException e) {
        //System.out.println(e.toString());
        Token t;
        do{
            t=getNextToken();
        }while(t.kind != DELIMITADOR);
        tabla.add("Parser error:" + e.getMessage());
    }
  }

//Probar manejo de errores
  final public void sentenciaDeclaracionVariables() throws ParseException {
    try {
      declaracionVariables();
      jj_consume_token(DELIMITADOR);
    } catch (ParseException e) {
            //System.out.println(e.toString());
            Token t;
            do{
                t=getNextToken();
                //VERIFICAR ERROR DE PUNTO Y COMA AL FINAL
            }while(t.kind != DELIMITADOR);
            tabla.add("Parser error:" + e.getMessage());
    }
  }

//CAMBIAMOS el tipo de dato retornado a token
  final public Token constantes() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMERO_ENTERO:
      jj_consume_token(NUMERO_ENTERO);
                     {if (true) return token;}
      break;
    case NUMERO_DECIMAL:
      jj_consume_token(NUMERO_DECIMAL);
                         {if (true) return token;}
      break;
    case CADENA_TEXTO:
      jj_consume_token(CADENA_TEXTO);
                      {if (true) return token;}
      break;
    case CARACTER_TEXTO:
      jj_consume_token(CARACTER_TEXTO);
                        {if (true) return token;}
      break;
    case BOOLEANO_FALSO:
      jj_consume_token(BOOLEANO_FALSO);
                        {if (true) return token;}
      break;
    case BOOLEANO_VERDADERO:
      jj_consume_token(BOOLEANO_VERDADERO);
                            {if (true) return token;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//ASIGNACION CORRECTA DE VALORES
  final public Token asignacion() throws ParseException {
                    Token asignado = new Token();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASIGNACION:
      jj_consume_token(ASIGNACION);
      break;
    case ASIGNACION_COMPUESTA:
      jj_consume_token(ASIGNACION_COMPUESTA);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    asignado = condicion();
                                                                    {if (true) return asignado;}
    throw new Error("Missing return statement in function");
  }

  final public Token condicion() throws ParseException {
                   Token asignado = new Token();
    asignado = operacion();
                          {if (true) return asignado;}
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPERADOR_IGUAL:
      case OPERADOR_DIFERENTE:
      case OPERADOR_MAYOR:
      case OPERADOR_MENOR:
      case OPERADOR_MAYOR_IGUAL:
      case OPERADOR_MENOR_IGUAL:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      operadoresRelacionales();
      operacion();
    }
    throw new Error("Missing return statement in function");
  }

  final public Token operacion() throws ParseException {
    Token t = new Token();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOGICO_NOT:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      jj_consume_token(LOGICO_NOT);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VARIABLE:
      jj_consume_token(VARIABLE);
                                     //EXISTENCIA DE IDENTIFICADORES
                                    // Sino existe el id, lo guardamos en nuestra arreglo de errores
                                    if(!TablaIdentificadores.checkExistenciaId(token)){
                                        tabla.add("The indentifier: " + token.image + " doesn't exist, at line:" +
                                                    token.beginLine + " column:" + token.beginColumn);
                                    }else{
                                        t=token;
                                    }
      break;
    case CADENA_TEXTO:
    case CARACTER_TEXTO:
    case NUMERO_ENTERO:
    case NUMERO_DECIMAL:
    case BOOLEANO_FALSO:
    case BOOLEANO_VERDADERO:
      t = constantes();
      break;
    case PAREN_ABIERTO:
      operacionParentesis();
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUMA:
      case RESTA:
      case MULTIPLICACION:
      case DIVISION:
      case MODULO:
      case LOGICO_AND:
      case LOGICO_OR:
      case OPERADOR_IGUAL:
      case OPERADOR_DIFERENTE:
      case OPERADOR_MAYOR:
      case OPERADOR_MENOR:
      case OPERADOR_MAYOR_IGUAL:
      case OPERADOR_MENOR_IGUAL:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      operadores();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LOGICO_NOT:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_5;
        }
        jj_consume_token(LOGICO_NOT);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIABLE:
        jj_consume_token(VARIABLE);
                                                     //EXISTENCIA DE IDENTIFICADORES
                                                     // Sino existe el id, lo guardamos en nuestra arreglo de errores
                                                        if(!TablaIdentificadores.checkExistenciaId(token)){
                                                            tabla.add("The indentifier: " + token.image + " doesn't exist, at line:" +
                                                            token.beginLine + " column:" + token.beginColumn);
                                                        }
        break;
      case CADENA_TEXTO:
      case CARACTER_TEXTO:
      case NUMERO_ENTERO:
      case NUMERO_DECIMAL:
      case BOOLEANO_FALSO:
      case BOOLEANO_VERDADERO:
        t = constantes();
        break;
      case PAREN_ABIERTO:
        operacionParentesis();
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  final public void operacionParentesis() throws ParseException {
    jj_consume_token(PAREN_ABIERTO);
    operacion();
    jj_consume_token(PAREN_CERRADO);
  }

  final public void operadores() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUMA:
    case RESTA:
    case MULTIPLICACION:
    case DIVISION:
    case MODULO:
      operadoresAritmeticos();
      break;
    case LOGICO_AND:
    case LOGICO_OR:
      operadoresLogicos();
      break;
    case OPERADOR_IGUAL:
    case OPERADOR_DIFERENTE:
    case OPERADOR_MAYOR:
    case OPERADOR_MENOR:
    case OPERADOR_MAYOR_IGUAL:
    case OPERADOR_MENOR_IGUAL:
      operadoresRelacionales();
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void operadoresAritmeticos() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUMA:
      jj_consume_token(SUMA);
      break;
    case RESTA:
      jj_consume_token(RESTA);
      break;
    case MULTIPLICACION:
      jj_consume_token(MULTIPLICACION);
      break;
    case DIVISION:
      jj_consume_token(DIVISION);
      break;
    case MODULO:
      jj_consume_token(MODULO);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void operadoresRelacionales() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPERADOR_IGUAL:
      jj_consume_token(OPERADOR_IGUAL);
      break;
    case OPERADOR_DIFERENTE:
      jj_consume_token(OPERADOR_DIFERENTE);
      break;
    case OPERADOR_MAYOR:
      jj_consume_token(OPERADOR_MAYOR);
      break;
    case OPERADOR_MAYOR_IGUAL:
      jj_consume_token(OPERADOR_MAYOR_IGUAL);
      break;
    case OPERADOR_MENOR:
      jj_consume_token(OPERADOR_MENOR);
      break;
    case OPERADOR_MENOR_IGUAL:
      jj_consume_token(OPERADOR_MENOR_IGUAL);
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void operadoresLogicos() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOGICO_AND:
      jj_consume_token(LOGICO_AND);
      break;
    case LOGICO_OR:
      jj_consume_token(LOGICO_OR);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//DECLARACION DE VARIABLES:COMPROBACION DE TIPOS
  final public void declaracionVariables() throws ParseException {
    Token identificador = new Token();
    Token asignado = new Token();
    String tipoDato = "";
    jj_consume_token(DEFINIR);
    tipoDato = tiposDato();
    jj_consume_token(VARIABLE);
                        if(TablaIdentificadores.checkExistenciaId(token)){
                            System.out.println("Ya existe el token: " + token.image);
                            tabla.add("The identifier: " + token.image + " already exist, at line: " + token.beginLine + " column:" + token.endColumn);
                        }else{
                            TablaIdentificadores.insertarIdentificadores(token,tipoDato);
                            identificador = token;
                        }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASIGNACION:
    case ASIGNACION_COMPUESTA:
      asignado = asignacion();
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
                               //Se evavlua si se esta asignando el tipo correcto al identificador
                    if(asignado.kind != 0){
                              System.out.println("identificador: " + identificador.image);
                              System.out.println("asignado: " + asignado.image);
                                  if(!TablaIdentificadores.verifiacionConToken(identificador,asignado)){
                                      tabla.add("The token: " + asignado.image + " doesn't correspond to the:" +
                                        TablaIdentificadores.obtenerTipo(identificador) + " type");
                                  }
                          }
  }

  final public String tiposDato() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTERO:
      jj_consume_token(ENTERO);
              {if (true) return token.image;}
      break;
    case FLOTANTE:
      jj_consume_token(FLOTANTE);
                  {if (true) return token.image;}
      break;
    case CADENA:
      jj_consume_token(CADENA);
                {if (true) return token.image;}
      break;
    case CARACTER:
      jj_consume_token(CARACTER);
                 {if (true) return token.image;}
      break;
    case BOOLEANO:
      jj_consume_token(BOOLEANO);
                  {if (true) return token.image;}
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public void leerDato() throws ParseException {
    jj_consume_token(LEER);
    jj_consume_token(VARIABLE);
    jj_consume_token(DELIMITADOR);
  }

  final public void imprimirDato() throws ParseException {
    if (jj_2_1(3)) {
      jj_consume_token(ESCRIBIR);
      jj_consume_token(CADENA_TEXTO);
      jj_consume_token(DELIMITADOR);
    } else if (jj_2_2(3)) {
      jj_consume_token(ESCRIBIR);
      jj_consume_token(CADENA_TEXTO);
      jj_consume_token(COMA);
      jj_consume_token(VARIABLE);
      jj_consume_token(DELIMITADOR);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ESCRIBIR:
        jj_consume_token(ESCRIBIR);
        jj_consume_token(VARIABLE);
        jj_consume_token(DELIMITADOR);
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void sentenciaSi() throws ParseException {
    try {
      jj_consume_token(INICIO_CONDICIONAL_SI);
      condicion();
      jj_consume_token(ENTONCES);
      label_6:
      while (true) {
        sentencias();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LEER:
        case ESCRIBIR:
        case DEFINIR:
        case INICIO_CICLO_PARA:
        case INICIO_CICLO_MIENTRAS:
        case INICIO_CICLO_REPETIR:
        case INICIO_CONDICIONAL_SI:
        case INICIO_CONDICIONAL_SEGUN:
        case VARIABLE:
          ;
          break;
        default:
          jj_la1[17] = jj_gen;
          break label_6;
        }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONDICIONAL_SINO:
        jj_consume_token(CONDICIONAL_SINO);
        label_7:
        while (true) {
          sentencias();
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LEER:
          case ESCRIBIR:
          case DEFINIR:
          case INICIO_CICLO_PARA:
          case INICIO_CICLO_MIENTRAS:
          case INICIO_CICLO_REPETIR:
          case INICIO_CONDICIONAL_SI:
          case INICIO_CONDICIONAL_SEGUN:
          case VARIABLE:
            ;
            break;
          default:
            jj_la1[18] = jj_gen;
            break label_7;
          }
        }
        break;
      default:
        jj_la1[19] = jj_gen;
        ;
      }
      jj_consume_token(FIN_CONDICIONAL_SI);
    } catch (ParseException e) {
        Token t;
        do{
            t=getNextToken();
        }while(t.kind != FIN_CONDICIONAL_SI);
        tabla.add("Parser error:" + e.getMessage());
    }
  }

  final public void sentenciaSegun() throws ParseException {
    jj_consume_token(INICIO_CONDICIONAL_SEGUN);
    jj_consume_token(VARIABLE);
    jj_consume_token(HACER);
    label_8:
    while (true) {
      jj_consume_token(CASO);
      constantes();
      jj_consume_token(OPERADOR_DOS_PUNTOS);
      label_9:
      while (true) {
        sentencias();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LEER:
        case ESCRIBIR:
        case DEFINIR:
        case INICIO_CICLO_PARA:
        case INICIO_CICLO_MIENTRAS:
        case INICIO_CICLO_REPETIR:
        case INICIO_CONDICIONAL_SI:
        case INICIO_CONDICIONAL_SEGUN:
        case VARIABLE:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_9;
        }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CASO:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_8;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CASO_PREDETERMINADO:
      jj_consume_token(CASO_PREDETERMINADO);
      jj_consume_token(OPERADOR_DOS_PUNTOS);
      label_10:
      while (true) {
        sentencias();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LEER:
        case ESCRIBIR:
        case DEFINIR:
        case INICIO_CICLO_PARA:
        case INICIO_CICLO_MIENTRAS:
        case INICIO_CICLO_REPETIR:
        case INICIO_CONDICIONAL_SI:
        case INICIO_CONDICIONAL_SEGUN:
        case VARIABLE:
          ;
          break;
        default:
          jj_la1[22] = jj_gen;
          break label_10;
        }
      }
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
    jj_consume_token(FIN_SEGUN);
  }

//Ciclo for
  final public void sentenciaPara() throws ParseException {
    try {
      jj_consume_token(INICIO_CICLO_PARA);
      jj_consume_token(VARIABLE);
      jj_consume_token(ASIGNACION);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CADENA_TEXTO:
      case CARACTER_TEXTO:
      case NUMERO_ENTERO:
      case NUMERO_DECIMAL:
      case BOOLEANO_FALSO:
      case BOOLEANO_VERDADERO:
        constantes();
        break;
      case VARIABLE:
        jj_consume_token(VARIABLE);
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(CONDICION_CICLO_PARA);
      condicion();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INCREMENTO_CICLO_PARA:
        jj_consume_token(INCREMENTO_CICLO_PARA);
        break;
      case DECREMENTO_CICLO_PARA:
        jj_consume_token(DECREMENTO_CICLO_PARA);
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMERO_ENTERO:
        jj_consume_token(NUMERO_ENTERO);
        break;
      case NUMERO_DECIMAL:
        jj_consume_token(NUMERO_DECIMAL);
        break;
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      label_11:
      while (true) {
        sentencias();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LEER:
        case ESCRIBIR:
        case DEFINIR:
        case INICIO_CICLO_PARA:
        case INICIO_CICLO_MIENTRAS:
        case INICIO_CICLO_REPETIR:
        case INICIO_CONDICIONAL_SI:
        case INICIO_CONDICIONAL_SEGUN:
        case VARIABLE:
          ;
          break;
        default:
          jj_la1[27] = jj_gen;
          break label_11;
        }
      }
      jj_consume_token(FIN_CICLO_PARA);
    } catch (ParseException e) {
        Token t;
        do{
            t=getNextToken();
        }while(t.kind != FIN); //FIN_CICLO_PARA.kind = 44
        tabla.add("Parser error:" + e.getMessage());
    }
  }

//Ciclo while
  final public void sentenciaRepetir() throws ParseException {
    jj_consume_token(INICIO_CICLO_REPETIR);
    label_12:
    while (true) {
      sentencias();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LEER:
      case ESCRIBIR:
      case DEFINIR:
      case INICIO_CICLO_PARA:
      case INICIO_CICLO_MIENTRAS:
      case INICIO_CICLO_REPETIR:
      case INICIO_CONDICIONAL_SI:
      case INICIO_CONDICIONAL_SEGUN:
      case VARIABLE:
        ;
        break;
      default:
        jj_la1[28] = jj_gen;
        break label_12;
      }
    }
    jj_consume_token(CONDICION_CICLO_REPETIR);
    condicion();
    jj_consume_token(DELIMITADOR);
  }

//Ciclo do while
  final public void sentenciaMientras() throws ParseException {
    jj_consume_token(INICIO_CICLO_MIENTRAS);
    condicion();
    jj_consume_token(HACER);
    label_13:
    while (true) {
      sentencias();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LEER:
      case ESCRIBIR:
      case DEFINIR:
      case INICIO_CICLO_PARA:
      case INICIO_CICLO_MIENTRAS:
      case INICIO_CICLO_REPETIR:
      case INICIO_CONDICIONAL_SI:
      case INICIO_CONDICIONAL_SEGUN:
      case VARIABLE:
        ;
        break;
      default:
        jj_la1[29] = jj_gen;
        break label_13;
      }
    }
    jj_consume_token(FIN_CICLO_MIENTRAS);
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_3_2() {
    if (jj_scan_token(ESCRIBIR)) return true;
    if (jj_scan_token(CADENA_TEXTO)) return true;
    if (jj_scan_token(COMA)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(ESCRIBIR)) return true;
    if (jj_scan_token(CADENA_TEXTO)) return true;
    if (jj_scan_token(DELIMITADOR)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public PseintTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  /** Whether we are looking ahead. */
  private boolean jj_lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[30];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1c0000,0x1c0000,0xfc00,0x40200000,0x80000000,0x20000000,0xfc00,0x9fc00000,0x20000000,0xfc00,0x9fc00000,0x7c00000,0x80000000,0x18000000,0x40200000,0xf8,0x80000,0x1c0000,0x1c0000,0x0,0x1c0000,0x0,0x1c0000,0x0,0xfc00,0x0,0x3000,0x1c0000,0x1c0000,0x1c0000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x422a100,0x422a100,0x0,0x0,0x1f,0x0,0x4000040,0x1f,0x0,0x4000040,0x1f,0x0,0x1f,0x0,0x0,0x0,0x0,0x422a100,0x422a100,0x80000,0x422a100,0x400000,0x422a100,0x800000,0x4000000,0xc00,0x0,0x422a100,0x422a100,0x422a100,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Pseint(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Pseint(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new PseintTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Pseint(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new PseintTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Pseint(PseintTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(PseintTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = jj_lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.add(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[66];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 30; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 66; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

        }
