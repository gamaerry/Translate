package traductor;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;
public class ControladorTranslate {
    @FXML
    private GridPane raiz;
    @FXML
    private TextArea escribe, seleccionable;
    @FXML
    private Label traduccion;
    @FXML
    private Button translate;
    @FXML
    private RadioButton espanol, english;
    @FXML
    private DarkButton dark;
    @FXML
    private ToggleGroup languajes;
    @FXML
    private void initialize(){
        espanol.setToggleGroup(languajes);
        english.setToggleGroup(languajes);
        raiz.setStyle("-fx-base:#101010");
    }
    public void cambiarBoton(ActionEvent evento){
        RadioButton languaje=(RadioButton) evento.getSource();
        boolean toEnglish = languaje.getText().equals("English");
        translate.setText(toEnglish ?"Translate to":"Traducir al");
        escribe.setPromptText(toEnglish ?"Write in any language":"Escribe en cualquier idioma");
    }
    public void translateText(){ //Puede o no recibir el ActionEvent
        String textoTraducido;
        try{
            ProcessBuilder comando= new ProcessBuilder(
                    "trans","-b","-t",
                    espanol.isSelected()?"es":"en",
                    escribe.getText().isBlank() ?"¡ENTER TEXT!":escribe.getText());
            textoTraducido= new String(comando.start().getInputStream().readAllBytes(), UTF_8);
        }catch(IOException e){
            textoTraducido="¡ERROR WITH THE TRANSLATION ENGINE!:\n"+
                    "if you are Linux user in Debian based distro, install the engine with: \n"+
                    "sudo apt-get install translate-shell";
            traduccion.setTextAlignment(TextAlignment.CENTER);
        }
        traduccion.setText(textoTraducido);
    }
    public void hacerSeleccionable(){
        seleccionable=new TextArea(traduccion.getText());
        traduccion.setVisible(false);
        seleccionable.setStyle("-fx-font-size: 16");
        GridPane.setMargin(seleccionable,new Insets(0,20,20,20));
        seleccionable.setEditable(false);
        raiz.add(seleccionable,0,2);
        seleccionable.setWrapText(true);
        GridPane.setColumnSpan(seleccionable,3);
        seleccionable.setOnMouseExited(evento->deshacerSeleccionable());
    }
    public void deshacerSeleccionable(){
        seleccionable.setVisible(false);
        traduccion.setVisible(true);
    }
    public void cambiarTema(MouseEvent event){ //En este metodo SE TIENE que recibir el MouseEvent
        raiz.setStyle(dark.encendidoProperty().get()?"-fx-base:#101010":"");
        //slategray y darkslategray son muy cool
    }
}
