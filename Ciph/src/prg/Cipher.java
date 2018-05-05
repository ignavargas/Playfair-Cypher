package prg;

/**
 * Created by IgnacioV on 19/11/2017.
 */
public class Cipher {

    //Globales:
    TABLA tabla;
    private String lock;
    private String code;

    /**
     * Constructor de la clase Cipher:
     * Inicializa las variables globales y crea una TABLA para la encriptacion.
     * @param lock es la clave que se ingresa para formar la tabla.
     * */
    public Cipher(String lock){

        this.lock = lock;
        code = "";

        tabla = new TABLA(lock);
    }

    /**
     * Metodo Encode:
     * Se encarga del proceso general de la codificacion del texto que se ingrese,
     * Utiliza recursividad para leer la hilera en pares.
     * El analizis de la codificacion la realiza con los metodos: retTableIndex y codificate.
     * @param substr es la hilera a la que se desea encriptar,
     *               cada vuelta recursiva recibira -2 caracteres inicales menos.
     * @return String con la hilera completamente codificada.
     *
     * */
    public String Encode(String substr){

        char a, b;
        int indexA[] = new int[2];
        int indexB[] = new int[2];
        int pos = 2;

        if(substr.length() == 1){

            a = substr.charAt(0);
            if(a != 'x') {
                b = 'x';
            }else{
                b = 'w';
            }

            indexA = retTableIndex(a);
            indexB = retTableIndex(b);

            //metodo de codificacion.
            code = codificate(indexA, indexB);
        }else{

            if(substr.length() == 2){

                a = substr.charAt(0);
                b = substr.charAt(1);

                indexA = retTableIndex(a);
                indexB = retTableIndex(b);
                //,etodo de codificacion
                code = codificate(indexA, indexB);
            }else {

                a = substr.charAt(0);
                b = substr.charAt(1);
                if (a != b) {

                    indexA = retTableIndex(a);
                    indexB = retTableIndex(b);
                    //metodo codificacion;
                    code = codificate(indexA, indexB);
                }else{
                    if(a != 'x') {
                        b = 'x';
                    }else{
                        b = 'w';
                    }

                    pos = 1;
                    indexA = retTableIndex(a);
                    indexB = retTableIndex(b);
                    //metodo codificacion;
                    code = codificate(indexA, indexB);
                }

                code += Encode(substr.substring(pos));
            }
        }

        return code;
    }


    /**
     * Metodo retTableIndex:
     * hace referencia a "Return Table Index" (retornar el indice de la tabla).
     * @param letter se recibe una unica letra (char).
     * @return -in Devuelve un arreglo con los indices correspondientes a la letra en la tabla
     * */
    public int[] retTableIndex(char letter){
        int in[] = new int[2];
        char tab[][] = tabla.getTabla();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if(tab[i][j] == letter){

                    in[0] = i;
                    in[1] = j;
                    i = 5;
                    j = 5;
                }
            }

        }
        return in;
    }

    /**
     * Metodo codificate:
     * Es el "core" de la clase Cipher.
     * Este metodo se encarga del intercambio de la letras originales por las cifradas basandose en las ubicaciones de las letras en la tabla.
     * @param x Es un arreglo de tipo entero con los indices correspondientes a la primera letra.
     * @param y Es un arreglo de tipo entero con los indices correspondientes a la segunda letra.
     * @return Un String con las dos letras codificadas.
     * */
    public String codificate(int x[], int y[]){

        int dif;
        int aux;
        String out = "";
        char cuadro[][] = tabla.getTabla();

        if(x[0] != y[0] && x[1] != y[1]){

            aux = x[1];
            x[1] = y[1];
            y[1] = aux;
            out += cuadro[x[0]][x[1]];
            out += cuadro[y[0]][y[1]];
        }else{

            if(x[0] != y[0] && x[1] == y[1]){

                dif = x[0] % 4;
                if(dif != x[0]){
                    out += cuadro[0][x[1]];
                }else{
                    out += cuadro[x[0]+1][x[1]];
                }

                dif = y[0] % 4;
                if(dif != y[0]){
                    out += cuadro[0][x[1]];
                }else{
                    out += cuadro[y[0]+1][y[1]];
                }

            }else{

                dif = x[1] % 4;
                if(dif != x[1]){
                    out += cuadro[x[0]][0];
                }else{
                    out += cuadro[x[0]][x[1]+1];
                }

                dif = y[1] % 4;
                if(dif != y[1]){
                    out += cuadro[y[0]][0];
                }else{
                    out += cuadro[y[0]][y[1]+1];
                }

            }
        }

        return out;
    }
}
