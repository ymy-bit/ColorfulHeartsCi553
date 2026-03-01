module com.example.colorfulheartsci {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorfulheartsci553 to javafx.fxml;
    exports com.example.ColorfulHeartsCi553;
    exports com.example.colorfulheartsci553.game;
    opens com.example.colorfulheartsci553.game to javafx.fxml;
}