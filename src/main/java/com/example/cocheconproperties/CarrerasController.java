package com.example.cocheconproperties;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private Button startButton;
    @FXML
    private Button restartButton;

    private Vehiculo vehiculo1;
    private Vehiculo vehiculo2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehiculo1 = new Vehiculo("C:\\Users\\a16gonzalocd\\IdeaProjects\\CocheConProperties\\src\\main\\resources\\img\\coche1.png",root.getWidth());
        vehiculo2 = new Vehiculo("C:\\Users\\a16gonzalocd\\IdeaProjects\\CocheConProperties\\src\\main\\resources\\img\\coche1.png",root.getWidth());


        double startY = 100; // Adjust this value as needed
        double spacing = 20; // Adjust the spacing between vehicles as needed
        vehiculo1.setLayoutX(10); // Adjust X position as needed
        vehiculo1.setLayoutY(startY);
        vehiculo2.setLayoutX(10); // Adjust X position as needed
        vehiculo2.setLayoutY(startY + vehiculo1.getImage().getHeight() + spacing);

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

}