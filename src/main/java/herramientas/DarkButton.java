package herramientas;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class DarkButton extends Parent {
    private TranslateTransition animacionApagador= new TranslateTransition(Duration.seconds(.25));
    private FillTransition animacionFondo=new FillTransition(Duration.seconds(.25));
    private ParallelTransition animaciones=new ParallelTransition(animacionApagador,animacionFondo);
    private BooleanProperty encendido= new SimpleBooleanProperty(true);
    public BooleanProperty encendidoProperty(){return encendido;}
    public DarkButton(){
        double k=0.6;
        var fondo = new Rectangle(100*k, 50*k);
        fondo.setFill(Color.LIGHTGREEN);
        fondo.setStroke(Color.DARKGRAY);
        fondo.setArcWidth(50*k);
        fondo.setArcHeight(50*k);
        var apagador = new Circle(25*k);
        //apagador.setCenterX(25*k);
        apagador.setCenterX(75*k);
        apagador.setCenterY(25*k);
        apagador.setFill(Color.WHITESMOKE);
        apagador.setStroke(Color.DARKGRAY);
        var texto = new Label("Dark mode");
        texto.setPrefWidth(100);
        texto.setLayoutX((100*k-texto.getPrefWidth())/2);
        texto.setLayoutY(-20);
        texto.setAlignment(Pos.CENTER);
        texto.setTextAlignment(TextAlignment.CENTER);
        animacionFondo.setShape(fondo);
        animacionApagador.setNode(apagador);
        encendido.addListener((observable, estadoAnterior, estadoActual) -> {
            //Si se cambia a true, entonces se recorrerá a la derecha
            //animacionApagador.setToX(estadoActual?100*k-50*k:0);
            //Si se cambia a false, entonces se recorrerá a la izquierda
            animacionApagador.setToX(estadoActual?0:100*k-150*k);
            animacionFondo.setFromValue(estadoActual?Color.WHITE:Color.LIGHTGREEN);
            animacionFondo.setToValue(estadoActual?Color.LIGHTGREEN:Color.WHITE);
            animaciones.play();
        });
        apagador.setOnMouseClicked(evento->encendido.set(!encendido.get()));
        fondo.setOnMouseClicked(evento->encendido.set(!encendido.get()));
        getChildren().addAll(fondo, apagador, texto);
    }
}