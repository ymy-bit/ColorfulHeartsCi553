module com.example.colorfulheartsci {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;


    opens com.example.colorfulheartsci553 to javafx.fxml;
    exports com.example.colorfulheartsci553.game;
    opens com.example.colorfulheartsci553.game to javafx.fxml;
    exports com.example.colorfulheartsci553.utils.enums;
    opens com.example.colorfulheartsci553.utils.enums to javafx.fxml;
    exports com.example.colorfulheartsci553.game.prefabs;
    opens com.example.colorfulheartsci553.game.prefabs to javafx.fxml;
    exports com.example.colorfulheartsci553.game.tests;
    exports com.example.colorfulheartsci553;
}