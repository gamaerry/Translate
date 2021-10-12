package traductor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
    @Override
    public void start(Stage escenario) throws Exception {
        escenario.setTitle("Traductor");
        escenario.setScene(new Scene(
                FXMLLoader.load(Main.class.getResource("layout_translate.fxml")), //raiz
                750,
                400));
        //escenario.getIcons().add(new Image(clase.getResourceAsStream("ico.png")));
        escenario.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
