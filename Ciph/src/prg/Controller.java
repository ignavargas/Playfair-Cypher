package prg;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class Controller {
    public TextField textIn;
    public Button codeText;
    public PasswordField keyIn;
    public TextField resultCode;
    public Button helpBt;



    public void encodeText(ActionEvent actionEvent) {

        codeText.defaultButtonProperty().bind(codeText.focusedProperty());

        Lector lector = new Lector(textIn.getText());
        if(lector.validWord() && lector.validKey(keyIn.getText())){

            lector.setWord();

            Cipher ciph = new Cipher(keyIn.getText());
            resultCode.setText( ciph.Encode(lector.getWord()));
        }else{
            ActionEvent AE = new ActionEvent();
            getHelp(AE);
        }

    }

    public void getHelp(ActionEvent actionEvent) {

        JOptionPane.showMessageDialog(null, "Este es un codificador de metodo Playfair.\n Encripta el texto deseado a partir de una clave.\n -Metodo de uso:\n" +
                "1)Ingrese el texto que deseea codificar.\n *NOTA: El texto no puede incluir Mayusculas ni valores no alfabeticos.**" +
                "\n 2)Ingrese la clave.\n*NOTA: la clave debe ser una palabra de 5 letras, SOLO minusculas, sin letras repetidas**\n" +
                "3)Oprima ENCODE y reciba el texto encriptado", "Ayuda - Manual de Uso", JOptionPane.INFORMATION_MESSAGE);

    }
}
