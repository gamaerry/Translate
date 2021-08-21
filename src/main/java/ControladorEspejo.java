import javafx.fxml.FXML;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
public class ControladorEspejo {
    @FXML
    private Arc boca;
    @FXML
    private Text texto;
    //@FXML private void initialize(){}   Este metodo opcional se ejecuta siempre que se inicia el fxml asociado
    public void apenar(){
        boca.setLength(180);
        texto.setStrikethrough(true);
    }
    public void sonreir(){
        boca.setLength(-180);
        texto.setStrikethrough(false);
    }
}