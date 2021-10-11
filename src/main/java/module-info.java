module traductor {
    requires javafx.controls;
    requires javafx.fxml;
    opens traductor to javafx.fxml;
    exports traductor;
}
