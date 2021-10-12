package traductor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.UTF_8;
public class TranslateController {
    private final ProcessBuilder COMANDO= new ProcessBuilder();
    @FXML
    private final TextArea SELECCIONABLE=new TextArea();
    @FXML
    private GridPane raiz;
    @FXML
    private TextArea escribe;
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
        SELECCIONABLE.setVisible(false);
        SELECCIONABLE.setStyle("-fx-font-size: 16");
        SELECCIONABLE.setEditable(false);
        SELECCIONABLE.setWrapText(true);
        SELECCIONABLE.setOnMouseExited(evento->deshacerSeleccionable());
        GridPane.setMargin(SELECCIONABLE,new Insets(0,20,20,20));
        GridPane.setColumnSpan(SELECCIONABLE,3);
        raiz.add(SELECCIONABLE,0,2);
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
            COMANDO.command("trans","-b","-t",
                    espanol.isSelected()?"es":"en",
                    escribe.getText().isBlank() ?"¡ENTER TEXT!":escribe.getText());
            textoTraducido= new String(COMANDO.start().getInputStream().readAllBytes(), UTF_8);
        }catch(IOException e){
            textoTraducido="¡ERROR WITH THE TRANSLATION ENGINE!:\n if you are Linux user in Debian based distro, install the engine with: \n sudo apt-get install translate-shell";
            traduccion.setTextAlignment(TextAlignment.CENTER);
        }
        traduccion.setText(textoTraducido);
    }
    public void hacerSeleccionable(){
        SELECCIONABLE.setText(traduccion.getText());
        traduccion.setVisible(false);
        SELECCIONABLE.setVisible(true);
    }
    public void deshacerSeleccionable(){
        SELECCIONABLE.setVisible(false);
        traduccion.setVisible(true);
    }
    public void cambiarTema(){
        raiz.setStyle(dark.encendidoProperty().get()?"-fx-base:#101010":"");
        //slategray y darkslategray son muy cool
    }
}
