package com.example.cocheconproperties;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.*;

public class CarrerasController implements Initializable, PropertyChangeListener {
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

    @FXML
    private ImageView imgTienda;

    @FXML
    private Button btnSeleccionarCoches;

    @FXML
    private Label txtSaldo;

    private List<Vehiculo> cochesDisponibles;
    private List<Vehiculo> cochesTienda;


    private Vehiculo vehiculo1;
    private Vehiculo vehiculo2;
    private Vehiculo cocheSeleccionado;
    private boolean primero;
    private Image rutaCoche1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche1.png")));

    private Image rutaCocheDefault = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche6.png")));

    private Integer saldo = 500;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarImagenesTienda();
        inicializarCochesDisponibles();


        vehiculo1 = new Vehiculo(rutaCoche1,"coche1");
        vehiculo2 = new Vehiculo(rutaCocheDefault,"coche2");
        double startY = 200;
        double spacing = 10;
        vehiculo1.setTranslateX(10);
        vehiculo1.setTranslateY(startY);
        vehiculo2.setTranslateX(10);
        vehiculo2.setTranslateY(startY + 150 + spacing);

        vehiculo1.addPropertyChangeListener(this);
        vehiculo2.addPropertyChangeListener(this);

        root.getChildren().addAll(vehiculo1, vehiculo2);

        txtSaldo.setText("Saldo: " + saldo + "€");
    }

    private void mostrarSeleccionCoche() {
        cocheSeleccionado = null;
        ImageView imagenCoche1 = new ImageView(vehiculo1.getImage());
        ImageView imagenCoche2 = new ImageView(vehiculo2.getImage());

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
        // Mostramos el coche seleccionado en consola
        System.out.println("Coche seleccionado: " + cocheSeleccionado.getNombre());
    }


    @FXML
    public void onSeleccionarCoches(){
        // Crear ventana de selección de vehículos
        Stage ventanaSeleccion = new Stage();
        ventanaSeleccion.setTitle("Seleccionar Vehículo");

        // Crear contenedor para las imágenes de los vehículos
        VBox contenedorVehiculos = new VBox();
        contenedorVehiculos.setSpacing(10);

        // Agregar imágenes de vehículos al contenedor
        for (Vehiculo vehiculo : cochesDisponibles) {
            ImageView imageView = new ImageView(vehiculo.getImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            // Agregar evento de clic a la imagen del vehículo
            imageView.setOnMouseClicked(event -> {
                // Mover el vehículo seleccionado de cochesTienda a cochesDisponibles
                vehiculo1.setImage(vehiculo.getImage());
                // Cerrar la ventana de selección
                ventanaSeleccion.close();
            });

            contenedorVehiculos.setAlignment(Pos.CENTER);
            contenedorVehiculos.getChildren().add(imageView);
        }

        // Crear una escena y mostrar la ventana de selección de vehículos
        Scene escena = new Scene(contenedorVehiculos, 600, 600);
        ventanaSeleccion.setScene(escena);
        ventanaSeleccion.show();
    }


    private void inicializarCochesDisponibles() {
        cochesDisponibles = new ArrayList<>();
        //String rutaCoche1 = "C:\\Users\\gonza\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche1.png";
        //String rutaCoche2 = "C:\\Users\\gonza\\IdeaProjects\\TrabajoDIVersion2\\src\\main\\resources\\img\\coche2.png";
        Image rutaCoche1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche1.png")));
        Image rutaCoche2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche6.png")));

        Vehiculo coche1 = new Vehiculo(rutaCoche1,"coche1");
        Vehiculo coche2 = new Vehiculo(rutaCoche2,"coche2");

        cochesDisponibles.add(coche1);
        cochesDisponibles.add(coche2);
    }

    private void inicializarImagenesTienda() {
        cochesTienda = new ArrayList<>();
        Image rutaCoche3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche3.png")));
        Image rutaCoche4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche4.png")));
        Image rutaCoche5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/coche5.png")));

        Vehiculo coche3 = new Vehiculo(rutaCoche3,"coche3");
        coche3.setPrecio(300);
        Vehiculo coche4 = new Vehiculo(rutaCoche4,"coche4");
        coche4.setPrecio(500);
        Vehiculo coche5 = new Vehiculo(rutaCoche5,"coche5");
        coche5.setPrecio(1000);

        cochesTienda.add(coche3);
        cochesTienda.add(coche4);
        cochesTienda.add(coche5);

    }

    @FXML
    private void onStartButtonClicked() {
        if (cocheSeleccionado == null){
            mostrarSeleccionCoche();
        }
        vehiculo1.mover();
        vehiculo2.mover();
        btnStart.setDisable(true);
    }

    @FXML
    private void onRestartButtonClicked() {
        cocheSeleccionado = null;
        btnStart.setDisable(false);
        vehiculo1.detener();
        vehiculo2.detener();
        vehiculo1.setLayoutX(0);
        vehiculo2.setLayoutX(0);
        vehiculo1.setVelocidadAleatoria();
        vehiculo2.setVelocidadAleatoria();
    }

    @FXML
    private void onStopButtonClicked(){
        btnStart.setDisable(false);
        vehiculo1.detener();
        vehiculo2.detener();
    }

    @FXML
    private void onAcelerarButtonClicked(){
        vehiculo1.acelerar();
        vehiculo2.acelerar();
    }



    public void mostrarAlertVictoria(){
        Platform.runLater(() -> {
            vehiculo1.detener();
            vehiculo2.detener();
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/v.jpg"))));
            imageView.setFitWidth(500); // ajusta el ancho de la imagen
            imageView.setFitHeight(300); // ajusta la altura de la imagen

            VBox vBox = new VBox();
            vBox.getChildren().addAll(imageView);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("VICTORIA");
            alert.setHeaderText("Has ganado");
            alert.getDialogPane().setContent(vBox);

            alert.showAndWait();
            saldo += 500;
            txtSaldo.setText("Saldo: " + saldo+ "€");
        });
    }

    public void mostrarAlertDerrota(){
        Platform.runLater(() -> {
            vehiculo1.detener();
            vehiculo2.detener();
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/l.jpg"))));
            imageView.setFitWidth(500); // ajusta el ancho de la imagen
            imageView.setFitHeight(300); // ajusta la altura de la imagen


            VBox vBox = new VBox();
            vBox.getChildren().addAll(imageView);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DERROTA");
            alert.setHeaderText("Has perdido");
            alert.getDialogPane().setContent(vBox);

            alert.showAndWait();
            cocheSeleccionado = null;
            saldo -= 150;
            txtSaldo.setText("Saldo: " + saldo+ "€");
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName ( );
        Object oldValue = evt.getOldValue ();
        Object newValue = evt.getNewValue ();
        Vehiculo vehiculo = (Vehiculo) evt.getSource();

        if ((boolean) newValue && vehiculo.getNombre().equals(cocheSeleccionado.getNombre())){
            mostrarAlertVictoria();

        }else {
           mostrarAlertDerrota();
        }

        System.out.println(propertyName  + vehiculo.getNombre());
        System.out.println(oldValue);
        System.out.println(newValue);
    }

    @FXML
    private void onTiendaClicked(){
        Stage ventanaSeleccion = new Stage();
        ventanaSeleccion.setTitle("Tienda de Coches");

        // Crear contenedor para las imágenes de los vehículos
        VBox contenedorVehiculos = new VBox();
        contenedorVehiculos.setSpacing(30);

        // Agregar imágenes de vehículos al contenedor
        for (Vehiculo vehiculo : cochesTienda) {
            HBox contenedorCoche = new HBox(); // Contenedor para imagen y precio
            contenedorCoche.setAlignment(Pos.CENTER_LEFT);
            contenedorCoche.setSpacing(10);


            ImageView imageView = new ImageView(vehiculo.getImage());
            imageView.setFitWidth(150);
            imageView.setFitHeight(75);

            // Agregar evento de clic a la imagen del vehículo
            imageView.setOnMouseClicked(event -> {
                if (saldo >= vehiculo.getPrecio()){
                    saldo-= vehiculo.getPrecio();
                    txtSaldo.setText("Saldo: " + saldo + "€");
                    cochesTienda.remove(vehiculo);
                    cochesDisponibles.add(vehiculo);
                    ventanaSeleccion.close();
                    mostrarAlertCompra();
                }else {
                    mostrarAlertSaldoInsuficiente();
                }
            });

            Label labelPrecio = new Label("Precio: " + vehiculo.getPrecio()+"€");
            labelPrecio.setAlignment(Pos.CENTER);

            contenedorCoche.setAlignment(Pos.CENTER);
            // Agregar imagen y precio al contenedor
            contenedorCoche.getChildren().addAll(imageView, labelPrecio);

            // Agregar contenedor al contenedor principal de vehículos
            contenedorVehiculos.getChildren().add(contenedorCoche);

        }

        // Crear una escena y mostrar la ventana de selección de vehículos
        Scene escena = new Scene(contenedorVehiculos, 400, 600);
        ventanaSeleccion.setScene(escena);
        ventanaSeleccion.show();
    }

    public void mostrarAlertCompra() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("COMPRA");
            alert.setHeaderText("Enorabuena por tu compra");
            alert.showAndWait();
    }

    public void mostrarAlertSaldoInsuficiente() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SALDO INSUFICIENTE");
        alert.setHeaderText("No tienes dinero para comprar este coche");
        alert.showAndWait();
    }
}