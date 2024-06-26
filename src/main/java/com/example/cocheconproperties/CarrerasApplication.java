package com.example.cocheconproperties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CarrerasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarrerasApplication.class.getResource("vista-carreras.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("estilos.css");
        stage.setTitle("Carreras de Coches");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}