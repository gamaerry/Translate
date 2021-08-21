import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
public class Principal extends Application {
    @Override
    public void start(Stage escenario) throws Exception {
        //String[] datos={"layout_espejo.fxml","Espejo","250","300"}; //CaraCambiante
        String[] datos={"layout_translate.fxml","Translate!","600","400"}; //Translate
        Parent raiz = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(datos[0])));
        escenario.setTitle(datos[1]);
        escenario.setScene(
                new Scene(raiz, Integer.parseInt(datos[2]), Integer.parseInt(datos[3])));
        escenario.show();
    }
}