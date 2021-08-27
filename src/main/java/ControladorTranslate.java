import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;

import java.io.*;
public class ControladorTranslate {
    @FXML
    private Button translate;
    @FXML
    private TextArea escribe;
    @FXML
    private Label traduccion;
    @FXML
    private RadioButton espanol;
    @FXML
    private RadioButton english;
    @FXML
    private ToggleGroup languajes;
    @FXML
    private void initialize(){
        espanol.setToggleGroup(languajes);
        english.setToggleGroup(languajes);
    }
    public void cambiarBoton(ActionEvent evento){
        RadioButton languaje=(RadioButton) evento.getSource();
        boolean toEnglish = languaje.getText().equals("English");
        translate.setText(toEnglish ?"Translate to":"Traducir al");
        escribe.setPromptText(toEnglish ?"Write in any language":"Escribe en cualquier idioma");
    }
    public void translateText(){ //Puede o no recibir el ActionEvent
        StringBuilder textoTraducido=new StringBuilder();
        try{
            ProcessBuilder comando= new ProcessBuilder(
                    "trans","-b","-t",
                    espanol.isSelected()?"es":"en",
                    !escribe.getText().equals("") ? escribe.getText():"¡INGRESE TEXTO!");
            BufferedReader salida=new BufferedReader(
                    new InputStreamReader(comando.start().getInputStream()));
            salida.lines().forEach(linea-> textoTraducido.append(linea+"\n"));
        }catch(IOException e){
            textoTraducido.append("¡ERROR WITH THE TRANSLATION ENGINE!\n"+
                    "(if you are Linux user in Debian based distro, install the engine with: \n"+
                    "sudo apt-get install translate-shell)");
            traduccion.setTextAlignment(TextAlignment.CENTER);
        }
        traduccion.setText(textoTraducido.toString());
    }
}
