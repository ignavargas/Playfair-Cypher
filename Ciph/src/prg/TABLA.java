package prg;

/**
 * Created by IgnacioV on 19/11/2017.
 */
public class TABLA {

    //Variables globales:
    private int letra;
    static private char tabla[][];
    int letraActual;
    private static String clave;

    /**
     * Constructor de la clase Tabla
     * Inicializa las variables globales y llama al metodo CreateTbale para llenar la tabla
     * a partir de la tabla corresponfiente
     * @param clavePF la clave que se usara como base para formar la tabla y codificar
     * */
    public TABLA(String clavePF){
        letra = 97;
        letraActual = 0;
        clave = clavePF;
        tabla = new char[5][5];
        CreateTable();

    }

    /**
     * Metodo Create Table:
     * Toma el arreglo de dos dimensiones y llena la primera fila con la clave (variable global)
     * Luego llena el resto del arreglo con el resto del abecedario.
     * */
    public void CreateTable(){

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                if(i<1){

                    tabla[i][j] = clave.charAt(j);
                }else{

                    letraActual = clave.indexOf(letra);
                    if(letraActual >= 0){
                        j--;
                    }else{
                        tabla[i][j] = (char)letra;

                    }
                    letra++;
                }



            }

        }
    }

    public char[][] getTabla() {
        return tabla;
    }
}
