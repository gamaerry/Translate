import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public void translateText(){
        StringBuilder textoTraducido=new StringBuilder(" ");
        try{
            ProcessBuilder comando= new ProcessBuilder(
                    "trans","-b","-t",
                    espanol.isSelected()?"es":"en",
                    !escribe.getText().equals("") ? escribe.getText():"¡INGRESE TEXTO!");
            BufferedReader salida=new BufferedReader(
                    new InputStreamReader(comando.start().getInputStream()));
            salida.lines().forEach(textoTraducido::append);
        }catch(IOException e){
            textoTraducido.append("¡ERROR CON EL MOTOR DE TRADUCCIÓN!");
        }
        traduccion.setText(textoTraducido.toString());
    }
}
