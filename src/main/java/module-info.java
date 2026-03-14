module com.example.colorfulheartsci {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.colorfulheartsci553 to javafx.fxml;
    exports com.example.colorfulheartsci553.game;
    opens com.example.colorfulheartsci553.game to javafx.fxml;
}