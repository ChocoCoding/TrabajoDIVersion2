package com.example.cocheconproperties;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CarrerasController implements Initializable {


    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imageViewCoche1;

    @FXML
    private ImageView imageViewCoche2;

    @FXML
    private Button btnStart;
    @FXML
    private Button btnRestart;
    @FXML
    private Button btnAcelerar;
    @FXML
    private Button btnStop;

    private Vehiculo vehiculo1;
    private Vehiculo vehiculo2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String rutaAbsoluta = new File("coche1.png").getAbsolutePath();
        System.out.println(rutaAbsoluta);
        vehiculo1 = new Vehiculo("C:\\Users\\gonza\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche1.png",root);
        vehiculo2 = new Vehiculo("C:\\Users\\gonza\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche2.png",root);

        double startY = 100; // Adjust this value as needed
        double spacing = 10; // Adjust the spacing between vehicles as needed
        vehiculo1.setTranslateX(10); // Adjust X position as needed
        vehiculo1.setTranslateY(startY);
        vehiculo2.setTranslateX(10); // Adjust X position as needed
        vehiculo2.setTranslateY(startY + 150 + spacing);

        root.getChildren().addAll(vehiculo1, vehiculo2);
    }

    @FXML
    private void onStartButtonClicked() {
        vehiculo1.mover();
        vehiculo2.mover();
    }

    @FXML
    private void onRestartButtonClicked() {
        vehiculo1.setLayoutX(0);
        vehiculo2.setLayoutX(0);
        vehiculo1.setVelocidadAleatoria();
        vehiculo2.setVelocidadAleatoria();
    }

    @FXML
    private void onStopButtonClicked(){
        vehiculo1.detener();
        vehiculo2.detener();

    }

    @FXML
    private void onAcelerarButtonClicked(){
        vehiculo1.acelerar();
        vehiculo2.acelerar();
    }
}