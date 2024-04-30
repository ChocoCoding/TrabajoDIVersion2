package com.example.cocheconproperties;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Vehiculo extends ImageView {

    private double velocidadX;
    private double velocidadMaxima = 5.0;
    private boolean enMovimiento = false;
    private Random random = new Random();
    private AnimationTimer timer;

    public Vehiculo(String rutaImagen, AnchorPane root) {
        super(rutaImagen);
        setVelocidadAleatoria();
        this.setFitWidth(150);
        this.setFitHeight(75);
    }

    void setVelocidadAleatoria() {
        velocidadX = Math.random() * velocidadMaxima;
    }

    public void mover() {
        if (!enMovimiento) {
            enMovimiento = true;
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double nextX = getLayoutX() + velocidadX;
                    double paneWidth = ((Pane)getParent()).getWidth();
                    // Check if the next X position is within the screen bounds
                    if (nextX + getImage().getWidth() >= paneWidth) {
                        setLayoutX(paneWidth - getImage().getWidth());
                        System.out.println(nextX);
                        detener();
                    } else {
                        // Adjust X position to ensure the vehicle doesn't go beyond the screen edge
                        setLayoutX(nextX);
                        // Stop the vehicle

                    }
                    // Randomly accelerate the vehicle
                    if (random.nextInt(100) < 1) { // Adjust probability as needed (5% chance here)
                        acelerar();
                    }
                }
            };
            timer.start();
        }
    }

    public void acelerar() {
        velocidadX += random.nextDouble() * 2; // Randomly increase velocity (0 to 2)
        if (velocidadX > velocidadMaxima) {
            velocidadX = velocidadMaxima;
        }
    }

    public void frenar() {
        velocidadX -= 1.0;
        if (velocidadX < 0) {
            velocidadX = 0;
        }
    }


    // Método para detener el vehículo
    public void detener() {
        velocidadX = 0;
        enMovimiento = false;
        if (timer != null) { // Check if timer is initialized before stopping
            timer.stop();
        }
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }
}
