module com.example.colorfulheartsci553 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.colorfulheartsci553 to javafx.fxml;
    exports com.example.colorfulheartsci553;
}