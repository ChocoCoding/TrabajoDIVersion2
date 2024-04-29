module com.example.cocheconproperties {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cocheconproperties to javafx.fxml;
    exports com.example.cocheconproperties;
}