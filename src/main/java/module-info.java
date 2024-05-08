module com.example.cocheconproperties {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.cocheconproperties to javafx.fxml;
    exports com.example.cocheconproperties;
}