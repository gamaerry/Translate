import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;
public class App extends Application {
    @Override
    public void start(Stage escenario) throws Exception {
        Parent raiz = FXMLLoader.load(getClass().getResource("layout_translate.fxml"));
        escenario.setTitle("Traductor");
        escenario.setScene(new Scene(raiz, 750, 400));
        escenario.getIcons().add(new Image("ico.png"));
        escenario.show();
    }
}
