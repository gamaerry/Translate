package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Main extends Application {
    @Override
    public void start(Stage escenario) throws Exception {
        final Class<Main> clase=Main.class;
        final Parent raiz = FXMLLoader.load(clase.getResource("layout_translate.fxml"));
        escenario.setTitle("Traductor");
        escenario.setScene(new Scene(raiz, 750, 400));
        escenario.getIcons().add(new Image(clase.getResourceAsStream("ico.png")));
        escenario.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
