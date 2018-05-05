package prg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IgnacioV on 19/11/2017.
 */
public class Lector {

    //Globaes:
    private String text;
    private static String regEx;
    Pattern pattern;
    Matcher mat;
    private int indexN;
    private int indexAct;
    private String output;

    /**
     * Cinstructor de la Clase Lector
     * Case encargada de la lectura analizis y posterior validacion de la entrada
     * de hileras e informacion otaorgadas por el usuario por medio de una clase Interface
     * @param inputTxt Es el texto por ser codificado,
     * */
    public Lector(String inputTxt){

        text = inputTxt;
        regEx = "[^a-z ]";
        pattern = Pattern.compile(regEx);
        mat = pattern.matcher(text);
        indexN = 0;
        indexAct = -1;
        output = "";
    }

    /**
     * Metodo setWord:
     * este metodo es el encargado de preparar la oracion o mensaje deseeado para su codificacion.
     * Utiliza la variable global text para la modificacion.
     * Su acomdacion requere un analisis de la hilera en busqueda de espacios y caracteres faltantes
     * para la ejecucoion adecuada del codificador.
     *
     * */
    public void setWord(){

        char esp = text.charAt(0);//revisa si hay un espacio inicial
        if(esp == ' '){

            text = text.substring(1);
        }
        esp = text.charAt(text.length()-1);//revisa si hay un espacio final
        if(esp == ' '){

            text = text.substring(0, text.length()-1);
        }

        indexAct = text.indexOf(" ", indexN);

        if(indexAct >= 0) {//revisa si hay más espacios a lo largo de la hilera
            while (indexAct != -1) {

                output += text.substring(indexN, indexAct);
                indexN = indexAct + 1;
                indexAct = text.indexOf(" ", indexN);
            }

            if (indexN > 0) {

                output += text.substring(indexN, text.length());
            }

        }else{
            output = text;
        }
    }

    /**
     * Metodo getWord:
     * @return output el texto que contiene el Lector, se debe usar despues de invocar
     * el metodo setWord(), sino este devolverá el texto sin modificar.
     * */
    public String getWord(){

        return output;
    }

    /**
     * Metodo validWord:
     * Se encarga de verificar que el texto contenga la sintaxis correcta
     * @return true, en caso de ser una escritura valida, false cualquier otro caso
     * */
    public boolean validWord(){

        boolean valid = false;

        if(!mat.find()){

            valid = true;
        }
        return valid;
    }

    /**
     * Metodo validKey:
     * Se encarga de validar la escritura de la clave
     * @return true, si se valida la clave, false en cualquier otro caso.
     * */
    public boolean validKey(String key){
        if (key.length() != 5){
            return false;
        }else{

            Pattern p = Pattern.compile("[^a-z]");
            Matcher m = p.matcher(key);
            if(m.find()){return false; }else {
                for (int k = 0; k < key.length() - 1; k++) {

                    if (key.charAt(k) == key.charAt(k + 1)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
