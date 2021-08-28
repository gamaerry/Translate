import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import java.io.*;
public class ControladorTranslate {
    @FXML
    private TextArea seleccionable;
    @FXML
    private GridPane raiz;
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
    public void hacerSeleccionable(){
        seleccionable=new TextArea(traduccion.getText());
        traduccion.setVisible(false);
        seleccionable.setStyle("-fx-font-size: 16");
        GridPane.setMargin(seleccionable,new Insets(0,20,20,20));
        seleccionable.setEditable(false);
        raiz.add(seleccionable,0,2);
        seleccionable.setWrapText(true);
        seleccionable.setOnMouseExited(evento->deshacerSeleccionable());
    }
    public void deshacerSeleccionable(){
        seleccionable.setVisible(false);
        traduccion.setVisible(true);
    }
    public void cambiarTema(ActionEvent evento){
        // TODO: 27/08/21
    }
}
