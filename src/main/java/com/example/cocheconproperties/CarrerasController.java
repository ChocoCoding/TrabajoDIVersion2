package com.example.cocheconproperties;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.net.URL;
import java.util.Optional;
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
    private Vehiculo cocheSeleccionado;
    private boolean primero;
    private String rutaCoche1 = "C:\\Users\\a16gonzalocd\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche1.png";
    private String rutaCoche2 = "C:\\Users\\a16gonzalocd\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche2.png";

    private String rutaCocheDefault = "C:\\Users\\a16gonzalocd\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche3.png";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String rutaAbsoluta = new File("coche1.png").getAbsolutePath();
        System.out.println(rutaAbsoluta);
        vehiculo1 = new Vehiculo(rutaCocheDefault,"coche1");
        vehiculo2 = new Vehiculo(rutaCocheDefault,"coche2");
        double startY = 100;
        double spacing = 10;
        vehiculo1.setTranslateX(10);
        vehiculo1.setTranslateY(startY);
        vehiculo2.setTranslateX(10);
        vehiculo2.setTranslateY(startY + 150 + spacing);

        root.getChildren().addAll(vehiculo1, vehiculo2);
    }

    @FXML
    private void onStartButtonClicked() {
        mostrarSeleccionCoche();
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


    private void mostrarSeleccionCoche() {
        cocheSeleccionado = null;
        ImageView imagenCoche1 = new ImageView(rutaCoche1);
        ImageView imagenCoche2 = new ImageView(rutaCoche2);

        // Creamos el botón "Start"
        ButtonType botonSeleccionarCoche = new ButtonType("Seleccionar Coche");
        Alert alert = new Alert(Alert.AlertType.NONE, "", botonSeleccionarCoche);
        alert.setTitle("Seleccionar Coche");
        alert.setHeaderText("Elige tu coche:");

        // Creamos un VBox para contener los ImageViews de los coches
        VBox vBox = new VBox();
        vBox.getChildren().addAll(imagenCoche1, imagenCoche2);

        // Creamos un HBox para contener el VBox y agregar un espacio entre las imágenes
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);

        // Configuramos el contenido del DialogPane del Alert con el HBox
        alert.getDialogPane().setContent(hBox);


        // Creamos los botones para seleccionar el coche
        ButtonType botonCoche1 = new ButtonType("coche1");
        ButtonType botonCoche2 = new ButtonType("coche2");
        alert.getButtonTypes().setAll(botonCoche1, botonCoche2, ButtonType.CANCEL);

        // Mostramos el Alert y esperamos a que se seleccione una opción
        Optional<ButtonType> resultado = alert.showAndWait();

        // Verificamos qué opción se seleccionó y actualizamos la variable cocheSeleccionado
        try {
            resultado.ifPresent(buttonType -> {
                if (buttonType == botonCoche1) {
                    cocheSeleccionado= vehiculo1 ;
                } else if (buttonType == botonCoche2) {
                    cocheSeleccionado= vehiculo2;
                }
            });
        }catch (Exception e){
            System.out.println("No has seleccionado ningun coche");
        }

        vehiculo1.setImage(new Image(rutaCoche1));
        vehiculo2.setImage(new Image(rutaCoche2));
        // Mostramos el coche seleccionado en consola
        System.out.println("Coche seleccionado: " + cocheSeleccionado.getNombre());
    }
}